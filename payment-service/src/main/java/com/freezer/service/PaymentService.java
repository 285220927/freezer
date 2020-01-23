/**
 * @FileName: PaymentService
 * @Author: zzc
 * @Date: 2019年12月30日 20:08:37
 * @Version V1.0.0
 */

package com.freezer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.freezer.dao.PaymentDao;
import freezer.dto.Coupon;
import freezer.dto.Payment;
import freezer.dto.WxUser;
import freezer.response.Response;
import freezer.serviceApi.UserServiceApi;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Transactional
public class PaymentService {
    @Autowired
    private PaymentDao paymentDao;
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private AmqpAdmin amqpAdmin;
    @Value("${rabbitmq.queue-integral}")
    private String queue;
    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.routing-key-integral}")
    private String routingKey;

    public Response pay(WxUser wxUser, float totalPrice, float integral, List<Payment> paymentList) {
        int updateStockAndSellResult = paymentDao.updateStockAndSell(paymentList);
        if (updateStockAndSellResult != 1) {
            return Response.UNEXPECTED_ERROR;
        }
        int payResult = paymentDao.pay(wxUser, totalPrice);
        if (payResult != 1) {
            return Response.BALANCE_NOT_ENOUGH;
        }
        amqpTemplate.convertAndSend(exchange, routingKey, integral + "," + wxUser.getUserId());
        return new Response();
    }

    // 初始化同步
    @PostConstruct
    private void init() {
        // 创建交换机
        amqpAdmin.declareExchange(new DirectExchange(exchange));
        // 创建队列
        amqpAdmin.declareQueue(new Queue(queue, true));
        // 建立绑定关系
        amqpAdmin.declareBinding(new Binding(queue, Binding.DestinationType.QUEUE, exchange, routingKey, null));
    }
}
