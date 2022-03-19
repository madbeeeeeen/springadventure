package com.madbeen.thinking.in.spring.annotation;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 偶数 Profile 条件
 * @author: madbeen
 * @date: 2022/03/19/5:12 PM
 */
public class EventProfileConditional implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        // 条件上下文
        Environment environment = conditionContext.getEnvironment();
        return environment.acceptsProfiles("even");
    }
}
