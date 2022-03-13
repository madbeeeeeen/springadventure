package com.madbeen.thinking.in.spring.bean.lifecycle;

import com.madbeen.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;


/**
 * BeanDefinition 合并示例
 *
 * @author: madbeen
 * @date: 2022/03/13/12:28 AM
 */
public class MergedBeanDefinitionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 基于 XML 资源 BeanDefinitionReader 实现
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "META-INF/dependency-lookup-context.xml";
        Resource resource = new ClassPathResource(location);
        EncodedResource encodedResource = new EncodedResource(resource);

        int count = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println("已加载的 BeanDefinition 数量： " + count);

        User superUser = beanFactory.getBean("superUser", User.class);

        User user = beanFactory.getBean("user", User.class);

        System.out.println("superUser > " + superUser);
        System.out.println("user > " + user);
    }

}
