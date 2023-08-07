package com.example.springbreaker.quoters;

public class T1000 extends TerminatorQuoter implements Quoter {
    @Override
    public void sayQuote() {
        System.out.println("Я ЖИДКИЙ");
    }
}
