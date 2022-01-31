package com.toy.palace.modern.chapter5;

import com.toy.palace.modern.dto.Dish;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class StreamEx {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork",false, 800, Dish.Type.MEAT),
                new Dish("beef",false, 700, Dish.Type.MEAT),
                new Dish("chicken",false, 400, Dish.Type.MEAT),
                new Dish("french fries",true, 530, Dish.Type.OTHER),
                new Dish("rice",true, 350, Dish.Type.OTHER),
                new Dish("season fruit",true, 120, Dish.Type.OTHER),
                new Dish("pizza",true, 550, Dish.Type.OTHER),
                new Dish("prawns",false, 300, Dish.Type.FISH),
                new Dish("salmon",false, 450, Dish.Type.FISH)
        );

        /*프레티케이트 필터링*/
        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList());

        /*고유 요소 필터링*/
        List<Integer> numbers = Arrays.asList(1,2,1,3,3,2,4);
        numbers.stream()
                .filter(i -> i%2==0)
                .distinct()
                .forEach(System.out::println);

        /* 스트림을 이용해 처음 등장하는 두 고기요리를 필터링하기*/
        List<Dish> dishes = menu.stream()
                .filter(dish -> {
                    System.out.println(dish.getName());
                    return dish.getType() == Dish.Type.MEAT;
                })
                .limit(2)
                .collect(toList());
    }
}
