package com.example.springbreaker.quoters;

import jakarta.annotation.PostConstruct;


@Profiling
public class TerminatorQuoter implements Quoter {

    @InjectRandomInt(min=2, max=7)
    private int repeat;
    private String message;

    // context.xml по-умолчанию не знает про аннотации => не сработает
    // надо указать CommonAnnotationBeanPostProcessor в бинах
    // работает до создания прокси в BeanPostProcessor
    @PostConstruct
    public void init() {
        System.out.println("Phase 2");
        System.out.println("repeat = " + repeat);
    }

    public TerminatorQuoter() {
        System.out.println("Phase 1");
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    @PostProxy
    public void sayQuote() {
        System.out.println("Phase 3");
        for (int i = 0; i < repeat; i++) {
            System.out.println("message = " + message);
        }
    }
}
