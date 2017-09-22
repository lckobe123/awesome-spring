package com.ternence.pdf.dao;

import com.ternence.pdf.entity.BasicMessageEntity;

/**
 * create by 陶江航 at 2017/9/18 22:51
 *
 * @version 1.0
 * @description 模拟数据库查询PDF渲染所需要的数据
 */
public interface PdfDao {

    BasicMessageEntity queryBasicMessage();

}
