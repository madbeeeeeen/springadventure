package com.madbeen.thinking.in.spring.bean.factory;

import com.madbeen.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * {@link com.madbeen.thinking.in.spring.ioc.overview.domain.User} Bean 的 {@link org.springframework.beans.factory.FactoryBean} 实现
 *
 * @author: madbeen
 * @date: 2022/03/09/8:53 PM
 */
public class UserFactoryBean implements FactoryBean {


    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
