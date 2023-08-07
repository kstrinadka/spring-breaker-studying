package com.example.springbreaker.quoters;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Все классы, которые помечены этой аннотацией будут профилировать свои методы
 * В лог идет время выполнения каждого метода.
 *
 * В каждый метод для классов с этой аннотацией должна прописываться логика профайлинга.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Profiling {
}
