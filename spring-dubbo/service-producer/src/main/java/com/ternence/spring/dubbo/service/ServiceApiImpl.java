package com.ternence.spring.dubbo.service;

import com.ternence.spring.dubbo.api.ServiceApi;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * service api 的实现类
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/5/8 15:15
 */
@Component
public class ServiceApiImpl implements ServiceApi {

    @Override
    public Object service(String word) {
        System.out.println("Hello , " + word);
        return Arrays.asList(getAllChar(word));
    }

    private char[] getAllChar(String word) {
        char[] chars = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            chars[i] = word.charAt(i);
        }
        return chars;
    }
}
