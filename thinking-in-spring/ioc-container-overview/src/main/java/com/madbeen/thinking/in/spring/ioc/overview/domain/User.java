package com.madbeen.thinking.in.spring.ioc.overview.domain;

import java.util.StringJoiner;

/**
 * @author: madbeen
 * @date: 2022/03/06/7:50 PM
 */
public class User {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }

    public static User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Anno madbeen");
        return user;
    }
}
