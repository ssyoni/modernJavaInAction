package com.toy.palace.modern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FilteringApples {
    public static void main(String... args){
        List<Apple> inventory = Arrays.asList(
                new Apple(80,"green"),
                new Apple(155,"green"),
                new Apple(170,"red"),
                new Apple(120,"red"),
                new Apple(120,"red")

        );

        /* 방법2 */
        List<Apple> greenApples = filterApples(inventory,FilteringApples::isGreenApple);
        System.out.println("greenApples >>> " + greenApples);

        List<Apple> heavyApple = filterApples(inventory, FilteringApples::isHeavyApple);
        System.out.println("heayApples >>>" + heavyApple);

        /* 방법3 - 람다식 */
        filterApples(inventory,(Apple a) -> "green".equals(a.getColor()));
        filterApples(inventory,(Apple a) -> a.getWeight() > 150 );
        filterApples(inventory,(Apple a) -> a.getWeight() < 80 || "red".equals(a.getColor()));

    }

    /* 방법 1 */
    // 초록색 사과만 가져오는 함수
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }
    // 무게가 150보다 큰 사과만 가져오는 함수
    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    /* 방법 2 */
   public static boolean isGreenApple(Apple apple){return "green".equals(apple.getColor());}
   public static boolean isHeavyApple(Apple apple){return apple.getWeight()>150;}

   public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p){
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory){
            if (p.test(apple)){
                result.add(apple);
            }
        }
        return result;
   }



    public static class Apple{
        private int weight =0;
        private String color="";

        public Apple(int weight, String color){
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @SuppressWarnings("boxing")
        @Override
        public String toString() {
            return String.format("Apple{color='%s', weight=%d}", color, weight);
        }
    }
}

