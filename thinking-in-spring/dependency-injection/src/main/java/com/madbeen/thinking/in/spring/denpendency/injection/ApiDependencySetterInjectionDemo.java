package com.madbeen.thinking.in.spring.denpendency.injection;

import com.madbeen.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Api 依赖 Setter 方法注入 Demo
 *
 * @author: madbeen
 * @date: 2022/03/11/11:27 PM
 */
public class ApiDependencySetterInjectionDemo {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext();

        // 注册 UserHolder 的 BeanDefinition
        BeanDefinition userHolderBeanDefinition = createUserHolderBeanDefinition();
        app.registerBeanDefinition("userHolder", userHolderBeanDefinition);

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(app);
        // 加载 XML 资源， 解析并生成 BeanDefinition
        String resourcePath = "classpath:META-INF/dependency-lookup-context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(resourcePath);

        // 启动 Spring 上下文
        app.refresh();

        // 依赖查找并创建 Bean
        UserHolder userHolder = app.getBean(UserHolder.class);

        System.out.println(userHolder);

        // 显示关闭 Spring 应用上下文
        app.close();
    }

    /**
     * 为 {@link UserHolder} 生成 {@link BeanDefinition}
     * @return
     */
    private static BeanDefinition createUserHolderBeanDefinition() {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        beanDefinitionBuilder.addPropertyReference("user", "superUser");

        return beanDefinitionBuilder.getBeanDefinition();
    }

//    @Bean
//    public UserHolder userHolder(User user) {
//        return new UserHolder(user);
//    }

}
