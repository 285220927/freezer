/**
 * @FileName: UserController
 * @Author: zzc
 * @Date: 2019年12月28日 13:52:07
 * @Version V1.0.0
 */

package com.freezer.controller;

import com.freezer.response.LoginResponse;
import freezer.dto.User;
import freezer.response.Response;
import com.freezer.service.LoginService;
import freezer.utils.SerializerUtils;
import freezer.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Value("${user.loginTimeout}")
    private String loginTimeout;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Response login(HttpServletResponse response, User userLoginInfo) {
        User user = loginService.login(userLoginInfo);
        if (user == null) {
            return Response.USERNAME_OR_PASSWORD_INVALID;
        }
        // 更新上次登录时间
        loginService.updateLastLoginTime(user.getWxId());
        // 用户信息存入redis
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String uuid = Utils.getUuid();
        opsForValue.set(uuid, SerializerUtils.serializationObject(user), Long.parseLong(loginTimeout), TimeUnit.SECONDS);
//        Cookie cookie = new Cookie("user", uuid);
//        cookie.setPath("/");
//        cookie.setDomain("192.168.1.112");
//        response.addCookie(cookie);
        return new LoginResponse(uuid);
    }
}
