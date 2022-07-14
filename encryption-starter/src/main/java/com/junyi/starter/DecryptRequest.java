package com.junyi.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * 解密，这儿我们没有实现接口 RequestBodyAdvice，而是继承子类 RequestBodyAdviceAdapter，其实现了接口 RequestBodyAdvice
 * @time: 2022/7/14 11:34
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@EnableConfigurationProperties(EncryptProperties.class)
@RestControllerAdvice
public class DecryptRequest extends RequestBodyAdviceAdapter {

    @Autowired
    EncryptProperties encryptProperties;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.hasMethodAnnotation(Decrypt.class) || methodParameter.hasParameterAnnotation(Decrypt.class);
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        byte[] keyByte = encryptProperties.getKey().getBytes();
        byte[] buffer = new byte[inputMessage.getBody().available()];
        inputMessage.getBody().read(buffer);
        try {
            byte[] decryptByte = AESUtils.decrypt(buffer, keyByte);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(decryptByte);
            return new HttpInputMessage() {
                @Override
                public HttpHeaders getHeaders() {
                    return inputMessage.getHeaders();
                }

                @Override
                public InputStream getBody() throws IOException {
                    return inputStream;
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.beforeBodyRead(inputMessage, parameter, targetType, converterType);
    }
}
