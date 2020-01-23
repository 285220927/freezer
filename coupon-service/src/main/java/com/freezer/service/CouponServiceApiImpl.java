/**
 * @FileName: CouponServiceImpl
 * @Author: zzc
 * @Date: 2020年01月12日 11:07:08
 * @Version V1.0.0
 */

package com.freezer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.freezer.dao.CouponDao;
import freezer.dto.Coupon;
import freezer.dto.Goods;
import freezer.dto.Payment;
import freezer.dto.WxUser;
import freezer.serviceApi.CouponServiceApi;
import freezer.serviceApi.GoodServiceApi;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CouponServiceApiImpl implements CouponServiceApi {
    @Autowired
    private CouponService couponService;
    @Autowired
    private CouponDao couponDao;
    @Reference
    private GoodServiceApi goodServiceApi;

    @Override
    public Coupon selectBestCoupon(WxUser wxUser, List<Payment> paymentList) {
        // 计算出最优的优惠券
        List<Coupon> availableCouponList = couponService.findAvailableCoupon(wxUser.getUserId());
        Map<String, String> paymentMap = new HashMap<>();
        for (Payment payment : paymentList) {
            paymentMap.put(String.valueOf(payment.getGoodsId()), String.valueOf(payment.getPurchaseCount()));
        }
        List<Goods> goodsList = goodServiceApi.findGoodByIdList(new ArrayList<>(paymentMap.keySet()));
        // key:可优惠金额 value:Coupon
        Map<Float, Coupon> totalAvailableCouponMap = new Hashtable<>();
        for (Coupon coupon : availableCouponList) {
            // 每张优惠券
            Date effectiveTime = coupon.getEffectiveTime();
            Date expireTime = coupon.getExpireTime();
            Date date = new Date();
            // 当前优惠券对于此次购买可用的减免金额
            float availCouponAmount = 0f;

            // 计算符合优惠券的商品分类的商品(goods_cate_id)
            for (Goods goods : goodsList) {
                if (goods.getGoodsCategory().getId() == coupon.getGoodsCategory().getId()) {
                    availCouponAmount += goods.getPrice() * Integer.valueOf(paymentMap.get(String.valueOf(goods.getId())));
                }
            }
            // 计算商品分类在优惠券的优惠商品之外的商品(other_goods_id)
            // 商品id,商品id,商品id... e.g 1,4,7 用逗号分隔
            String otherGoodsId = coupon.getOtherGoodsId();
            if (!StringUtils.isBlank(otherGoodsId)) {
                List<String> otherGoodsIdList = Arrays.asList(otherGoodsId.split(","));
                List<Goods> otherGoodsList = goodServiceApi.findGoodByIdList(otherGoodsIdList);
                for (Goods goods : otherGoodsList) {
                    if (paymentMap.containsKey(String.valueOf(goods.getId()))) {
                        availCouponAmount += goods.getPrice() * Integer.valueOf(paymentMap.get(String.valueOf(goods.getId())));
                    }
                }
            }
            // 根据有效期和总金额筛选可用的优惠券
            if ((effectiveTime.getTime() < date.getTime() && expireTime.getTime() > date.getTime()) &&
                    (coupon.getMoneyCondition() == 0f || coupon.getMoneyCondition() < availCouponAmount) &&
                    ((coupon.getRule() == 1 && coupon.getSubtractMoney() < availCouponAmount) ||
                            (coupon.getRule() == 2 && availCouponAmount != 0f))) {
                // 计算减免金额
                totalAvailableCouponMap.put(availCouponAmount, coupon);
            }
        }
        if (totalAvailableCouponMap.isEmpty()) {
            return null;
        }
        // 计算最优的优惠券
        Set<Map.Entry<Float, Coupon>> couponEntrySet = totalAvailableCouponMap.entrySet();
        Coupon bestCoupon = null;
        float bestCouponDiscount = 0f;
        boolean flag = true;
        for (Map.Entry<Float, Coupon> couponEntry : couponEntrySet) {
            Coupon currentCoupon = couponEntry.getValue();
            float currentCouponDiscount;
            if (currentCoupon.getRule() == 1) {
                // 满减
                currentCouponDiscount = currentCoupon.getSubtractMoney();
            } else {
                // 折扣
                currentCouponDiscount = couponEntry.getKey() - (couponEntry.getKey() * currentCoupon.getDiscount() / 10);
            }
            if (flag) {
                bestCoupon = currentCoupon;
                bestCouponDiscount = currentCouponDiscount;
                flag = false;
            } else {
                if (currentCouponDiscount > bestCouponDiscount) {
                    bestCoupon = currentCoupon;
                    bestCouponDiscount = currentCouponDiscount;
                }
            }
        }
        if (bestCoupon == null) {
            return null;
        }
        int reduceCouponResult = couponDao.reduceCouponCount(bestCoupon.getId(), wxUser.getUserId());
        if (reduceCouponResult != 1) {
            return null;
        }
        couponDao.deleteZeroCountCoupon(bestCoupon.getId(), wxUser.getUserId());
        BigDecimal b = new BigDecimal(bestCouponDiscount);
        bestCouponDiscount = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        bestCoupon.setBestCouponDiscount(bestCouponDiscount);
        return bestCoupon;
    }
}
