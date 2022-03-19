package com.madbeen.thinking.in.spring.generic;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * {@link org.springframework.core.GenericTypeResolver} 示例
 *
 * @author: madbeen
 * @date: 2022/03/16/10:33 PM
 */
public class GenericTypeResolverDemo {

    public static void main(String[] args) throws NoSuchMethodException {
        // String Comparable<String> 具体化
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, Comparable.class, "getString");
        // List
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getList");
        // List<String> 具体化
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getStringList");
        // 具备 ParameterizedType 返回， 否则返回 null

        Map<TypeVariable, Type> typeVariableMap = GenericTypeResolver.getTypeVariableMap(StringList.class);
        System.out.println(typeVariableMap);
    }

    private static void displayReturnTypeGenericInfo(Class<?> containingClass, Class<?> genericIfc, String methodName, Class... argumentType) throws NoSuchMethodException {
        Method method = containingClass.getMethod(methodName, argumentType);
        // 常规类型作为方法返回值
        Class<?> returnType = GenericTypeResolver.resolveReturnType(method, containingClass);
        System.out.printf("GenericTypeResolver.resolveReturnType(%s, %s) = %s%n", methodName, containingClass.toString(), returnType);
        // 常规类型不具备泛型参数类型 List<E>
        Class<?> returnTypeArgument = GenericTypeResolver.resolveReturnTypeArgument(method, genericIfc);
        System.out.printf("GenericTypeResolver.resolveReturnTypeArgument(%s, %s) = %s%n", methodName, containingClass.toString(), returnTypeArgument);

        System.out.println("============================================================================");
    }

    public static <E> List<E> getList() {
        return null;
    }

    public String getString() {
        return null;
    }

    public StringList getStringList() {
        return null;
    }

    /**
     * 泛型参数具体化（字节码有记录）
     */
    static class StringList extends ArrayList<String> {

    }
}
