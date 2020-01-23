/**
 * @FileName: GoodServiceApi
 * @Author: zzc
 * @Date: 2019年12月29日 11:18:39
 * @Version V1.0.0
 */

package freezer.serviceApi;



import freezer.dto.Goods;
import freezer.dto.Payment;
import freezer.dto.WxUser;
import freezer.response.Response;

import java.util.List;

public interface GoodServiceApi {
    Goods findGoodById(int id);

    List<Goods> findGoodByIdList(List<String> otherGoodsId);

    Response checkBeforePay(WxUser wxUser, List<Payment> paymentList);
}
