package java8new._02stream.collect;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;
import static java8new._02stream.collect.Dish.menu;

public class _05PartitioningDishes {

    public static void main(String ... args) {
        System.out.println("Dishes partitioned by vegetarian: " + partitionByVegeterian());
        System.out.println("Vegetarian Dishes by type: " + vegetarianDishesByType());
        System.out.println("Most caloric dishes by vegetarian: " + mostCaloricPartitionedByVegetarian());
    }

    //1. 채식요리와 채식요리가 아닌 것으로 분류하기
    private static Map<Boolean, List<Dish>> partitionByVegeterian() {

        return menu.stream().collect(partitioningBy(Dish::isVegetarian));
    }
    //2. 채식요리와 채식요리가 아닌 것으로 분류한 다음 type별로 그룹핑 하기
    private static Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType() {

        return menu.stream().collect(
                partitioningBy(
                        Dish::isVegetarian,
                        groupingBy(Dish::getType)
                        )
        );
    }

    //3. 채식요리와 채식요리가 아닌 것으로 분류한 다음
    // 채식요리 중 칼로리 가장 높은 Dish, 채식요리가 아닌 것 중 칼로리 가장 높은 Dish
    private static Map<Boolean,Dish> mostCaloricPartitionedByVegetarian() {

        return menu.stream().collect(
                partitioningBy(
                        Dish::isVegetarian,
                        collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)
                )
        );
    }
}

