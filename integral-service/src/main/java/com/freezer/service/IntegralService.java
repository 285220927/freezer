/**
 * @FileName: IntegralService
 * @Author: zzc
 * @Date: 2020年01月18日 11:43:28
 * @Version V1.0.0
 */

package com.freezer.service;

import com.freezer.dao.IntegralDao;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class IntegralService {
    @Autowired
    private IntegralDao integralDao;

    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("${rabbitmq.queue-integral}"),
            exchange = @Exchange("${rabbitmq.exchange}"),
            key = "${rabbitmq.routing-key-integral}"
    ))
    public void addIntegral(String integralInfo) {
        // integralInfo: integral,userId
        float integral = Float.valueOf(integralInfo.split(",")[0]);
        int userId = Integer.valueOf(integralInfo.split(",")[1]);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String week = sdf.format(new Date());
        if ("Saturday".equals(week) || "Sunday".equals(week)) {
            integral *= 2;
        }
        integralDao.addIntegral(userId, integral);
    }
}
