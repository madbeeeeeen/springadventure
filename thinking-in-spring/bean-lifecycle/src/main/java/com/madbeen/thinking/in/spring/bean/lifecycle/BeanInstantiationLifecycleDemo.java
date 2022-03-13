package com.madbeen.thinking.in.spring.bean.lifecycle;

import com.madbeen.thinking.in.spring.ioc.overview.domain.SuperUser;
import com.madbeen.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.ObjectUtils;


/**
 * BeanInstantiation 实例化示例
 *
 * @author: madbeen
 * @date: 2022/03/13/12:28 AM
 */
public class BeanInstantiationLifecycleDemo {

    public static void main(String[] args) {
        executeBeanFactory();

        System.out.println("=====================");

        executeApplicationContext();
    }

    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 方法1 添加 BeanPostProcessor 实例
//        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 方法2 添加 BeanPostProcessor 实例 将MyInstantiationAwareBeanPostProcessor作为一个Bean 注册

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

    public static void executeApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        // 基于 XML 资源 BeanDefinitionReader 实现
        String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        applicationContext.setConfigLocations(locations);

        applicationContext.refresh();

        UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);
        System.out.println("userHolder > " + userHolder);

        User user = applicationContext.getBean("user", User.class);
        System.out.println("user > " + user);

        applicationContext.close();
    }


}

