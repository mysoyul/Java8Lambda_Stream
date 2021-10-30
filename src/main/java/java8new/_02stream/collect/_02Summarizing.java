package java8new._02stream.collect;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;
import static java8new._02stream.collect.Dish.menu;

public class _02Summarizing {

    public static void main(String ... args) {
        System.out.println("Nr. of dishes: " + howManyDishes());
        System.out.println("The most caloric dish is: " + findMostCaloricDish());
        System.out.println("The least caloric dish is: " + findMostCaloricDishUsingComparator());
        System.out.println("Total calories in menu: " + calculateTotalCalories());
        System.out.println("Average calories in menu: " + calculateAverageCalories());
        System.out.println("Menu statistics: " + calculateMenuStatistics());
        System.out.println("Short menu: " + getShortMenu());
        System.out.println("Short menu comma separated: " + getShortMenuCommaSeparated());
    }


    private static long howManyDishes() {
        return menu.stream().collect(counting());
    }

    //1. Comparator를 사용한 collect(), Collectors.mixBy()를 사용해서 최소 칼로리
    private static Dish findMostCaloricDishUsingComparator() {

        return menu.stream().collect(minBy(comparingInt(Dish::getCalories))).get();
    }

    //1-1. collect() - Collectors.reducing() 사용해서 최대 칼로리
    private static Dish findMostCaloricDish() {
        return menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).get();
    }

    //2.  summingInt() 사용해서 칼로리 합계
    private static int calculateTotalCalories() {

        return menu.stream().collect(summingInt(Dish::getCalories));
    }

    //3. averagingInt() 사용해서 칼로리 평균
    private static Double calculateAverageCalories() {

        return menu.stream().collect(averagingInt(Dish::getCalories));
    }

    //4. summarizingInt() 사용해서 집계함수
    private static IntSummaryStatistics calculateMenuStatistics() {

        return menu.stream().collect(summarizingInt(Dish::getCalories));
    }

    //5. joining() 사용
    private static String getShortMenu() {
        return menu.stream() //Stream<Dish>
                .map(dish -> dish.getName()) //Stream<String>
                .collect(Collectors.joining(" "));
    }

    private static String getShortMenuCommaSeparated() {
        return menu.stream().map(Dish::getName).collect(joining(", "));
    }
}
