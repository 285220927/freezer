/**
 * @FileName: GoodsClientApplication
 * @Author: zzc
 * @Date: 2019年12月30日 21:29:51
 * @Version V1.0.0
 */

package com;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class GoodsClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsClientApplication.class, args);
    }
}
