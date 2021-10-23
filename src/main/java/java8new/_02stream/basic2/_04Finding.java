package java8new._02stream.basic2;
import java.util.Optional;

import java8new._02stream.basic1.Dish;

public class _04Finding {

    public static void main(String...args){
        if(isVegetarianFriendlyMenu()){
            System.out.println("Vegetarian friendly");
        }

        System.out.println(isHealthyMenu());
        System.out.println(isHealthyMenu2());
        
        Optional<Dish> dish = findVegetarianDish();
        dish.ifPresent(d -> System.out.println(d.getName()));
    }

    //1. anyMatch
    private static boolean isVegetarianFriendlyMenu(){
        return false;
    }
    //2.allMatch
    private static boolean isHealthyMenu(){

        return false;
    }

    //3. noneMatch
    private static boolean isHealthyMenu2(){

        return false;
    }
    //4. findAny
    private static Optional<Dish> findVegetarianDish(){

        return Optional.empty();
    }
    
}
