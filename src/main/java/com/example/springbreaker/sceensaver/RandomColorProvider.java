package com.example.springbreaker.sceensaver;

import java.awt.*;
import java.util.Random;


public class RandomColorProvider implements ColorProvider {
    @Override
    public Color getColor() {
        Random random = new Random();
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }
}
