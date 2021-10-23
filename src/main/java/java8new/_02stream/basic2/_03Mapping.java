package java8new._02stream.basic2;

import java8new._02stream.basic1.Dish;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class _03Mapping {

    public static void main(String...args){

        //1. map -Dish의 name 목록만
        List<String> nameList = Dish.menu.stream()
                .map(Dish::getName)
                .collect(toList());
        nameList.forEach(System.out::println);

        // map 
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
                                         .map(String::length)
                                         .collect(toList());
        System.out.println(wordLengths);

        //2. map - 중복된 문자 제거하지 못한 word 리스트
        words.stream()  //Stream<String>
                .map(word -> word.split(""))   //Stream<String[]>
                .distinct()
                .forEach(System.out::println);

        //3.flatMap  - 중복된 문자 제거한 word 리스트
        words.stream()
                .map(word -> word.split(""))
                .flatMap(wordArr -> Arrays.stream(wordArr))
                .distinct()
                .forEach(System.out::println);

        //flatMap : Stream<String[]> --> Stream<String>
        words.stream()
                .flatMap(word -> Arrays.stream(word.split("")))
                .distinct()
                .forEach(System.out::print);

        // flatMap
        List<Integer> numbers1 = Arrays.asList(1,2,3,4,5);
        List<Integer> numbers2 = Arrays.asList(6,7,8);
        List<int[]> pairs =
                        numbers1.stream()
                                .flatMap((Integer i) -> numbers2.stream()
                                                       .map((Integer j) -> new int[]{i, j})
                                 )
                                .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                                .collect(toList());
        pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));
    }
}
