package com.madbeen.thinking.in.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigBeanDefinitionParser;

/**
 * 注解 BeanDefinition 解析示例
 *
 * @author: madbeen
 * @date: 2022/03/12/11:08 PM
 */
public class AnnotatedBeanDefinitionParsingDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 基于 Java 注解的 AnnotatedBeanDefinitionReader 的实例
        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);

        int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();

        // 注册当前类（非 @Component class）
        // 普通 Class 作为 Component 注册到 IoC 容器后，名称为 annotatedBeanDefinitionParsingDemo
        // Bean 名称来自于 BeanNameGenerator，注解实现 AnnotatedBeanNameGenerator
        annotatedBeanDefinitionReader.register(AnnotatedBeanDefinitionParsingDemo.class);
        int beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();

        System.out.printf("注册前 BeanFactory 里BeanDefinition 数量：%d, 注册后：%d%n", beanDefinitionCountBefore, beanDefinitionCountAfter);

        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

        AnnotatedBeanDefinitionParsingDemo annotatedBeanDefinitionParsingDemo = beanFactory.getBean("annotatedBeanDefinitionParsingDemo", AnnotatedBeanDefinitionParsingDemo.class);
        System.out.println(annotatedBeanDefinitionParsingDemo);
    }
}
