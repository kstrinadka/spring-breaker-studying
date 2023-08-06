package com.example.springbreaker.quoters;

public class TerminatorQuoter implements Quoter {

    @InjectRandomInt(min=2, max=7)
    private int repeat;
    private String message;

    // напечатает 0, т.к. оно еще не обработалось спрингом
    public TerminatorQuoter() {
        System.out.println(repeat);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void sayQuote() {
        for (int i = 0; i < repeat; i++) {
            System.out.println("message = " + message);
        }
    }
}
