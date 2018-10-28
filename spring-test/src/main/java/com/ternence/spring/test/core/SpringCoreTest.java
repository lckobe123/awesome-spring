package com.ternence.spring.test.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author TAOJIANGHANG
 * @since 2018/10/11 11:25
 */
public class SpringCoreTest {
    private final static Logger logger = LoggerFactory.getLogger(SpringCoreTest.class);

    public static void main(String[] args) {
        SpringCoreTest coreTest = new SpringCoreTest();

        File file = coreTest.obtainResourceAsFile(ResourceUtils.CLASSPATH_URL_PREFIX + "logback.xml");

        logger.info("file is : {}", file);
    }

    @SuppressWarnings("SameParameterValue")
    private File obtainResourceAsFile(String relativePath) {

        ResourceLoader resourceLoader = new DefaultResourceLoader();

        Resource resource = resourceLoader.getResource(relativePath);
        try {
            return resource.getFile();
        } catch (IOException e) {
            logger.error("The file cannot be obtained by resource loader, may be it's" +
                    " not exists, make sure it's exists please. "
                    + e.getLocalizedMessage());
            return null;
        }
    }
}
