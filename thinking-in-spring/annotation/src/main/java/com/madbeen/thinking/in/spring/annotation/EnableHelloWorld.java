package com.madbeen.thinking.in.spring.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 激活HelloWorld模块
 *
 * @author: madbeen
 * @date: 2022/03/19/4:43 PM
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
// 通过 @Import 导入具体实现
// 方法一：通过ConfigurationClass 实现
//@Import(HelloWorldConfiguration.class)
// 方法二：通过 ImportSelector 接口实现
//@Import(HelloWorldImportSelector.class)
// 方法三：通过 ImportBeanDefinitionRegistrar
@Import(HelloWorldBeanDefinitionRegistrar.class)
public @interface EnableHelloWorld {
}
