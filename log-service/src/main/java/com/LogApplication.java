/**
 * @FileName: LogApplication
 * @Author: zzc
 * @Date: 2020年01月23日 15:09:24
 * @Version V1.0.0
 */

package com;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class LogApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogApplication.class, args);
    }
}
