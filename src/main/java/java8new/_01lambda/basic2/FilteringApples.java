package java8new._01lambda.basic2;

import java.util.*;
import java.util.function.Predicate;

public class FilteringApples{

    public static void main(String ... args){

        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                                              new Apple(155, "green"),
                                              new Apple(120, "red"));


        
        // []
        List<Apple> weirdApples = filterApples(inventory, (Apple a) -> a.getWeight() < 80 || 
                                                                       "brown".equals(a.getColor()));
        System.out.println(weirdApples);
    }


    public static boolean isGreenApple(Apple apple) {
        return true; 
    }

    public static boolean isHeavyApple(Apple apple) {
        return true;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p){
        
    	return null;
    }       
}
