package com.ternence.spring.skills.tomcat;

import org.junit.Test;

import java.io.File;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/6/9 20:48
 */
public class TomcatServerStarterTest {


    @Test
    public void testObtainCurrentPath() {
        String docBase = "src/main/webapp/";
        System.out.println(new File(docBase).getAbsolutePath());
    }
}