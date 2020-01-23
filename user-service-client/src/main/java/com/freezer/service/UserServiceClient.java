/**
 * @FileName: UserServiceClient
 * @Author: zzc
 * @Date: 2019年12月29日 13:02:52
 * @Version V1.0.0
 */

package com.freezer.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.freezer.dao.UserDaoClient;
import freezer.dto.User;
import freezer.dto.WxUser;
import freezer.serviceApi.UserServiceApi;
import freezer.utils.SerializerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@Service
public class UserServiceClient implements UserServiceApi {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserDaoClient userDaoClient;

    @Override
    public User findUserByCookie(String userCookieString) {
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String userSerializerString = opsForValue.get(userCookieString);
        User user = null;
        try {
            user = SerializerUtils.deserializationObject(userSerializerString, User.class);
        } catch (NullPointerException e) {
        }

        return user;
    }

    @Override
    public WxUser findWxUserByUserId(int userId) {
        return userDaoClient.findWxUserByUserId(userId);
    }
}
