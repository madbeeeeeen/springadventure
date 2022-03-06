package com.madbeen.thinking.in.spring.ioc.overview.container;

import com.madbeen.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * IoC容器示例
 *
 * @author: madbeen
 * @date: 2022/03/06/9:53 PM
 */
public class BeanFactoryAsIoCContainerDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // XML 文件 ClassPath 路径
        String location = "classpath:/META-INF/dependency-injection-context.xml";
        // 加载配置
        int beanDefinitionCount = reader.loadBeanDefinitions(location);

        System.out.println("Bean 定义加载的数量：" + beanDefinitionCount);

        // 依赖查找：集合对象
        lookupByCollectionType(beanFactory);
    }

    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有集合对象" + users);
        }
    }
}
