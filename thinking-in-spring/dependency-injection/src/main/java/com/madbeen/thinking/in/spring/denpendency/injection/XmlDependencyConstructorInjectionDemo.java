package com.madbeen.thinking.in.spring.denpendency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Xml 资源的依赖 Constructor 方法注入 Demo
 *
 * @author: madbeen
 * @date: 2022/03/11/11:27 PM
 */
public class XmlDependencyConstructorInjectionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        // 加载 XML 资源， 解析并生成 BeanDefinition
        String resourcePath = "classpath:META-INF/dependency-constructor-injection.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(resourcePath);

        // 依赖查找并创建 Bean
        UserHolder userHolder = beanFactory.getBean(UserHolder.class);

        System.out.println(userHolder);
    }

}
