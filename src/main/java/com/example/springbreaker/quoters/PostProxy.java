package com.example.springbreaker.quoters;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Метод, помеченный @PostProxy, запускается автоматически в тот момент,
 * когда все бины уже настроены и все прокси сгенерировались (после всей вообще настройки)
 *
 * Это можно сделать только с помощью ContextListener, т.к. через другие способы
 * мы доступаемся к бинам на этапе их настройки.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface PostProxy {
}
