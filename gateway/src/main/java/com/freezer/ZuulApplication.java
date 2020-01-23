/**
 * @FileName: ZuulApplication
 * @Author: zzc
 * @Date: 2019年12月31日 19:51:45
 * @Version V1.0.0
 */

package com.freezer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableZuulProxy
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}

