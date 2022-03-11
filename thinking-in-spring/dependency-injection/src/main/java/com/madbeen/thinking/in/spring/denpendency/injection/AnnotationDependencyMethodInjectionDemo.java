package com.madbeen.thinking.in.spring.denpendency.injection;

import com.madbeen.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于注解依赖方法注入示例
 *
 * @author: madbeen
 * @date: 2022/03/11/11:27 PM
 */
public class AnnotationDependencyMethodInjectionDemo {

    private UserHolder userHolder;

    private UserHolder userHolder2;

    @Autowired
    public void initUserHolder(UserHolder userHolder) {
        this.userHolder = userHolder;
    }

    @Resource
    public void initUserHolder2(UserHolder userHolder2) {
        this.userHolder2 = userHolder2;
    }

    @Bean
    public UserHolder userHolder(User user) {
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class 配置类
        app.register(AnnotationDependencyMethodInjectionDemo.class);

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(app);
        // 加载 XML 资源， 解析并生成 BeanDefinition
        String resourcePath = "classpath:META-INF/dependency-lookup-context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(resourcePath);

        // 启动 Spring 上下文
        app.refresh();

        AnnotationDependencyMethodInjectionDemo annotationDependencyFieldInjectionDemo = app.getBean(AnnotationDependencyMethodInjectionDemo.class);


        // @Autowired 字段关联
        UserHolder userHolder = annotationDependencyFieldInjectionDemo.userHolder;

        System.out.println(userHolder);
        System.out.println(annotationDependencyFieldInjectionDemo.userHolder2);

        // 显示关闭 Spring 应用上下文
        app.close();
    }



}
