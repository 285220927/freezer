/**
 * @FileName: CouponDao
 * @Author: zzc
 * @Date: 2020年01月06日 20:39:23
 * @Version V1.0.0
 */

package com.freezer.dao;

import freezer.dto.Coupon;
import freezer.dto.GoodsCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CouponDao {

    @Select("select c.*, uc.count from coupon c inner join user_coupon uc on c.id = uc.coupon_id where uc.user_id = #{userId}")
    @Results({
            @Result(column = "effective_time", property = "effectiveTime"),
            @Result(column = "expire_time", property = "expireTime"),
            @Result(column = "money_condition", property = "moneyCondition"),
            @Result(column = "subtract_money", property = "subtractMoney"),
            @Result(column = "obtain_way", property = "obtainWay"),
            @Result(column = "goods_cate_id", property = "goodsCategory", one = @One(select = "findCateById")),
            @Result(column = "other_goods_id", property = "otherGoodsId"),
    })
    List<Coupon> findAvailableCoupon(int userId);

    @Select("select * from category where id = #{id}")
    @Results(@Result(column = "cate_name", property = "categoryName"))
    GoodsCategory findCateById(int id);

    @Update("update user_coupon set count = count - 1 where coupon_id = #{coupon_id} and user_id = #{user_id} and count > 0")
    int reduceCouponCount(@Param("coupon_id") int couponId, @Param("user_id") int userId);

    @Delete("delete from user_coupon where coupon_id = #{coupon_id} and user_id = #{user_id} and count = 0")
    void deleteZeroCountCoupon(@Param("coupon_id") int couponId, @Param("user_id") int userId);
}
