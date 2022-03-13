package com.madbeen.thinking.in.spring.denpendency.injection.annotation;

import java.lang.annotation.*;

/**
 * @author: madbeen
 * @date: 2022/03/12/6:23 PM
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectedUser {
}
