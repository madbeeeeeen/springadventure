package com.madbeen.thinking.in.spring.bean.lifecycle;

import com.madbeen.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

import java.util.concurrent.TimeUnit;


/**
 * BeanLifecycleDemo Bean 生命周期示例
 *
 * @author: madbeen
 * @date: 2022/03/13/12:28 AM
 */
public class BeanLifecycleDemo {

    public static void main(String[] args) throws InterruptedException {
        executeBeanFactory();
    }

    private static void executeBeanFactory() throws InterruptedException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 方法1 添加 BeanPostProcessor 实例
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 添加 MyDestructionAwareBeanPostProcessor 执行销毁前回调
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        // 添加 CommonAnnotationBeanPostProcessor 解决 @PostConstruct @PreDestroy
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());

        // 基于 XML 资源 BeanDefinitionReader 实现
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        int count = beanDefinitionReader.loadBeanDefinitions(locations);
        System.out.println("已加载的 BeanDefinition 数量： " + count);

        // 显示执行 preInstantiateSingletons
        // SmartInitializingSingleton 通常在 SpringApplicationContext 场景使用
        // preInstantiateSingletons 将已注册的 BeanDefinition 初始化成Spring Bean
        beanFactory.preInstantiateSingletons();

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println("userHolder > " + userHolder);

        User user = beanFactory.getBean("user", User.class);
        System.out.println("user > " + user);

        // Bean 销毁
        beanFactory.destroyBean("userHolder", userHolder);
        // Bean 的销毁并不代表着 Bean 被 GC
        System.out.println("userHolder > " + userHolder);

        // 撤掉销毁 BeanFactory 里的单一对象
        beanFactory.destroySingleton("userHolder");

        userHolder = null;

        // 强制GC
        System.gc();

        TimeUnit.SECONDS.sleep(10);

        System.gc();
    }

}

