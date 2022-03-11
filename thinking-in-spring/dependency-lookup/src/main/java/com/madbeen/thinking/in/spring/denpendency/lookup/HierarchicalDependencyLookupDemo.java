package com.madbeen.thinking.in.spring.denpendency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

/**
 * 层次性依赖查找示例
 * @author: madbeen
 * @date: 2022/03/10/8:41 PM
 */
public class HierarchicalDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext();
         // 注册 Configuration Class 配置类
        app.register(HierarchicalDependencyLookupDemo.class);

        // 1. 获取 HierarchicalBeanFactory <- ConfigurableBeanFactory <- ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory configurableListableBeanFactory = app.getBeanFactory();
        System.out.println("当前 BeanFactory 的 Parent BeanFactory: " + configurableListableBeanFactory.getParentBeanFactory());
        // 2. 设置 ParentBeanFactory
        HierarchicalBeanFactory parentBeanFactory = createParentBeanFactory();
        configurableListableBeanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println("当前 BeanFactory 的 Parent BeanFactory: " + configurableListableBeanFactory.getParentBeanFactory());

        displayContainsLocalBean(configurableListableBeanFactory, "user");
        displayContainsLocalBean(parentBeanFactory, "user");
        displayContainsBean(configurableListableBeanFactory, "user");
        displayContainsBean(parentBeanFactory, "user");

        // 启动 Spring 上下文
        app.refresh();

        // 显示关闭 Spring 应用上下文
        app.close();
    }

    private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.println(String.format("当前 BeanFactory [%s] 是否包含 bean [names : %s] : %s", beanFactory, beanName, containsBean(beanFactory, beanName)));
    }

    private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory parentHierarchicalBeanFactory = (HierarchicalBeanFactory) parentBeanFactory;
            if (containsBean(parentHierarchicalBeanFactory, beanName)) {
                return true;
            }
        }
        return beanFactory.containsLocalBean(beanName);
    }

    private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.println(String.format("当前 BeanFactory [%s] 是否包含 Local bean [names : %s] : %s", beanFactory, beanName, beanFactory.containsLocalBean(beanName)));
    }

    private static ConfigurableListableBeanFactory createParentBeanFactory() {
        // 创建 BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // XML 文件 ClassPath 路径
        String location = "classpath:/META-INF/dependency-injection-context.xml";
        // 加载配置
        reader.loadBeanDefinitions(location);

        return beanFactory;
    }

}
