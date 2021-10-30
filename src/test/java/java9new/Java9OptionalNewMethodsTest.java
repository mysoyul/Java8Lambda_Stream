package java9new;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class Java9OptionalNewMethodsTest {

    @Test
    public void testOptionalIfPresentOrElse() {

        List<Optional<String>> days = List.of(Optional.of("Monday"),
                Optional.of("Tuesday"),
                Optional.empty(),
                Optional.ofNullable(null),
                Optional.of("Friday"),
                Optional.of("Saturday"),
                Optional.of("Sunday")
        );
        days.stream().forEach(p -> p.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Day not available")));
    }

    @Test
    public void testOptionalOr() {

        List<Optional<Integer>> studentAges = List.of(Optional.of(20),
                Optional.of(21),
                Optional.empty(),
                Optional.ofNullable(null),
                Optional.of(22),
                Optional.of(18),
                Optional.of(19)
        );
        studentAges.stream().map(p -> p.or(() -> Optional.of(20)))
                .forEach(System.out::println);
    }

    @Test
    public void testOptionalStream() {

        Optional<String> maleOpt = Optional.of("Male");
        Optional<String> feMaleOpt = Optional.of("FeMale");
        Optional<String> OtherOpt = Optional.empty();

        assertEquals("Male",maleOpt.stream().findFirst().get());
        assertTrue(maleOpt.stream().count() == 1);

        assertTrue(OtherOpt.stream().count() == 0);
    }


}
