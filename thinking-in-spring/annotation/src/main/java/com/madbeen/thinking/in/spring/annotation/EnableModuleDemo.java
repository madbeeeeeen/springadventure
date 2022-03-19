package com.madbeen.thinking.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Enable 模块示例
 * @author: madbeen
 * @date: 2022/03/19/4:42 PM
 */
@EnableHelloWorld
public class EnableModuleDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        context.register(EnableModuleDemo.class);
        // 启动 Spring 应用上下文
        context.refresh();

        String helloWorld = context.getBean("helloWorld", String.class);

        System.out.println(helloWorld);

        context.close();
    }

}
