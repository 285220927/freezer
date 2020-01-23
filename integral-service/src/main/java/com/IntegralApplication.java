/**
 * @FileName: IntegralApplication
 * @Author: zzc
 * @Date: 2020年01月18日 11:43:44
 * @Version V1.0.0
 */

package com;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class IntegralApplication {
    public static void main(String[] args) {
        SpringApplication.run(IntegralApplication.class, args);
    }
}
