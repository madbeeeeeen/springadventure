package com.madbeen.thinking.in.spring.bean.lifecycle;

import com.madbeen.thinking.in.spring.ioc.overview.domain.SuperUser;
import com.madbeen.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * @author: madbeen
 * @date: 2022/03/13/11:05 PM
 */
class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
            // 把配置好的 superUser bean 替换掉
            return new SuperUser();
        }
        // 保持 IoC 容器的实例化操作
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
            // 把配置好的 superUser bean 替换掉
            User user = (User) bean;
            user.setId(1000L);
            // "false" > "user" 对象不允许属性赋值  (配置元信息 -> 属性值)
            return false;
        }
        return true;
    }

    // user 是跳过 Bean 属性复制
    // super user 也是完全跳过 Bean 实例化，（Bean 属性赋值
    // userHolder

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        // 对 userHolder 进行拦截
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            // 假设配置 <property name="number" value="1" />
            // PropertyValues 就包含 一个 PropertyValues（number=1）
            final MutablePropertyValues mutablePropertyValues;

            if (pvs instanceof MutablePropertyValues) {
                mutablePropertyValues = (MutablePropertyValues) pvs;
            } else {
                mutablePropertyValues = new MutablePropertyValues();
            }

            // 等价于 <property name="number" value="1" />
            mutablePropertyValues.addPropertyValue("number", "1");

            // 如果存在 desc 属性配置, 则进行替换
            if (mutablePropertyValues.contains("desc")) {
                // PropertyValue value 是不可变的 （final）
//                    PropertyValue desc = mutablePropertyValues.getPropertyValue("desc");
                mutablePropertyValues.removePropertyValue("desc");
                mutablePropertyValues.add("desc", "modified by [postProcessProperties] desc v2: The user holder");
            }

            return mutablePropertyValues;
        }
        return null;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = (UserHolder) bean;
            userHolder.setDesc("modified by [postProcessBeforeInitialization] desc v3: The user holder");
        }

        return bean;
    }
}
