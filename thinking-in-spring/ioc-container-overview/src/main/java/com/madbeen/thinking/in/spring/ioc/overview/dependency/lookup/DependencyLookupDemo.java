package com.madbeen.thinking.in.spring.ioc.overview.dependency.lookup;

import com.madbeen.thinking.in.spring.ioc.overview.annotation.Super;
import com.madbeen.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找
 * 1. 通过名称
 * 2. 通过类型
 *
 * @author: madbeen
 * @date: 2022/03/06/7:47 PM
 */
public class DependencyLookupDemo {

    public static void main(String[] args) {
        /*
            1. 配置 XML 配置文件
            2. 启动 Spring 应用上下文
         */
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-lookup-context.xml");

        // 按照名称查找
        lookupInRealTime(beanFactory);
        lookupInLazy(beanFactory);
        // 按照类型查找
        lookupByType(beanFactory);
        // 按照类型查找集合对象
        lookupByCollectionType(beanFactory);
        // 按注解查找
        lookupByAnnotation(beanFactory);
    }

    private static void lookupInRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找：" + user);
    }

    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找：" + user);
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("实时查找[类型]：" + user);
    }

    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有集合对象" + users);
        }
    }


    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("查找标注 @Super 所有的 User 集合对象：" + users);
        }
    }
}
