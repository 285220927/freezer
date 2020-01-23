/**
 * @FileName: IntegralDao
 * @Author: zzc
 * @Date: 2020年01月18日 11:47:05
 * @Version V1.0.0
 */

package com.freezer.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface IntegralDao {

    @Update("update user set integral = integral + #{integralCount} where id = #{userId}")
    void addIntegral(int userId, float integralCount);
}
