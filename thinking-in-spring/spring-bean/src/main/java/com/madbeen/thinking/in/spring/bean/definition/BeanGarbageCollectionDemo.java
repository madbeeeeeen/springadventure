package com.madbeen.thinking.in.spring.bean.definition;

import com.madbeen.thinking.in.spring.bean.factory.DefaultUserFactory;
import com.madbeen.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Bean GC 实例
 *
 * @author: madbeen
 * @date: 2022/03/09/10:51 PM
 */
@Configuration
public class BeanGarbageCollectionDemo {

    public static void main(String[] args) throws InterruptedException {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class 配置类
        applicationContext.register(BeanGarbageCollectionDemo.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();
        // 依赖查找 UserFactory
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        // 关闭 Spring 应用上下文
        applicationContext.close();
        TimeUnit.SECONDS.sleep(5);
        // 强制触发 GC
        System.gc();
        TimeUnit.SECONDS.sleep(5);
    }

    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }

}
