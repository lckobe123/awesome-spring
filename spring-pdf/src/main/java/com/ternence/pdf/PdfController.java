package com.ternence.pdf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * create by 陶江航 at 2017/9/18 22:12
 * 符合restful的接口规则，URL中只包含表述性的名词，而动作则有http方法阐述
 *
 * @version 1.0
 * @description PDF相关的Controller
 */
@Controller
@RequestMapping(path = "/pdf")
public class PdfController {

    /**
     * 下载pdf的接口,/user表示user的pdf，GET表示用户的动作是获取-下载
     *
     * @param request  请求对象
     * @param response 响应对象
     */
    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public void downloadPdf(HttpServletRequest request, HttpServletResponse response) {
        //1:生成PDF

        //2:将PDF以流的方式写入到响应中
    }

}
