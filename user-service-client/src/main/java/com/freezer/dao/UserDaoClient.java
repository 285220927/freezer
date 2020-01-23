/**
 * @FileName: UserDaoClient
 * @Author: zzc
 * @Date: 2019年12月30日 20:34:40
 * @Version V1.0.0
 */

package com.freezer.dao;

import freezer.dto.User;
import freezer.dto.WxUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDaoClient {

    @Select("select * from wx_user where user_id = #{userId}")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "last_login", property = "lastLogin"),
    })
    WxUser findWxUserByUserId(int userId);

    @Select("select * from user where id = (select user_id from wx_user where id = #{wxUserId})")
    User findUserByWxUserId(int wxUserId);
}
