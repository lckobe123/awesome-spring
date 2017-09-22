package com.ternence.pdf.service.impl;

import com.ternence.pdf.dao.PdfDao;
import com.ternence.pdf.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 * create by 陶江航 at 2017/9/18 22:50
 *
 * @version 1.0
 * @description PDF service的实现类
 */
@Service("pdfServiceImpl")
public class PdfServiceImpl implements PdfService {
    @Autowired
    private PdfDao pdfDao;

    @Override
    public File getHtml(String templatePath) {
        if (!StringUtils.isEmpty(templatePath)) {

        }
        return null;
    }

    @Override
    public File renderHtmlToPdf(File html) {
        return null;
    }
}
