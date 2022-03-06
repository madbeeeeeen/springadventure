package com.madbeen.thinking.in.spring.ioc.overview.domain;

import com.madbeen.thinking.in.spring.ioc.overview.annotation.Super;

/**
 * @author: madbeen
 * @date: 2022/03/06/8:22 PM
 */
@Super
public class SuperUser extends User {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
