package com.ternence.spring.test.xml;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.xml.SimpleSaxErrorHandler;
import org.springframework.util.xml.XmlValidationModeDetector;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
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
     * JAXP attribute used to configure the schema language for validation.
     */
    private static final String SCHEMA_LANGUAGE_ATTRIBUTE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

    /**
     * JAXP attribute value indicating the XSD schema language.
     */
    private static final String XSD_SCHEMA_LANGUAGE = "http://www.w3.org/2001/XMLSchema";

    /**
     * 验证并解析XML
     */
    public static void main(String[] args) throws Exception {
        XmlParser xmlParser = new XmlParser();

        Resource resource = new ClassPathResource("xml/students.xml");

        InputSource inputSource = new InputSource(resource.getInputStream());

        //无需验证的加载Document的方法
        /*Document document = xmlParser.loadDocument(inputSource, null,
                new SimpleSaxErrorHandler(LogFactory.getLog(XmlParser.class)),
                XmlValidationModeDetector.VALIDATION_NONE, false);*/

        //验证的加载Document的方法
        Document document = xmlParser.loadDocument(inputSource,  new InternalEntityResolver(),
                new SimpleSaxErrorHandler(LogFactory.getLog(XmlParser.class)),
                XmlValidationModeDetector.VALIDATION_XSD, false);

        System.out.println(document);
    }


    private static void parseBySax() {
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


    public Document loadDocument(InputSource inputSource, EntityResolver entityResolver,
                                 ErrorHandler errorHandler, int validationMode, boolean namespaceAware) throws Exception {

        DocumentBuilderFactory factory = createDocumentBuilderFactory(validationMode, namespaceAware);
        DocumentBuilder builder = createDocumentBuilder(factory, entityResolver, errorHandler);
        return builder.parse(inputSource);
    }

    /**
     * Create the {@link DocumentBuilderFactory} instance.
     *
     * @param validationMode the type of validation: {@link XmlValidationModeDetector#VALIDATION_DTD DTD}
     *                       or {@link XmlValidationModeDetector#VALIDATION_XSD XSD})
     * @param namespaceAware whether the returned factory is to provide support for XML namespaces
     * @return the JAXP DocumentBuilderFactory
     * @throws ParserConfigurationException if we failed to build a proper DocumentBuilderFactory
     */
    private DocumentBuilderFactory createDocumentBuilderFactory(int validationMode, boolean namespaceAware)
            throws ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(namespaceAware);

        if (validationMode != XmlValidationModeDetector.VALIDATION_NONE) {
            factory.setValidating(true);
            if (validationMode == XmlValidationModeDetector.VALIDATION_XSD) {
                // Enforce namespace aware for XSD...
                factory.setNamespaceAware(true);
                try {
                    factory.setAttribute(SCHEMA_LANGUAGE_ATTRIBUTE, XSD_SCHEMA_LANGUAGE);
                } catch (IllegalArgumentException ex) {
                    ParserConfigurationException pcex = new ParserConfigurationException(
                            "Unable to validate using XSD: Your JAXP provider [" + factory +
                                    "] does not support XML Schema. Are you running on Java 1.4 with Apache Crimson? " +
                                    "Upgrade to Apache Xerces (or Java 1.5) for full XSD support.");
                    pcex.initCause(ex);
                    throw pcex;
                }
            }
        }

        return factory;
    }

    /**
     * Create a JAXP DocumentBuilder that this bean definition reader
     * will use for parsing XML documents. Can be overridden in subclasses,
     * adding further initialization of the builder.
     *
     * @param factory        the JAXP DocumentBuilderFactory that the DocumentBuilder
     *                       should be created with
     * @param entityResolver the SAX EntityResolver to use
     * @param errorHandler   the SAX ErrorHandler to use
     * @return the JAXP DocumentBuilder
     * @throws ParserConfigurationException if thrown by JAXP methods
     */
    private DocumentBuilder createDocumentBuilder(
            DocumentBuilderFactory factory, EntityResolver entityResolver, ErrorHandler errorHandler)
            throws ParserConfigurationException {

        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        if (entityResolver != null) {
            docBuilder.setEntityResolver(entityResolver);
        }
        if (errorHandler != null) {
            docBuilder.setErrorHandler(errorHandler);
        }
        return docBuilder;
    }

}
