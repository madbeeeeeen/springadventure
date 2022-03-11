package com.madbeen.thinking.in.spring.denpendency.injection;

import com.madbeen.thinking.in.spring.ioc.overview.domain.User;

/**
 * {@link User} Holder
 *
 * @author: madbeen
 * @date: 2022/03/11/11:34 PM
 */
public class UserHolder {

    private User user;

    public User getUser() {
        return user;
    }

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
