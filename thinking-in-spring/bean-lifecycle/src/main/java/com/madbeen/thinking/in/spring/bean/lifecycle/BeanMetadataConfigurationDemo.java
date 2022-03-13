package com.madbeen.thinking.in.spring.bean.lifecycle;

import com.madbeen.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * Bean 元信息配置示例
 * @author: madbeen
 * @date: 2022/03/12/10:34 PM
 */
public class BeanMetadataConfigurationDemo {

    public static void main(String[] args) {

        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        // 实例化基于 Properties 资源的 BeanDefinitionReader
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(defaultListableBeanFactory);
        // 加载 Properties 资源
        Resource resource = new ClassPathResource("META-INF/user.properties");
        // 指定字符
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        int beanCount = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println("已加载 BeanDefinition 数量：" + beanCount);

        // 依赖查找
        User user = defaultListableBeanFactory.getBean("user", User.class);
        System.out.println(user);
    }

}
