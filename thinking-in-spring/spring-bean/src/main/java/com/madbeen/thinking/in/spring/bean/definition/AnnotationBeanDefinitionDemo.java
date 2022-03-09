package com.madbeen.thinking.in.spring.bean.definition;

import com.madbeen.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 注解 BeanDefinition 示例
 *
 * @author: madbeen
 * @date: 2022/03/08/11:24 PM
 */
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class 配置类
        app.register(Config.class);

        // 1. 命名 Bean 的注册方式
        registerBeanDefinition(app, "jackson.madbeen", User.class);
        // 2. 非命名的注册方式
        registerBeanDefinition(app, User.class);

        // 启动 Spring 上下文
        app.refresh();

        // 1. 通过 @Bean 定义

        // 2. 通过 @Component 实现

        // 3. 通过 @Import 导入

        // 按照类型依赖查找
        System.out.println("Config 所有类型的 Beans ：" + app.getBeansOfType(Config.class));
        System.out.println("User 所有类型的 Beans ：" + app.getBeansOfType(User.class));


        // 显示关闭 Spring 应用上下文
        app.close();
    }

    /**
     * 命名 Bean 的注册方式
     * @param registry
     * @param beanName
     * @param beanClass
     */
    public static void registerBeanDefinition(BeanDefinitionRegistry registry, String beanName, Class<?> beanClass) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
        builder.addPropertyValue("id", 10L)
                .addPropertyValue("name", "jackson");

        // 如果 beanName 参数存在时
        if (StringUtils.hasText(beanName)) {
            // 注册 BeanDefinition
            registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
        } else {
            // 非命名方式
            BeanDefinitionReaderUtils.registerWithGeneratedName(builder.getBeanDefinition(), registry);
        }
    }

    /**
     * 非命名注册 Bean 方式
     *
     * @param registry
     * @param beanClass
     */
    public static void registerBeanDefinition(BeanDefinitionRegistry registry, Class<?> beanClass) {
        registerBeanDefinition(registry, null, beanClass);
    }

    @Component
    public static class Config {
        @Bean(name = {"user", "madbeen"})
        public User user() {
            User user = new User();
            user.setId(1L);
            user.setName("Anno madbeen");
            return user;
        }
    }

}
