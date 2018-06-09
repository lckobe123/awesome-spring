package com.ternence.spring.skills.tomcat.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * 欢迎页面的servlet
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/6/9 20:57
 */
public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer w = resp.getWriter();
        w.write("Embedded Tomcat servlet.\n");
        w.flush();
        w.close();
    }
}
