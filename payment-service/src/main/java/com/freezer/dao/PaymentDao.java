/**
 * @FileName: PaymentDao
 * @Author: zzc
 * @Date: 2019年12月30日 20:08:59
 * @Version V1.0.0
 */

package com.freezer.dao;

import freezer.dto.Payment;
import freezer.dto.WxUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PaymentDao {

//    @Update("update wx_user, goods set wx_user.account = wx_user.account - totalPrice, goods.stock = " +
//            "goods.stock - 1, goods.sell = goods.sell + 1 where wx_user.id = #{wxUserId} and goods.id = " +
//            "#{good.id} and goods.stock > 0")
//    int pay(@Param("wxUserId") int wxUserId, @Param("totalPrice") float totalPrice, @Param("good") Payment payment);

//    @Update("<foreach collection='paymentList' item='payment' ' + 'open='(' separator=',' close=')'> " +
//            "update wx_user, goods <set> wx.user_account = wx_user.account - totalPrice, goods.stock = (select s.stock" +
//            " from (select stock from goods where id = #{payment.goodsId}) s) - #{payment.purchaseCount} </set> where id = " +
//            "#{payment.goodsId}  </foreach>")
//    int pay(@Param("wxUserId") int wxUserId, @Param("totalPrice") float totalPrice, @Param("paymentList")
//            List<Payment> paymentList);

    @Update("<script><foreach collection = 'paymentList' item ='payment' open='' close='' separator=';'>" +
            "update goods set goods.stock = (select s.stock from (select stock from goods where id = " +
            "#{payment.goodsId}) s) - #{payment.purchaseCount}, goods.sell = (select e.sell from (select sell " +
            "from goods where id = #{payment.goodsId}) e) + #{payment.purchaseCount} where goods.id = " +
            "#{payment.goodsId} and goods.stock > #{payment.purchaseCount}</foreach></script>")
    int updateStockAndSell(@Param("paymentList") List<Payment> paymentList);

    @Update("update wx_user set account = account - #{totalPrice} where wx_user.id = #{wxUser.id} " +
            "and #{wxUser.account} >= #{totalPrice}")
    int pay(@Param("wxUser") WxUser wxUser, @Param("totalPrice") float totalPrice);

    @Select("select account from wx_user where id = #{wxUserId}")
    float findUserAccount(int wxUserId);
}
