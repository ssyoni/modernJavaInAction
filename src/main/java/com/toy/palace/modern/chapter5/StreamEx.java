package com.toy.palace.modern.chapter5;

import com.toy.palace.modern.dto.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

        /*단어 리스트가 주어졌을 때 각 단어가 포함하는 글자 수의 리스트*/
        List<String> words = Arrays.asList("modern","java","in","action");
        List<Integer> wordLength = words.stream().map(String::length).collect(toList());

        /* 각 요리명의 길이 매핑*/
        List<Integer> menuLength = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());

        /*스트림 평면화 - 리스트에서 고유문자로 이루어진 리스트를 반환*/
        List<String> wordss = Arrays.asList("hello","world");
        List<String> uniqueCharacters = wordss.stream()
                    .map(w->w.split(""))
                    .flatMap(Arrays::stream)
                    .distinct()
                    .collect(toList());



        /*
        문제1) 숫자리스트가 주어졌을 때 각 숫자의 제곱근으로 이루어진 리스트를 반환하시오.
        예를 들어 [1,2,3,4,5]가 주어지면 [1,4,9,16,25]를 반환해야 한다.
        * */
        List<Integer> nums = Arrays.asList(1,2,3,4,5);

        List<Integer> result = nums.stream()
                .map(n -> n*n)
                .collect(toList());

        /*
        문제2) 두 개의 숫자 리스트가 있을 때 모든 숫자 쌍의 리스트를 반환하시오.
        예를들어 두 개의 리스트[1,2,3]과 [3,4]가 주어지면
        [(1,3),(1,4),(2,3),(2,4),(3,3),(3,4)] 를 반환해야한다. */
        System.out.println("=================================");
        List<Integer> num1 = Arrays.asList(1,2,3);
        List<Integer> num2 = Arrays.asList(3,4);
        List<int[]> numResult = num1.stream()
                .flatMap(i ->
                {
                    System.out.println(">"+i);
                    return num2.stream().map(j -> {
                                                        System.out.println(">>>"+i+","+j);
                                                        return new int[]{i,j};
                                                    });}
                )
                .collect(toList());
        System.out.println("=================================");

        /*이전 예제에서 합이 3으로 나누어 떨어지는 쌍만 반환하기*/
        List<int[]> numResult2 = num1.stream()
                .flatMap(i -> num2.stream()
                                  .filter(j -> (i+j)%3 == 0)
                                  .map(j -> new int[]{i,j})
                )
                .collect(toList());


        /*anyMatch : 프레디케이트가 적어도 한 요소와 일치하는지 확인*/
        if (menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("This menu is vegetarian friendly !!!");
        }
        /*allMath : 모든 요소가 프레디케이트와 일치하는지 확인*/
        boolean isHealthy = menu.stream().allMatch(dish -> dish.getCalories() < 1000);
        System.out.println("isHealthy : "+isHealthy);

        /*noneMatch : 모든 요소아 프레디케이트가 일치하지 않는지 확인*/
        boolean isHealthy2 = menu.stream().noneMatch(dish -> dish.getCalories() >= 1000);

        /*문제) map과 reduce 메서드를 이용해서 스트림의 요리 개수를 계산하시요.*/
        int cnt = menu.stream().map(dish -> 1).reduce(0,(a,b)->a+b);
        System.out.println("요리 갯수 : "+cnt);

    }
}
