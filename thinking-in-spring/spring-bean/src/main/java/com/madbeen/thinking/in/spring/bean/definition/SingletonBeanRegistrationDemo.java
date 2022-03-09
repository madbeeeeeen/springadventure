package com.madbeen.thinking.in.spring.bean.definition;

import com.madbeen.thinking.in.spring.bean.factory.DefaultUserFactory;
import com.madbeen.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

/**
 * @author: madbeen
 * @date: 2022/03/09/11:03 PM
 */
public class SingletonBeanRegistrationDemo {
    public static void main(String[] args) throws InterruptedException {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 创建外部对象
        DefaultUserFactory defaultUserFactory = new DefaultUserFactory();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        // 注册 外部单例对象
        beanFactory.registerSingleton("userFactoryByOuterSingleton", defaultUserFactory);
        // 启动 Spring 应用上下文
        applicationContext.refresh();
        // 依赖查找 UserFactory
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);

        UserFactory userFactoryByLookup = beanFactory.getBean("userFactoryByOuterSingleton", UserFactory.class);
        System.out.println("userFactoryByLookup == defaultUserFactory  =>  " + (userFactoryByLookup == defaultUserFactory));

        // 关闭 Spring 应用上下文
        applicationContext.close();
    }
}
