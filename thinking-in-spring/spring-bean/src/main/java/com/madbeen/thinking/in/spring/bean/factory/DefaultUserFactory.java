package com.madbeen.thinking.in.spring.bean.factory;

import com.madbeen.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 默认实现
 *
 * @author: madbeen
 * @date: 2022/03/09/8:45 PM
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    @PostConstruct
    public void init() {
        System.out.println("Running DefaultUserFactory init method... (@PostConstruct)");
    }

    public void initUserFactory() {
        System.out.println("Running DefaultUserFactory initUserFactory method... ");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Running DefaultUserFactory afterPropertiesSet method... ");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("Running DefaultUserFactory preDestroy method... (@PreDestroy)");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Running DefaultUserFactory destroy method... ");
    }

    public void doDestroy() {
        System.out.println("Running DefaultUserFactory doDestroy method... ");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Running DefaultUserFactory finalize method... ");
    }
}
