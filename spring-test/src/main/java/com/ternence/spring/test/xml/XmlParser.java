package com.ternence.spring.test.xml;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/10/1 16:16
 */
public class XmlParser {
    /**
     * 验证并解析XML
     */
    public static void main(String[] args) {

        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        URL xsdUrl = XmlParser.class.getResource("/xml/students.xsd");
        String xsdPath = xsdUrl.getPath();
        Schema schema;
        try {
            schema = factory.newSchema(new File(xsdPath));
        } catch (SAXException e) {
            System.out.println("不合法的schema");
            e.printStackTrace();
            return;
        }
        Validator validator = schema.newValidator();
        URL xmlUrl = XmlParser.class.getResource("/xml/students.xml");
        String xmlPath = xmlUrl.getPath();
        try {
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (SAXException | IOException e) {
            System.out.println("验证xml异常");
            e.printStackTrace();
            return;
        }

        System.out.println("验证成功,开始解析...");

        try {
            parse(xmlPath);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    private static void parse(String systemId) throws ParserConfigurationException, SAXException, IOException {

        // step 1: 获得SAX解析器工厂实例
        SAXParserFactory factory = SAXParserFactory.newInstance();

        // step 2: 获得SAX解析器实例
        SAXParser parser = factory.newSAXParser();

        // step 3: 开始进行解析 传入待解析的文档的处理器
        parser.parse(new File(systemId), new SAXDefaultHandler());
    }

}
