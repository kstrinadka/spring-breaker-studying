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
    @Scope(value = "periodical")
    public Color color() {
        Random random = new Random();
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    @Bean
    public ColorFrame frame() {
        return new ColorFrame() {
            @Override
            protected Color getColor() {
                return color(); // это обращение к бину color. Если это prototype, то всегда будет возвращаться новый бин.
            }
        };
    }


    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        while (true) {
            context.getBean(ColorFrame.class).showOnRandomPlace();
            Thread.sleep(150);
        }
    }
}
