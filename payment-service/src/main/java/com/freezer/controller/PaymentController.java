/**
 * @FileName: PaymentController
 * @Author: zzc
 * @Date: 2019年12月30日 20:03:28
 * @Version V1.0.0
 */

package com.freezer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import freezer.dto.Coupon;
import freezer.dto.Payment;
import freezer.dto.User;
import freezer.dto.WxUser;
import freezer.response.Response;
import com.freezer.service.PaymentService;
import freezer.serviceApi.CouponServiceApi;
import freezer.serviceApi.GoodServiceApi;
import freezer.serviceApi.UserServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/pay")
public class PaymentController {
    @Reference
    private GoodServiceApi goodServiceApi;
    @Reference
    private UserServiceApi userServiceApi;
    @Reference
    private CouponServiceApi couponServiceApi;
    @Autowired
    private PaymentService paymentService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Response pay(HttpServletRequest request, @RequestBody List<Payment> paymentList) {
        User user = (User) request.getAttribute("user");
        WxUser wxUser = userServiceApi.findWxUserByUserId(user.getId());
        Response goodsQueryResponse = goodServiceApi.checkBeforePay(wxUser, paymentList);
        if (!"2000".equals(goodsQueryResponse.getResponseCode())) {
            return goodsQueryResponse;
        }
        float totalPrice = Float.valueOf(goodsQueryResponse.getResponseMessage());
        Coupon coupon = couponServiceApi.selectBestCoupon(wxUser, paymentList);
        return paymentService.pay(wxUser, coupon != null ? totalPrice - coupon.getBestCouponDiscount() : totalPrice,
                totalPrice, paymentList);
    }
}

