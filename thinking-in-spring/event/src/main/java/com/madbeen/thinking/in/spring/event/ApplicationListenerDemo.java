package com.madbeen.thinking.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * {@link org.springframework.context.ApplicationListener} 示例
 *
 * @author: madbeen
 * @date: 2022/03/17/10:47 PM
 */
@EnableAsync
public class ApplicationListenerDemo implements ApplicationEventPublisherAware {
    public static void main(String[] args) {
//        GenericApplicationContext applicationContext = new GenericApplicationContext();

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 将引导类 ApplicationListenerDemo 作为 Configuration Class
        applicationContext.register(ApplicationListenerDemo.class);

        // 方法一：基于Spring 接口：向Spring 上下文呢注册事件
        // a方法: 基于 ConfigurableApplicationContext API实现
        applicationContext.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent applicationEvent) {
                println("ApplicationListener - 收到事件: " + applicationEvent);
            }
        });

        // b方法: 基于 ApplicationListener 注册为 Spring Bean
        applicationContext.register(MyApplicationListener.class);

        // 方法二：基于 Spring 注解 @EventListener

        // ApplicationEventMulticaster

        // 启动应用上下文
        applicationContext.refresh();

        // 启动应用上下文
        applicationContext.start();

        // 关闭应用上下文
        applicationContext.close();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new ApplicationEvent("Hello World") {

        });

        applicationEventPublisher.publishEvent("Hello World");
    }

    static class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
        @Override
        public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
            println("MyApplicationListener - 收到事件: " + contextRefreshedEvent);
        }
    }

    @EventListener
    @Order(1)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        println("@EventListener [onApplicationEvent] 收到 ContextRefreshedEvent：" + event);
    }

    @EventListener
    @Order(2)
    public void onApplicationEvent1(ContextRefreshedEvent event) {
        println("@EventListener [onApplicationEvent1] 收到 ContextRefreshedEvent：" + event);
    }

    @EventListener
    @Async
    public void onApplicationEventAsync(ContextRefreshedEvent event) {
        println("@EventListener [@Async] 收到 ContextRefreshedEven：" + event);
    }

    @EventListener
    public void onApplicationEvent(ContextStartedEvent event) {
        println("@EventListener 收到 ContextStartedEvent：" + event);
    }

    @EventListener
    public void onApplicationEvent(ContextClosedEvent event) {
        println("@EventListener 收到 ContextClosedEvent：" + event);
    }

    private static void println(Object printable) {
        System.out.printf("线程[%s] : %s%n", Thread.currentThread().getName(), printable);
    }
}
