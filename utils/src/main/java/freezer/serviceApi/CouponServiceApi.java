/**
 * @FileName: CouponServiceApi
 * @Author: zzc
 * @Date: 2020年01月06日 20:25:03
 * @Version V1.0.0
 */

package freezer.serviceApi;

import freezer.dto.Coupon;
import freezer.dto.Payment;
import freezer.dto.WxUser;

import java.util.List;

public interface CouponServiceApi {
    Coupon selectBestCoupon(WxUser user, List<Payment> paymentList);
}
