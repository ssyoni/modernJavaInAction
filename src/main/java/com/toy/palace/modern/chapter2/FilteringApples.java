package com.toy.palace.modern.chapter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FilteringApples {
    public static void main(String... args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(170, "red"),
                new Apple(120, "red"),
                new Apple(120, "red")
        );

        //List<Apple> greenApples = filterApples(inventory, new AppleGreenColorPredicate());
        //List<Apple> heavyApples = filterApples(inventory, new AppleHeavyWeightPredicate());
        
        /*람다표현식 사용*/
        List<Apple> greenApples = filterApples(inventory, (Apple a) -> "GREEN".equals(a.getColor()));

        /*리스트 형식으로 추상화*/
        List<Apple> redApples = filter(inventory, (Apple a)->"RED".equals(a.getColor()));

        //
       /* inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });*/

       // inventory.sort((Apple a1, Apple a2)-> a1.getWeight().compareTo(a2.getWeight()));

    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory){
            if (p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    /*리스트 형식으로 추상화*/
    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for (T e:list){
            if (p.test(e)){
                result.add(e);
            }
        }
        return result;
    }

}