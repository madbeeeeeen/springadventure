package com.madbeen.thinking.in.spring.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * 泛型 Api 示例
 *
 * @author: madbeen
 * @date: 2022/03/16/10:16 PM
 */
public class GenericApiDemo {

    public static void main(String[] args) {
        // 原生类型 primitive type: int, long, double, float
        Class intClass = int.class;

        // 数组类型 array type: int[] Object[]
        Class objArrayClass = Object[].class;

        // 原始类型 raw type: String
        Class rawClass = String.class;

        // 泛型参数类型 parameterized type:
        ParameterizedType parameterizedType = (ParameterizedType) ArrayList.class.getGenericSuperclass();

        // parameterizedType.getRawType() = java.util.AbstractList
        parameterizedType.getRawType();

        // 泛型类型变量 type variable
        System.out.println(parameterizedType.toString());

        // <E>
        Type[] typeVariables = parameterizedType.getActualTypeArguments();
        Stream.of(typeVariables)
                .map(TypeVariable.class::cast) // Type -> TypeVariable
                .forEach(System.out::println);
    }

}
