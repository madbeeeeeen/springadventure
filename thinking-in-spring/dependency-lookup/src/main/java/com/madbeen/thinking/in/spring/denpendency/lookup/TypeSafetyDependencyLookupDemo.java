package com.madbeen.thinking.in.spring.denpendency.lookup;

import com.madbeen.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 类型安全的依赖查找示例
 * @author: madbeen
 * @date: 2022/03/10/10:28 PM
 */
public class TypeSafetyDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class 配置类
        app.register(TypeSafetyDependencyLookupDemo.class);
        // 启动 Spring 上下文
        app.refresh();

        // BeanFactory#getBean 的安全性
        displayBeanFactoryGetBean(app);
        // ObjectFactory#getObject 的安全性
        displayObjectFactoryGetBean(app);
        // ObjectProviderIfAvailable#getIfAvaailable 安全性
        displayObjectProviderGetIfAvailable(app);

        // ListableBeanFactory#getBeansOfType 方法安全性
        displayListableBeanFactory(app);
        // ObjectProvider#stream 方法安全性
        displayObjectProviderStreamOps(app);

        app.close();
    }

    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
        printBeansException("displayObjectProviderStreamOps", () -> {
            ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
            userObjectProvider.stream()
                    .forEach(System.out::println);
        });
    }

    private static void displayListableBeanFactory(ListableBeanFactory applicationContext) {
        printBeansException("displayListableBeanFactory", () -> {
            applicationContext.getBeansOfType(User.class);
        });
    }

    private static void displayObjectProviderGetIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        printBeansException("displayObjectProviderGetIfAvailable", () -> {
            ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
            userObjectProvider.getIfAvailable();
        });
    }

    public static void displayObjectFactoryGetBean(ApplicationContext applicationContext) {
        printBeansException("displayObjectFactoryGetBean", () -> {
            ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
            userObjectProvider.getObject();
        });
    }

    public static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printBeansException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));
    }

    public static void printBeansException(String message, Runnable runnable) {
        System.err.println("==================================================");
        System.err.println("source from " + message);
        try {
            runnable.run();
        } catch (BeansException ex) {
            ex.printStackTrace();
        }
    }

}
