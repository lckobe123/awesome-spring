package com.ternence.spring.test.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/10/1 18:27
 */
public class SAXDefaultHandler extends DefaultHandler {

    @Override
    public void startDocument() throws SAXException {

        System.out.println("start document : ");
    }

    @Override
    public void endDocument() throws SAXException {

        System.out.println("end document : ");
    }

    /**
     * 元素的开始节点
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        System.out.println("start element : " + uri + "," + localName + "," + qName);
    }

    /**
     * 元素的结束节点
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        System.out.println("end element : " + uri + "," + localName + "," + qName);
    }

    /**
     * ch就是xml的内容
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        System.out.println("end element : " + start + "," + length);

    }
}
