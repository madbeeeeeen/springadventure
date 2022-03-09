package com.madbeen.thinking.in.spring.bean.factory;

import com.madbeen.thinking.in.spring.ioc.overview.domain.User;

/**
 * {@link User} 工厂类
 *
 * @author: madbeen
 * @date: 2022/03/09/8:43 PM
 */
public interface UserFactory {

    default User factoryCreateUser()  {
        return User.createUser();
    }

}
