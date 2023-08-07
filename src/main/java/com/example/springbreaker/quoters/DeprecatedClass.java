package com.example.springbreaker.quoters;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Хотим, чтобы все бины, которые Deprecated, заменились на нормальные
 *
 * Можно это сделать еще до создания бина в BeanDefinition поменять название на нужное
 * Это можно сделать с помощью BeanFactoryPostProcessor
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DeprecatedClass {
    Class newImpl();
}
