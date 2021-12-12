package com.toy.palace.modern.chapter2;

public class AppleGreenColorPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return "GREEN".equals(apple.getColor());
    }
}
