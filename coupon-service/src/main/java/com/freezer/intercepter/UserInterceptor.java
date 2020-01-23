/**
 * @FileName: UserInterceptor
 * @Author: zzc
 * @Date: 2019年12月29日 12:42:22
 * @Version V1.0.0
 */

package com.freezer.intercepter;

import com.alibaba.dubbo.config.annotation.Reference;
import freezer.dto.User;
import freezer.serviceApi.UserServiceApi;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;

@Component
public class UserInterceptor implements HandlerInterceptor {
    @Reference
    private UserServiceApi userService;
    private static final String redirectUrl = "http://192.168.181.130";

    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        String userSerializerString = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user".equals(cookie.getName())) {
                    userSerializerString = cookie.getValue();
                }
            }
        }
        if (StringUtils.isBlank(userSerializerString)) {
            userSerializerString = request.getParameter("token");
        }
        if (StringUtils.isBlank(userSerializerString) || userSerializerString.length() != 32) {
            response.sendRedirect(redirectUrl);
            return false;
        }
        User user = userService.findUserByCookie(userSerializerString);
        if (user == null) {
            response.sendRedirect(redirectUrl);
            return false;
        }
        request.setAttribute("user", user);
        return true;
    }

    @Override
    public void postHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
