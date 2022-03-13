package com.madbeen.thinking.in.spring.denpendency.injection;

import com.madbeen.thinking.in.spring.denpendency.injection.annotation.UserGroup;
import com.madbeen.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * {@link Qualifier} 注解依赖注入
 *
 * @author: madbeen
 * @date: 2022/03/12/3:10 PM
 */
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User superUser; // super user primary

    @Autowired
    @Qualifier("user")
    private User user;

    @Bean
    @Qualifier // 进行逻辑分组
    public User user1() {
        return createUser(7L);
    }

    @Bean
    @Qualifier
    public User user2() {
        return createUser(8L);
    }

    @Bean
    @UserGroup
    public User user3() {
        return createUser(9L);
    }

    @Bean
    @UserGroup
    public User user4() {
        return createUser(10L);
    }

    private static User createUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    @Autowired
    private Collection<User> allUsers; // 2 Beans


    @Autowired
    @Qualifier
    private Collection<User> qualifierUsers; // 2 Beans = user1 user2 -> user3 user4

    @Autowired
    @UserGroup
    private Collection<User> groupUsers;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class 配置类
        app.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(app);
        // 加载 XML 资源， 解析并生成 BeanDefinition
        String resourcePath = "classpath:META-INF/dependency-lookup-context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(resourcePath);

        // 启动 Spring 上下文
        app.refresh();

        QualifierAnnotationDependencyInjectionDemo annotationDependencyFieldInjectionDemo = app.getBean(QualifierAnnotationDependencyInjectionDemo.class);


        // @Autowired 字段关联
        User user = annotationDependencyFieldInjectionDemo.user;
        User superUser = annotationDependencyFieldInjectionDemo.superUser;

        System.out.println("user > " + user);
        System.out.println("superUser > " + superUser);

        Collection<User> allUsers = annotationDependencyFieldInjectionDemo.allUsers;
        Collection<User> qualifierUsers = annotationDependencyFieldInjectionDemo.qualifierUsers;

        System.out.println("allUsers > " + allUsers);
        System.out.println("qualifierUsers > " + qualifierUsers);

        Collection<User> groupUsers = annotationDependencyFieldInjectionDemo.groupUsers;
        System.out.println("groupUsers > " + groupUsers);

        // 显示关闭 Spring 应用上下文
        app.close();
    }
}
