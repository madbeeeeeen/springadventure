package com.madbeen.thinking.in.spring.denpendency.lookup;

import com.madbeen.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @author: madbeen
 * @date: 2022/03/10/8:41 PM
 */

public class ObjectProviderDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext();
         // 注册 Configuration Class 配置类
        app.register(ObjectProviderDemo.class);

        // 启动 Spring 上下文
        app.refresh();

        lookupByObjectProvider(app);

        lookupIfAvailable(app);

        lookupByStreamOp(app);

        // 显示关闭 Spring 应用上下文
        app.close();
    }

    public static void lookupByStreamOp(ApplicationContext applicationContext) {
        ObjectProvider<String> stringObjectProvider = applicationContext.getBeanProvider(String.class);
        stringObjectProvider.stream()
                .forEach(System.out::println);
    }

    public static void lookupIfAvailable(ApplicationContext applicationContext) {
        ObjectProvider<User> userProvider = applicationContext.getBeanProvider(User.class);
        User user = userProvider.getIfAvailable(User::createUser);
        System.out.println(user);
    }

    @Bean
    @Primary
    public String helloWorld() {
        return "Hello World";
    }

    @Bean
    public String message() {
        return "Message";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext app) {
        ObjectProvider<String> beanProvider = app.getBeanProvider(String.class);
        String str = beanProvider.getObject();
        System.out.println(str);
    }
}
