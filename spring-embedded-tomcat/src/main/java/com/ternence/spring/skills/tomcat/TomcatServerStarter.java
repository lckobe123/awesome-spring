package com.ternence.spring.skills.tomcat;

import com.ternence.spring.skills.tomcat.servlet.WelcomeServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;

/**
 * Tomcat服务器的启动入口
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/6/9 19:52
 */
public class TomcatServerStarter {

    public static void main(String[] args) throws IOException, LifecycleException, ServletException {
        startSpringBootStyleContainer();
    }

    /**
     * 一个既具有servlet能力，又可以提供web服务的容器
     */
    public static void startCompletedContainer() throws ServletException, LifecycleException {
        Tomcat tomcat = new Tomcat();
        String webDocBase = "src/main/webapp";
        Context context = tomcat.addWebapp("", new File(webDocBase).getAbsolutePath());
        Tomcat.addServlet(context, "embedded", new WelcomeServlet());
        context.addServletMappingDecoded("/embedded/*", "embedded");
        tomcat.start();
        tomcat.getServer().await();
    }


    /**
     * 一个既具有servlet能力，又可以提供web服务的容器
     * <p>
     * 但是他的文件和目录组织方式是类似spring boot的风格
     */
    public static void startSpringBootStyleContainer() throws ServletException, LifecycleException {
        Tomcat tomcat = new Tomcat();
        String webDocBase =// new File("src/main/resources/static").getAbsolutePath();
        TomcatServerStarter.class.getClassLoader().getResource("static").getPath();
        Context context = tomcat.addWebapp("", new File(webDocBase).getAbsolutePath());
        Tomcat.addServlet(context, "embedded", new WelcomeServlet());
        context.addServletMappingDecoded("/embedded/*", "embedded");
        tomcat.start();
        tomcat.getServer().await();
    }

    /**
     * 这次启动的tomcat只是jsp页面的解析和访问
     * <p>
     * 一个问题：jsper编译jsp出现的class在哪里？还是不需要了呢？直接输出html？
     */
    private static void startContainerWithJspSupport() throws ServletException, LifecycleException {
        Tomcat tomcat = new Tomcat();
        String webDocBase = "src/main/webapp";
        tomcat.addWebapp("", new File(webDocBase).getAbsolutePath());
        tomcat.start();
        tomcat.getServer().await();
    }

    /**
     * 启动一个标准的tomcat容器，支持servlet 的访问
     */
    private static void startContainer() throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        //tomcat.setPort(8082);
        //应用部署环境信息的描述对象,contextPath用于context映射，docBase用于context和静态资源文件的基础目录
        Context context = tomcat.addContext("", new File(".").getAbsolutePath());
        Tomcat.addServlet(context, "embedded", new WelcomeServlet());
        context.addServletMappingDecoded("/", "embedded");
        tomcat.start();
        tomcat.getServer().await();//阻塞，以免主线程停止，服务就自动停止了
    }
}
