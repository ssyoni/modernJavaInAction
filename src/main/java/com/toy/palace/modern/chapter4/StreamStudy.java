package com.toy.palace.modern.chapter4;

import com.toy.palace.modern.dto.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class StreamStudy {
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

        List<String> threeHighCaloriesDishNames =
                menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(toList());

    //    System.out.println(threeHighCaloriesDishNames);


        List<String> names =
                menu.stream()
                    .filter(dish -> {
                        System.out.println("filtering: "+ dish.getName());
                        return dish.getCalories() > 300;
                    }) // 필터링한 요리 명을 출력
                    .map(dish -> {
                        System.out.println("mapping: "+ dish.getName());
                        return dish.getName();
                    })// 추출한 요리명을 출력한다.
                    .limit(3)
                    .collect(toList());
     //   System.out.println(names);

        menu.stream().forEach(System.out::println); // menu 에서 만든 스트림의 모든 요리를 출력

    }
}
