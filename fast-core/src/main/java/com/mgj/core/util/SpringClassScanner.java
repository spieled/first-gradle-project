package com.mgj.core.util;


import com.mgj.base.annotation.Table;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.ArrayList;
import java.util.List;

public class SpringClassScanner {

    public static SpringClassScanner getInstance() {
        return new SpringClassScanner();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Finding annotated classes using Spring:");
        new SpringClassScanner().findAnnotatedClasses("com.mgj");
    }

    public List<Class> findTableAnnotatedClassed(String scanPackage) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        ClassPathScanningCandidateComponentProvider provider = createComponentScanner();
        for (BeanDefinition beanDef : provider.findCandidateComponents(scanPackage)) {
            classes.add(Class.forName(beanDef.getBeanClassName()));
        }
        return classes;
    }

    public void findAnnotatedClasses(String scanPackage) {
        ClassPathScanningCandidateComponentProvider provider = createComponentScanner();
        for (BeanDefinition beanDef : provider.findCandidateComponents(scanPackage)) {
            printMetadata(beanDef);
        }
    }

    private ClassPathScanningCandidateComponentProvider createComponentScanner() {
        // Don't pull default filters (@Component, etc.):
        ClassPathScanningCandidateComponentProvider provider
                = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(Table.class));
        return provider;
    }

    private void printMetadata(BeanDefinition beanDef) {
        try {
            Class<?> cl = Class.forName(beanDef.getBeanClassName());
            Table findable = cl.getAnnotation(Table.class);
            System.out.printf("Found class: %s, with meta name: %s%n",
                    cl.getSimpleName(), findable.toString());
        } catch (Exception e) {
            System.err.println("Got exception: " + e.getMessage());
        }
    }
}