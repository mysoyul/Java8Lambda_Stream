package java8new;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;

public class FunctionInterfaceTest {

    @Test
    public void iterable() {
        List<String> stringList = List.of("lambda", "stream", "reactor");
        //익명함수
        stringList.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
        //람다함수
        stringList.forEach(str -> System.out.println(str + ">>>"));

        //Method Reference
        stringList.forEach(System.out::println);
    }


    @Test
    public void functional() {
        //Anonymous Inner class 익명 클래스로 Runnable의 run() 메서드 재정의하기
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("익명 Inner class 구현");
            }
        });
        t1.start();

        //람다식으로 Runnable의 run() 메서드 구현
        Thread t2 = new Thread(() -> System.out.println("람다식으로 구현"));
        t2.start();


        //Thread thread = new Thread(new MyRunnable());

    }

//    class MyRunnable implements Runnable{
//        @Override
//        public void run() {
//
//        }
//    }
}
