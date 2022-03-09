package com.madbeen.thinking.in.spring.bean.definition;

import com.madbeen.thinking.in.spring.bean.factory.DefaultUserFactory;
import com.madbeen.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: madbeen
 * @date: 2022/03/09/9:58 PM
 */
@Configuration
public class BeanInitializationDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class 配置类
        applicationContext.register(BeanInitializationDemo.class);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 依赖查找 UserFactory
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);

        // 关闭 Spring 应用上下文
        applicationContext.close();

    }

    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }

}
