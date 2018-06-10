package com.ternence.boot.mvc.eureka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * index controller
 *
 * @author Ternence
 * @version 1.0
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    private final static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/node/{server}")
    public Object requestServiceNode(@PathVariable("server") String server) {

        LOGGER.info("请求服务{}的注册信息", server);

        return "node";
    }

}
