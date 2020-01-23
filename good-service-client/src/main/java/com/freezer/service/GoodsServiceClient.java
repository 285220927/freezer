/**
 * @FileName: GoodsServiceClient
 * @Author: zzc
 * @Date: 2019年12月29日 11:22:34
 * @Version V1.0.0
 */

package com.freezer.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.freezer.dao.GoodsDaoClient;
import freezer.dto.Goods;
import freezer.dto.Payment;
import freezer.dto.WxUser;
import freezer.response.Response;
import freezer.serviceApi.GoodServiceApi;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Service
public class GoodsServiceClient implements GoodServiceApi {
    @Autowired
    private GoodsDaoClient goodsDaoClient;

    @Override
    public Goods findGoodById(int id) {
        return goodsDaoClient.findGoodById(id);
    }

    @Override
    public List<Goods> findGoodByIdList(List<String> otherGoodsId) {
        return goodsDaoClient.findGoodByIdList(otherGoodsId);
    }

    @Override
    public Response checkBeforePay(WxUser wxUser, List<Payment> paymentList) {
        // 1.判断本次购买的商品库存
        Map<String, String> paymentMap = new HashMap<>();
        for (Payment payment : paymentList) {
            paymentMap.put(String.valueOf(payment.getGoodsId()), String.valueOf(payment.getPurchaseCount()));
        }
        // 本次购买的商品
        List<Goods> goods = this.findGoodByIdList(new ArrayList<>(paymentMap.keySet()));
        List<String> stockNotEnoughList = new LinkedList<>();
        for (Goods good : goods) {
            if (Integer.valueOf(paymentMap.get(String.valueOf(good.getId()))) > good.getStock()) {
                stockNotEnoughList.add(good.getGoodsName());
            }
        }
        if (stockNotEnoughList.size() > 0) {
            return new Response("1005", stockNotEnoughList.toString().
                    replace("[", "").replace("]", "") + " stock is not enough");
        }

        // 2.计算本次购买所需金额(使用优惠券之前)
        float totalAmount = 0;
        for (Goods good : goods) {
            totalAmount += good.getPrice() * Integer.valueOf(paymentMap.get(String.valueOf(good.getId())));
        }
        return new Response("2000", String.valueOf(totalAmount));
    }
}
