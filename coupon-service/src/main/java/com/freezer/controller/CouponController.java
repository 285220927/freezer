/**
 * @FileName: CouponController
 * @Author: zzc
 * @Date: 2020年01月06日 20:38:16
 * @Version V1.0.0
 */

package com.freezer.controller;

import com.freezer.service.CouponService;
import freezer.dto.Coupon;
import freezer.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @RequestMapping
    public List<Coupon> findAvailableCoupon(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        return couponService.findAvailableCoupon(user.getId());
    }
}
