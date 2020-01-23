/**
 * @FileName: LoginService
 * @Author: zzc
 * @Date: 2019年12月28日 14:33:23
 * @Version V1.0.0
 */

package com.freezer.service;

import com.freezer.dao.LoginDao;
import freezer.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class LoginService {
    @Autowired
    private LoginDao loginDao;

    public User login(User user) {
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        return loginDao.login(user);
    }

    public void updateLastLoginTime(int userWxId) {
        loginDao.updateLastLoginTime(userWxId);
    }
}
