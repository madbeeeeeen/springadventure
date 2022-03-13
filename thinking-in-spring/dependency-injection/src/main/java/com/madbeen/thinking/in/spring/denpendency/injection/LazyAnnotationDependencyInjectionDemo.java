package com.madbeen.thinking.in.spring.denpendency.injection;

import com.madbeen.thinking.in.spring.denpendency.injection.annotation.UserGroup;
import com.madbeen.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Set;

/**
 * {@link org.springframework.beans.factory.ObjectProvider} 延迟依赖注入
 *
 * @author: madbeen
 * @date: 2022/03/12/3:10 PM
 */
public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private User user; // 实时注入

    @Autowired
    private ObjectProvider<User> userObjectProvider; // 延迟注入

    @Autowired
    private ObjectFactory<Set<User>> userObjectFactory;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class 配置类
        app.register(LazyAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(app);
        // 加载 XML 资源， 解析并生成 BeanDefinition
        String resourcePath = "classpath:META-INF/dependency-lookup-context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(resourcePath);

        // 启动 Spring 上下文
        app.refresh();

        LazyAnnotationDependencyInjectionDemo annotationDependencyFieldInjectionDemo = app.getBean(LazyAnnotationDependencyInjectionDemo.class);


        // @Autowired 字段关联
        User superUser = annotationDependencyFieldInjectionDemo.user;
        System.out.println("superUser > " + superUser);


        ObjectProvider<User> userObjectProvider = annotationDependencyFieldInjectionDemo.userObjectProvider;
        System.out.println("userObjectProvider.getObject > " + userObjectProvider.getObject());

        userObjectProvider.forEach(System.out::println);

        ObjectFactory<Set<User>> userObjectFactory = annotationDependencyFieldInjectionDemo.userObjectFactory;
        System.out.println("userObjectFactory.getObject > " + userObjectFactory.getObject());

        // 显示关闭 Spring 应用上下文
        app.close();
    }
}
