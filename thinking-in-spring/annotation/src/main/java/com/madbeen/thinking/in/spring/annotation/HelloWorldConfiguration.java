package com.madbeen.thinking.in.spring.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * HelloWorldConfiguration Class
 *
 * @author: madbeen
 * @date: 2022/03/19/4:45 PM
 */
@Configuration
public class HelloWorldConfiguration {

    @Bean
    public String helloWorld() {
        return "HelloWorld";
    }

}
