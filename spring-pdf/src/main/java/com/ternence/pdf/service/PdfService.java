package com.ternence.pdf.service;

import org.springframework.stereotype.Service;

import java.io.File;

/**
 * create by 陶江航 at 2017/9/18 22:46
 *
 * @version 1.0
 * @description 为PDF生成提供service
 */
public interface PdfService {
    /**
     * 通过freemarker模板，将模板渲染为一个html，然后作为File对象返回
     *
     * @param templatePath 模板路径
     * @return html
     */
    File getHtml(String templatePath);

    /**
     * 根据html文件渲染一个pdf，然后以File对象返回
     *
     * @param html pdf路径
     * @return PDF
     */
    File renderHtmlToPdf(File html);
}
