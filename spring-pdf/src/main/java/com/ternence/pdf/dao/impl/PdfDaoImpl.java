package com.ternence.pdf.dao.impl;

import com.ternence.pdf.dao.PdfDao;
import com.ternence.pdf.entity.BasicMessageEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * create by 陶江航 at 2017/9/18 22:55
 *
 * @version 1.0
 * @description PDF dao的实现类
 */
@Repository
public class PdfDaoImpl implements PdfDao {

    @Override
    public BasicMessageEntity queryBasicMessage() {
        BasicMessageEntity entity = new BasicMessageEntity();
        entity.setUuid(UUID.randomUUID().toString());
        entity.setAddress("广东省深圳市南山区前海路2567号");
        entity.setEmail("1312058405@qq.com");
        entity.setTelphone("18798624470");
        entity.setCreditCode("UF7845-34298777634XV");
        return entity;
    }
}
