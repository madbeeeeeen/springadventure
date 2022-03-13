package com.madbeen.thinking.in.spring.bean.lifecycle;

import com.madbeen.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * BeanInitialization 初始化示例
 *
 * @author: madbeen
 * @date: 2022/03/13/12:28 AM
 */
public class BeanIntializationLifecycleDemo {

    public static void main(String[] args) {
        executeBeanFactory();
    }

    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 方法1 添加 BeanPostProcessor 实例
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

        // 基于 XML 资源 BeanDefinitionReader 实现
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        int count = beanDefinitionReader.loadBeanDefinitions(locations);
        System.out.println("已加载的 BeanDefinition 数量： " + count);

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println("userHolder > " + userHolder);

        User user = beanFactory.getBean("user", User.class);
        System.out.println("user > " + user);
    }

}

