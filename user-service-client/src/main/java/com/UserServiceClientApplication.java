/**
 * @FileName: UserServiceClientApplication
 * @Author: zzc
 * @Date: 2019年12月29日 13:11:14
 * @Version V1.0.0
 */

package com;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class UserServiceClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceClientApplication.class, args);
    }
}
