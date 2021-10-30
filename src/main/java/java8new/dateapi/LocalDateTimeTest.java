package java8new.dateapi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class LocalDateTimeTest {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("today = " + today);

        LocalDate todayAgain = LocalDate.now();
        System.out.println(today.compareTo(todayAgain) == 0);

        System.out.println("년월일 = " + today.getYear() + " " +
                today.getMonth() + " " + today.getMonthValue() + " " +
                today.getDayOfMonth());
        System.out.println("요일  = " + today.getDayOfWeek()+ " " +
                today.getDayOfWeek().getValue());

        //특정 날짜를 지정해서 LocalDate 생성
        LocalDate endDay = LocalDate.of(2021, 12, 31);
        System.out.println("현재기준 몇일 남아 있는냐 ? " + today.until(endDay, ChronoUnit.DAYS) + "일");

        System.out.println("현재기준 1개월 후 " + today.plusMonths(1));
        System.out.println(DayOfWeek.SATURDAY.plus(3));

        //LocalDateTime 사용
        LocalDateTime now = LocalDateTime.now();
        System.out.println("LocalDateTime의 now = " + now);
        
        //LocalDateTime을 LocalDate와 LocalTime으로 변환
        LocalDate localDate = now.toLocalDate();
        System.out.println("localDate = " + localDate);
        LocalTime localTime = now.toLocalTime();
        System.out.println("localTime = " + localTime);
        
    }
}
