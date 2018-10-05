package com.ternence.spring.test.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/10/5 13:49
 */
@Service
public class StatisticService {

    @Autowired
    private UserService userService;

    public StatisticService() {
    }
}
