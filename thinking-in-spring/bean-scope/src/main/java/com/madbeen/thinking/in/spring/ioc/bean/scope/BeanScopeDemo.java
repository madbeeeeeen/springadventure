package com.madbeen.thinking.in.spring.ioc.bean.scope;

import com.madbeen.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;

/**
 * Bean 作用域示例
 *
 * @author: madbeen
 * @date: 2022/03/12/7:22 PM
 */
public class BeanScopeDemo implements DisposableBean {

    @Bean
    public static User singletonUser() {
        return createUser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static User prototypeUser() {
        return createUser();
    }

    @Bean
    public static User singletonUser1() {
        return createUser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static User prototypeUser1() {
        return createUser();
    }



    public static User createUser() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    @Autowired
    private User singletonUser;


    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser1;


    @Autowired
    private User prototypeUser;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser2;

    @Autowired
    private Map<String, User> users;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory; // Resolvable Dependency

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        applicationContext.register(BeanScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(configurableListableBeanFactory -> {
            configurableListableBeanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    System.out.printf("%s Bean 名称: %s 在初始化后回调...%n", bean.getClass().getName(), beanName);
                    return bean;
                }
            });
        });

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        User singletonUser = applicationContext.getBean("singletonUser", User.class);
        User prototypeUser = applicationContext.getBean("prototypeUser", User.class);

        System.out.println(singletonUser);
        System.out.println(prototypeUser);
        System.out.println(singletonUser == prototypeUser);

        scopedBeansByLookup(applicationContext);

        System.out.println(singletonUser);
        System.out.println(prototypeUser);


        scopedBeansByInjection(applicationContext);


        // 关闭 Spring 应用上下文
        applicationContext.close();
    }

    public static void scopedBeansByLookup(AnnotationConfigApplicationContext applicationContext) {

        for (int i = 0; i < 3; i++) {
            User singletonUser = applicationContext.getBean("singletonUser", User.class);
            System.out.println("singletonUser = " + singletonUser);

            User prototypeUser = applicationContext.getBean("prototypeUser", User.class);
            System.out.println("prototypeUser = " + prototypeUser);
        }

    }

    public static void scopedBeansByInjection(AnnotationConfigApplicationContext applicationContext) {
        BeanScopeDemo bean = applicationContext.getBean(BeanScopeDemo.class);
        User singletonUser = bean.singletonUser;
        System.out.println("singletonUser = " + singletonUser);

        User singletonUser1 = bean.singletonUser1;
        System.out.println("singletonUser1 = " + singletonUser1);

        User prototypeUser = bean.prototypeUser;
        System.out.println("prototypeUser = " + prototypeUser);

        User prototypeUser1 = bean.prototypeUser1;
        System.out.println("prototypeUser1 = " + prototypeUser1);

        User prototypeUser2 = bean.prototypeUser2;
        System.out.println("prototypeUser2 = " + prototypeUser2);

        Map<String, User> users = bean.users;
        System.out.println("users = " + users);
    }

    @Override
    public void destroy() throws Exception {

        System.out.println("当前 BeanScopeDemo Bean 正在销毁中。。。");

        this.prototypeUser.preDestroyMethod();

        for (Map.Entry<String, User> entry : users.entrySet()) {
            String beanNam = entry.getKey();
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanNam);
            if (beanDefinition.isPrototype()) {
                User value = entry.getValue();
                value.preDestroyMethod();
            }
        }

        System.out.println("当前 BeanScopeDemo Bean 正在销毁完成。。。");

    }
}
