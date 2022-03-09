package com.madbeen.thinking.in.spring.bean.definition;

import com.madbeen.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @author: madbeen
 * @date: 2022/03/08/12:00 AM
 */
public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {
        // 1. 通过 BeanDefinitionBuilder
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 通过属性设置
        beanDefinitionBuilder.addPropertyValue("name", "madbeen");
        beanDefinitionBuilder.addPropertyValue("age", 23);
        // 获取 BeanDefinition 实例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // BeanDefinition 并非 bean 终态，可自定义修改

        // 2. 通过 AbstractBeanDefinition 以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        // 设置 Bean 的类型
        genericBeanDefinition.setBeanClass(User.class);
        // 通过 MutablePropertyValues 批量操作
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue("name", "dog");
        propertyValues.addPropertyValue("age", 25);
        // 通过 set MutablePropertyValues 批量操作
        genericBeanDefinition.setPropertyValues(propertyValues);
    }

}
