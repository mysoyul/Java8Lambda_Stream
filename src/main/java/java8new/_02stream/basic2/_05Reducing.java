package java8new._02stream.basic2;

import java8new._02stream.basic1.Dish;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.IntStream;


public class _05Reducing {

    public static void main(String... args) {
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);

        //reduce - - reduce를 사용하여 sum 을 구하는 방법
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        sum = numbers.stream().reduce((a, b) -> a + b).orElse(0);//Optional<Integer>
        System.out.println(sum);

        //reduce를 사용하여 최소값 구하는 방법
        int min = numbers.stream().reduce((a, b) -> Integer.min(a, b)).get();
        System.out.println(min);

        //reduce를 사용하여 최대값 구하는 방법
        int max = numbers.stream().reduce(0, Integer::max);
        System.out.println(max);

        //칼로리 합계를 구하는 여러가지 방법
        //1. reduce() 함수 - 계산식 사용
        Integer totalCalory = Dish.menu.stream()
                .map(Dish::getCalories)
                .reduce((d1, d2) -> d1 + d2)
                .get();
        System.out.println(totalCalory);
        
        //2. reduce() 함수 - Integer의 sum() 사용
        totalCalory = Dish.menu.stream()
                        .map(Dish::getCalories)
                        .reduce(Integer::sum)
                        .get();
        System.out.println(totalCalory);
        //3. reduce() 대신 mapToInt() 사용해서 IntStream으로 변환
        totalCalory = 
        Dish.menu.stream()  //Steam<Dish>
                .mapToInt(Dish::getCalories) //IntStream
                .sum();
        System.out.println("totalCalory = " + totalCalory);

        //IntStream의 집계함수들을 제공하는 summaryStatistics() 사용
        IntSummaryStatistics summaryStatistics = IntStream.rangeClosed(1, 100).summaryStatistics();
        System.out.println("SummaryStatistics = " + summaryStatistics);

    }
}
