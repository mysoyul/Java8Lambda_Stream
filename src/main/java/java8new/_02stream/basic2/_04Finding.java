package java8new._02stream.basic2;

import java8new._02stream.basic1.Dish;

import java.util.Optional;

import static java8new._02stream.basic1.Dish.menu;

public class _04Finding {

    public static void main(String...args){
        if(isVegetarianFriendlyMenu()){
            System.out.println("Vegetarian friendly");
        }

        System.out.println(isHealthyMenu());
        System.out.println(isHealthyMenu2());
        
        Optional<Dish> optional = findVegetarianDish();
        if(optional.isPresent()) {
            Dish dish = optional.get();
            System.out.println(dish);
        }
        optional.ifPresent(d -> System.out.println(d.getName()));

        Dish dish2 = optional.orElseGet(() -> new Dish("짜장",false,500, Dish.Type.OTHER));
        Dish dish3 = optional.orElseThrow(() -> new RuntimeException("조건을 만족하는 Dish가 없습니다."));
        System.out.println(dish3);
    }

    //1. anyMatch
    private static boolean isVegetarianFriendlyMenu(){
        return menu.stream().anyMatch(Dish::isVegetarian);
    }
    //2.allMatch - 모든 엘리먼트가 해당 조건을 만족해야 true 가 반환된다.
    private static boolean isHealthyMenu(){
        return menu.stream().allMatch(dish -> dish.getCalories() <= 800);
    }

    //3. noneMatch - 모든 엘리먼트가 해당 조건을 만족하지 않아야 true 가 반환된다.
    private static boolean isHealthyMenu2(){
        return menu.stream().noneMatch(dish -> dish.getCalories() > 800);
    }
    //4. findAny
    private static Optional<Dish> findVegetarianDish(){
        //return Optional.empty(); //비어 있는 Optional 객체 반환
        //조건을 만족하여 Dish가 Not null 인 경우
        //return menu.stream().filter(Dish::isVegetarian).findAny();

        //조건을 만족하지 않아서 Dish가 Null 인 경우
        return menu.stream().filter(dish -> dish.getCalories() > 800).findAny();

        //return null;
    }
    
}
