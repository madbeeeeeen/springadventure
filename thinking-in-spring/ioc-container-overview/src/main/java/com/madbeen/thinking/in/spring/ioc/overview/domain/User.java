package com.madbeen.thinking.in.spring.ioc.overview.domain;

import com.madbeen.thinking.in.spring.ioc.overview.enums.City;
import org.springframework.core.io.Resource;

import java.util.StringJoiner;

/**
 * @author: madbeen
 * @date: 2022/03/06/7:50 PM
 */
public class User {

    private Long id;

    private String name;

    private City city;

    private Resource configFileLocation;

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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Resource getConfigFileLocation() {
        return configFileLocation;
    }

    public void setConfigFileLocation(Resource configFileLocation) {
        this.configFileLocation = configFileLocation;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", configFileLocation=" + configFileLocation +
                '}';
    }

    public static User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Anno madbeen");
        return user;
    }
}
