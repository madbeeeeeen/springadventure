package com.madbeen.thinking.in.spring.annotation;

import org.springframework.context.annotation.*;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

/**
 * {@link org.springframework.context.annotation.Profile} 示例
 *
 * @author: madbeen
 * @date: 2022/03/19/5:03 PM
 *
 * @see Environment#getActiveProfiles()
 */
@Configuration
public class ProfileDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        context.register(ProfileDemo.class);
        // 获取 Environment 对象 (可配置的)
        ConfigurableEnvironment environment = context.getEnvironment();
        // 设置默认 profiles = {"odd"}
        environment.setDefaultProfiles("odd");
        // 设置活跃 profile
        environment.setActiveProfiles("even");

        // 启动 Spring 应用上下文
        context.refresh();

        Integer number = context.getBean("number", Integer.class);

        System.out.println(number);

        context.close();
    }

    @Bean(name = "number")
    @Profile("odd")
    public Integer odd() {
        return 1;
    }

    @Bean(name = "number")
//    @Profile("even")
    @Conditional(EventProfileConditional.class)
    public Integer even() {
        return 2;
    }
}
