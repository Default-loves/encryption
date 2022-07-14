package com.junyi.starter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.nio.charset.StandardCharsets;

/**
 * @time: 2022/7/14 11:27
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@EnableConfigurationProperties(EncryptProperties.class)
@ControllerAdvice
public class EncryptResponse implements ResponseBodyAdvice<RespBean> {

    @Autowired
    EncryptProperties encryptProperties;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.hasMethodAnnotation(Encrypt.class);
    }

    @Override
    public RespBean beforeBodyWrite(RespBean body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        try {
            byte[] keyByte = encryptProperties.getKey().getBytes();

            if (!StringUtils.isEmpty(body.getMsg())) {
                body.setMsg(AESUtils.encrypt(body.getMsg().getBytes(StandardCharsets.UTF_8), keyByte));
            }
            if (!StringUtils.isEmpty(body.getObj())) {
                body.setMsg(AESUtils.encrypt(objectMapper.writeValueAsBytes(body.getObj()), keyByte));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }
}
