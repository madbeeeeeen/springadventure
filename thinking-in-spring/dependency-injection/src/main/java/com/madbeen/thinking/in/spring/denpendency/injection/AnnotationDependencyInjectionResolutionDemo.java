package com.madbeen.thinking.in.spring.denpendency.injection;

import com.madbeen.thinking.in.spring.denpendency.injection.annotation.InjectedUser;
import com.madbeen.thinking.in.spring.denpendency.injection.annotation.MyAutowired;
import com.madbeen.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.Annotation;
import java.util.*;

import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

/**
 * 注解驱动的依赖注入处理过程
 *
 * @author: madbeen
 * @date: 2022/03/12/3:10 PM
 */
public class AnnotationDependencyInjectionResolutionDemo {

    /**
     * 依赖查找 （处理）
     *  DependencyDescriptor ->
     *      必须 required=true
     *      实时注入 eager=true
     *      通过类型依赖查找 User.class
     *      字段名称 user
     *      是否首要 primary=true
     */
    @Autowired
    private User user;

    @Autowired
    private Map<String, User> users;

    @MyAutowired
    private Optional<User> optionalUser;

//    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    private static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
//        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        // 新注解处理 + 旧注解
//        Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(Arrays.asList(Autowired.class, InjectedUser.class));
//        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
//        return autowiredAnnotationBeanPostProcessor;
//    }

    @Bean()
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    private static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);
        return autowiredAnnotationBeanPostProcessor;
    }

    @InjectedUser
    private User myInjectedUser;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class 配置类
        app.register(AnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(app);
        // 加载 XML 资源， 解析并生成 BeanDefinition
        String resourcePath = "classpath:META-INF/dependency-lookup-context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(resourcePath);

        // 启动 Spring 上下文
        app.refresh();

        AnnotationDependencyInjectionResolutionDemo annotationDependencyFieldInjectionDemo = app.getBean(AnnotationDependencyInjectionResolutionDemo.class);

        // @Autowired 字段关联
        User superUser = annotationDependencyFieldInjectionDemo.user;
        System.out.println("superUser > " + superUser);

        Optional<User> optionalUser = annotationDependencyFieldInjectionDemo.optionalUser;
        System.out.println("optionalUser > " + optionalUser);

        User myInjectedUser = annotationDependencyFieldInjectionDemo.myInjectedUser;
        System.out.println("myInjectedUser > " + myInjectedUser);


        // 显示关闭 Spring 应用上下文
        app.close();
    }
}
