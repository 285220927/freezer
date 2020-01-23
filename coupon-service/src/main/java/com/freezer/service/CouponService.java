/**
 * @FileName: CouponService
 * @Author: zzc
 * @Date: 2020年01月06日 20:39:15
 * @Version V1.0.0
 */

package com.freezer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.freezer.dao.CouponDao;
import freezer.dto.Coupon;
import freezer.dto.Goods;
import freezer.serviceApi.GoodServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CouponService {
    @Autowired
    private CouponDao couponDao;
    @Reference
    private GoodServiceApi goodServiceApi;

    public List<Coupon> findAvailableCoupon(int userId) {
        List<Coupon> availableCoupon = couponDao.findAvailableCoupon(userId);
        for (Coupon coupon : availableCoupon) {
            String otherGoodsId = coupon.getOtherGoodsId();
            if (otherGoodsId != null) {
                List<String> otherGoodsList = Arrays.asList(otherGoodsId.split(","));
                List<Goods> goods = goodServiceApi.findGoodByIdList(otherGoodsList);
                coupon.setOtherGoods(goods);
            }
        }
        return availableCoupon;
    }
}
