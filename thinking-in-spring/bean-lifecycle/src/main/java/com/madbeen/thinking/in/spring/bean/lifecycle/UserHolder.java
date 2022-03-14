package com.madbeen.thinking.in.spring.bean.lifecycle;

import com.madbeen.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author: madbeen
 * @date: 2022/03/13/3:51 PM
 */
public class UserHolder implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware,
        EnvironmentAware, InitializingBean, SmartInitializingSingleton, DisposableBean {

    private final User user;

    private Integer number;

    private String desc;

    private ClassLoader classLoader;

    private BeanFactory beanFactory;

    private String beanName;

    private Environment environment;

    public UserHolder(User user) {
        this.user = user;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public String getBeanName() {
        return beanName;
    }

    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", desc='" + desc + '\'' +
//                ", classLoader=" + classLoader +
//                ", beanFactory=" + beanFactory +
                ", beanName='" + beanName + '\'' +
                ", environment=" + environment +
                '}';
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String s) {
        this.beanName = s;
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * 依赖于注解驱动
     * 当前场景：BeanFactory
     */
    @PostConstruct
    public void initPostConstruct() {
        System.out.println("modified by [initPostConstruct @PostConstruct] desc v4: The user holder");
        this.desc = "modified by [initPostConstruct @PostConstruct] desc v4: The user holder";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("modified by [afterPropertiesSet InitializingBean] desc v5: The user holder");
        this.desc = "modified by [afterPropertiesSet InitializingBean] desc v5: The user holder";
    }


    public void init() {
        System.out.println("modified by [init customize] desc v6: The user holder");
        this.desc = "modified by [init customize] desc v6: The user holder";
    }

    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("modified by [afterSingletonsInstantiated SmartInitializingSingleton] desc v8: The user holder");
        this.desc = "modified by [afterSingletonsInstantiated SmartInitializingSingleton] desc v8: The user holder";
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("modified by [preDestroy @PreDestroy] desc v10: The user holder");
        this.setDesc("modified by [preDestroy @PreDestroy] desc v10: The user holder");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("[destroy DisposableBean]");
        this.setDesc("[destroy DisposableBean]");
    }

    public void doDestroy() {
        System.out.println("[doDestroy customize]");
        this.setDesc("[doDestroy customize]");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("The user holder is finalizing");
    }
}
