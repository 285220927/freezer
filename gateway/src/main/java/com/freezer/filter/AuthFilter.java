/**
 * @FileName: AuthInterceptor
 * @Author: zzc
 * @Date: 2020年01月02日 20:45:35
 * @Version V1.0.0
 */

package com.freezer.filter;

import com.freezer.config.FilterUriProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
@EnableConfigurationProperties(FilterUriProperties.class)
public class AuthFilter extends ZuulFilter {
    @Autowired
    private FilterUriProperties filterUriProperties;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_ERROR_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestURI = request.getRequestURI();
        List<String> shouldFilterUriList = filterUriProperties.getUriList();
        for (String uri : shouldFilterUriList) {
            if (requestURI.contains(uri)) {
                return true;
            }
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = request.getParameter("token");
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                System.out.println(cookie.getName() + " " + cookie.getValue());
//            }
//        }
        return null;
    }
}
