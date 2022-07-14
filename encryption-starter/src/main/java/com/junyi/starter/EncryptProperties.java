package com.junyi.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @time: 2022/7/14 11:26
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@ConfigurationProperties(prefix = "my.encrypt")
public class EncryptProperties {
    private final static String DEFAULT_KEY = "aaaabbbbccccdddd";
    private String key = DEFAULT_KEY;

    public String getKey() {
        return key;
    }

    public void setKey(String key)
}
