package com.madbeen.thinking.in.spring.ioc.overview.repository;

import com.madbeen.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

/**
 * 用户信息仓库
 * @author: madbeen
 * @date: 2022/03/06/8:40 PM
 */
public class UserRepository {
    // 自定义 Bean
    private Collection<User> users;
    // 内建非 Bean 对象 （依赖）
    private BeanFactory beanFactory;
    //
    private ObjectFactory<ApplicationContext> objectFactory;

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public ObjectFactory<ApplicationContext> getObjectFactory() {
        return objectFactory;
    }

    public void setObjectFactory(ObjectFactory<ApplicationContext> objectFactory) {
        this.objectFactory = objectFactory;
    }

    @Override
    public String toString() {
        return "UserRepository{" +
                "users=" + users +
                ", beanFactory=" + beanFactory +
                ", objectFactory=" + objectFactory +
                '}';
    }
}
