package com.example.springbreaker;

import com.example.springbreaker.quoters.Quoter;
import com.example.springbreaker.quoters.TerminatorQuoter;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        context.getBean(TerminatorQuoter.class).sayQuote();
    }
}

