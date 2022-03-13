package com.madbeen.thinking.in.spring.bean.lifecycle;

import com.madbeen.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * @author: madbeen
 * @date: 2022/03/13/3:51 PM
 */
public class UserHolder implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware {

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
}
