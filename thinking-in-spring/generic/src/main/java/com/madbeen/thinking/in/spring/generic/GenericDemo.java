package com.madbeen.thinking.in.spring.generic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Java 泛型
 * @author: madbeen
 * @date: 2022/03/16/10:08 PM
 */
public class GenericDemo {

    public static void main(String[] args) {
        Collection<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");

        // 编译时错误
//        list.add(1);

        // 泛型擦写
        Collection tmp = list;
        // 通过编译
        tmp.add(1);

        System.out.println(list);
    }

}
