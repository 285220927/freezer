/**
 * @FileName: FilterUriConfiguration
 * @Author: zzc
 * @Date: 2020年01月04日 19:00:01
 * @Version V1.0.0
 */

package com.freezer.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "filter")
public class FilterUriProperties {
    private List<String> uriList;

    public List<String> getUriList() {
        return uriList;
    }

    public void setUriList(List<String> uriList) {
        this.uriList = uriList;
    }
}
