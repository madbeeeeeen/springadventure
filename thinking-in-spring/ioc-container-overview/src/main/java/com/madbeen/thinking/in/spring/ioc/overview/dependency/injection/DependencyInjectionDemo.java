package com.madbeen.thinking.in.spring.ioc.overview.dependency.injection;

import com.madbeen.thinking.in.spring.ioc.overview.domain.User;
import com.madbeen.thinking.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入
 * 1. 通过名称
 * 2. 通过类型
 *
 * @author: madbeen
 * @date: 2022/03/06/7:47 PM
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        /*
            1. 配置 XML 配置文件
            2. 启动 Spring 应用上下文
         */
        ApplicationContext beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");

        // 依赖来源一：自定义 Bean
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);

        // 依赖来源二：依赖注入 （内建依赖）
        BeanFactory innerBeanFactory = userRepository.getBeanFactory();

        ObjectFactory<ApplicationContext> userObjectFactory = userRepository.getObjectFactory();
        System.out.println(userObjectFactory.getObject() == beanFactory);

        // 依赖来源三：容器内建 Bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("获取Environment 类型的 Bean：" + environment);

        realIoCContainer(innerBeanFactory, beanFactory);
    }

    private static void realIoCContainer(BeanFactory innerBeanFactory, ApplicationContext beanFactory) {
        System.out.println("innerBeanFactory == beanFactory：" + (innerBeanFactory == beanFactory));

        // ConfigurableApplicationContext <- ApplicationContext <- BeanFactory

        // ConfigurableApplicationContext#getBeanFactory

        // ApplicationContext is BeanFactory
    }

}
