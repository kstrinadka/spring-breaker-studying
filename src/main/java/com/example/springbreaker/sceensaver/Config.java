package com.example.springbreaker.sceensaver;

import org.springframework.context.annotation.*;

import java.awt.*;
import java.util.Random;

@Configuration
@ComponentScan(basePackages = "com.example.springbreaker.sceensaver")
public class Config {

    // это не метод
    // это объявление бина в java конфигурации (выглядит как метод)

    //Как обновлять prototype в синглтоне?
    @Bean
//    @Scope(value = "prototype"/*, proxyMode = ScopedProxyMode.TARGET_CLASS*/)
    public ColorProvider colorProvider() {
        return new RandomColorProvider();
    }


    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        while (true) {
            context.getBean(ColorFrame.class).showOnRandomPlace();
            Thread.sleep(50);
        }
    }
}
