package com.toy.palace.modern.chapter5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * 1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오
 * 2. 거래자가 근무하는 모든 도시를 중복없이 나열하시오
 * 3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오.
 * 4. 모든 거래자의 이름을 케알파벳순으로 정렬해서 반환하시오.
 * 5. 밀라노에 거래자가 있는가?
 * 6. 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오.
 * 7. 전체 트랜잭션 중 최댓값은 얼마인가?
 * 8. 전체 트랜잭션 중 최솟값은 얼마인가?
 * */
public class RealPractice {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian,2011,300),
                new Transaction(raoul,2012,1000),
                new Transaction(raoul,2011,400),
                new Transaction(mario,2012,710),
                new Transaction(mario,2012,700),
                new Transaction(alan,2012,950)
        );

        //1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오
        List<Transaction> result1 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(toList());

        //2. 거래자가 근무하는 모든 도시를 중복없이 나열하시오
        List<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(toList());

        //3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오.
        List<Trader> workers = transactions.stream()
                .map(Transaction::getTrader) // 트랜잭션의 모든 거래자 추출
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct() // 중복 없도록
                .sorted(Comparator.comparing(Trader::getName))
                .collect(toList());


        //4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오.
        String traderStr = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()// 증복된 이름 제거
                .sorted() // 정렬
                .reduce("",(n1, n2) -> n1+n2);

        //5. 밀라노에 거래자가 있는가?
        boolean milanBased = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("milan"));

        //6. 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오.
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge")) //케임브리지에 거주하는 거래자의 트랜잭션 선택
                .map(Transaction::getValue) // 이 거래자들의 값 추출
                .forEach(System.out::println); // 각 값을 출력

        //7. 전체 트랜잭션 중 최댓값은 얼마인가?
        Optional<Integer> maxVal = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);

        Optional<Transaction> maxVal2 = transactions.stream()
                .reduce((t1, t2)-> t1.getValue() < t2.getValue() ? t1 : t2);

        Optional<Transaction> maxVal3 = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));

        //8. 전체 트랜잭션 중 최솟값은 얼마인가?
        Optional<Integer> minVal = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);

    }
}
