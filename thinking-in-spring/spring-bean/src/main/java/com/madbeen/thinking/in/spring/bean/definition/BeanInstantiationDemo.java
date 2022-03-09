package com.madbeen.thinking.in.spring.bean.definition;

import com.madbeen.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 实例化实例
 *
 * @author: madbeen
 * @date: 2022/03/09/8:37 PM
 */
public class BeanInstantiationDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/bean-instantiation-context.xml");
        User userByStaticMethod = beanFactory.getBean("user-by-static-method", User.class);
        System.out.println(userByStaticMethod);

        User beanByInstanceMethod = beanFactory.getBean("user-by-instance-method", User.class);
        System.out.println(beanByInstanceMethod);

        System.out.println(userByStaticMethod == beanByInstanceMethod);

        User beanByFactoryBean = beanFactory.getBean("user-by-factory-bean", User.class);
        System.out.println(beanByFactoryBean);

        System.out.println(beanByFactoryBean == beanByInstanceMethod);

    }
}
