/**
 * @FileName: InterceptorConfig
 * @Author: zzc
 * @Date: 2019年12月29日 16:22:55
 * @Version V1.0.0
 */

package com.freezer.config;

import com.freezer.intercepter.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor).addPathPatterns("/pay/**");
    }
}
