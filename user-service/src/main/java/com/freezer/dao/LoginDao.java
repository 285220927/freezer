/**
 * @FileName: LoginDao
 * @Author: zzc
 * @Date: 2019年12月28日 14:33:30
 * @Version V1.0.0
 */

package com.freezer.dao;

import freezer.dto.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LoginDao {
    @Select("select * from user where username = #{user.username} and password = #{user.password}")
    @Results({
            @Result(column = "wx_id", property = "wxId"),
            @Result(column = "create_time", property = "createDatetime"),
    })
    User login(@Param("user") User user);

    @Update("update wx_user set last_login = now() where id = #{userWxId}")
    void updateLastLoginTime(int userWxId);
}
