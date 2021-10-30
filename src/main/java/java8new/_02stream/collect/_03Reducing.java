package java8new._02stream.collect;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.reducing;
import static java8new._02stream.collect.Dish.menu;

public class _03Reducing {

    public static void main(String ... args) {
        System.out.println("Total calories in menu: " + calculateTotalCalories());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesWithMethodReference());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesWithoutCollectors());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesUsingSum());
    }

    //1. Collectors.reducing() 사용 - 칼로리 합
    private static int calculateTotalCalories() {

        return menu.stream().collect(reducing(0, Dish::getCalories, (d1, d2) -> d1 + d2));
    }
    //2. Method Reference 로 Collectors.reducing(), Integer의 sum() 사용
    private static int calculateTotalCaloriesWithMethodReference() {
                                                                         //(d1,d2) -> Integer.sum(d1,d2)
        return menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
    }
    //3.map(), reduce() 사용
    private static int calculateTotalCaloriesWithoutCollectors() {

        return menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
    }

    //4. mapToInt(), IntStream의 sum() 사용
    private static int calculateTotalCaloriesUsingSum() {

        return menu.stream().mapToInt(Dish::getCalories).sum();
    }
}