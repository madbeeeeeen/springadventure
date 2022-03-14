package com.madbeen.thinking.in.spring.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * {@link org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor} 实现
 *
 * @author: madbeen
 * @date: 2022/03/14/8:07 PM
 */
public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {
    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            System.out.println("modified by [postProcessBeforeDestruction DestructionAwareBeanPostProcessor] desc v9: The user holder");
            UserHolder userHolder = (UserHolder) bean;
            userHolder.setDesc("modified by [postProcessBeforeDestruction DestructionAwareBeanPostProcessor] desc v9: The user holder");
        }
    }
}
