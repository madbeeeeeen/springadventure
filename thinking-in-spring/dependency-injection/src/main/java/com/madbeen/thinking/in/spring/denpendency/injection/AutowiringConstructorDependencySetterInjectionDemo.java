package com.madbeen.thinking.in.spring.denpendency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Autowiring "constructor" 依赖 Setter 方法注入示例
 *
 * @author: madbeen
 * @date: 2022/03/12/12:02 AM
 */
public class AutowiringConstructorDependencySetterInjectionDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        // 加载 XML 资源， 解析并生成 BeanDefinition
        String resourcePath = "classpath:META-INF/autowiring-dependency-constructor-injection.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(resourcePath);

        // 依赖查找并创建 Bean
        UserHolder userHolder = beanFactory.getBean(UserHolder.class);

        System.out.println(userHolder);
    }
}
