package java8new._02stream.basic1;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class StreamBasic {

    public static void main(String... args) {
        // Java 7
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        System.out.println("8 ---");

        // Java 8
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);
        System.out.println("8 정렬 ---- ");
        getLowCaloricDishesNamesInJava8DESC(Dish.menu).forEach(System.out::println);

        System.out.println(getGroupingMenu(Dish.menu));

        System.out.println("Max 칼로리");
        System.out.println(getMaxCaloryDish(Dish.menu));
        System.out.println(getMaxCaloryDishIntStream(Dish.menu));

    }

    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes) {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish d : dishes) {
            if (d.getCalories() <= 400) {
                lowCaloricDishes.add(d);
            }
        }
        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2) {
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        for (Dish d : lowCaloricDishes) {
            lowCaloricDishesName.add(d.getName());
        }
        List<String> lowCaloricLimit3DishesName = new ArrayList<>();
        lowCaloricLimit3DishesName = lowCaloricDishesName.subList(0, 3);

        return lowCaloricLimit3DishesName;
    }

    //Java 8
    //Stream<Dish> -> Stream<String> -> List<String>
    //정렬 Ascending
    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes) {
        return dishes.stream() //Stream<Dish>
                .filter(dish -> dish.getCalories() <= 400)   //Stream<Dish>
                //.sorted(Comparator.comparing(dish -> dish.getCalories()))   //람다식
                .sorted(Comparator.comparing(Dish::getCalories))              //Method Reference
                //.map(dish -> dish.getName())  //Stream<String>
                .map(Dish::getName)  //Stream<String>
                .collect(toList())  //List<String>
                .subList(0, 3);
    }

    //정렬 Descending
    public static List<String> getLowCaloricDishesNamesInJava8DESC(List<Dish> dishes) {
        Comparator<Dish> dishComparator = comparing(Dish::getCalories);

        return dishes.stream()  //Stream<Dish>
                .filter(dish -> dish.getCalories() <= 400)  //Stream<Dish>
                .sorted(dishComparator.reversed())  //Stream<Dish>
                .map(Dish::getName)                 //Stream<String>
                .collect(toList());                 //List<String>

    }


    //400칼로리 이하인 메뉴를 다이어트로, 아닐 경우 일반으로 그룹핑해라.
    public static Map<String, List<Dish>> getGroupingMenu(List<Dish> dishes) {
        Map<String, List<Dish>> dishMap = dishes.stream()
                .collect(groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return "diet";
                    else return "normal";
                }));
        return dishMap;
    }


    //가장 칼로리가 높은 메뉴를 찾아라
    //1. Stream 의 max() 함수 사용
    public static Dish getMaxCaloryDish(List<Dish> dishes) {
        return dishes.stream()  //Stream<Dish>
                .max(comparing(Dish::getCalories))  //Optional<Dish>
                .get(); //Dish
    }

    //2. IntStream의 max() 함수 사용
    public static int getMaxCaloryDishIntStream(List<Dish> dishes) {
        return dishes.stream()  //Stream<Dish>
                .mapToInt(Dish::getCalories)    //IntStream
                .max()  //OptionalInt
                .getAsInt();  //int
    }

}
