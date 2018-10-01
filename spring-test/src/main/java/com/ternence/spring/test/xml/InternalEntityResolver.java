package com.ternence.spring.test.xml;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * 避免联网下载 xsd 资源
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/10/1 18:29
 */
public class InternalEntityResolver implements EntityResolver {
    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {

        System.out.println("调用EntityResolver");

        return null;
    }
}
