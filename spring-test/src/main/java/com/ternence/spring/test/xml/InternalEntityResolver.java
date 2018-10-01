package com.ternence.spring.test.xml;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

/**
 * 查找xsd的EntityResolver,使用本地的xsd，不要去网上下载
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/10/1 18:29
 */
public class InternalEntityResolver implements EntityResolver {

    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
        URL xmlUrl = XmlParser.class.getResource("/xml/students.xsd");

        if (xmlUrl == null) {
            throw new IllegalStateException("请在classpath:xml/下放置students.xsd文件");
        }

        String xsdPath = xmlUrl.getPath();

        return new InputSource(new FileInputStream(new File(xsdPath)));
    }
}
