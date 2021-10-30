package java8new._02stream.parallel;

import java.util.stream.*;

public class ParallelStreams {
    //기존의 For Loop = 3 msecs
    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 0; i <= n; i++) {
            result += i;
        }
        return result;
    }
    //순차스트림 - Stream 과 iterate()(스트림의 크기가 고정되지 않음) 메서드 사용 = 67 msecs
    public static long sequentialSum(long n) {
        
        return Stream.iterate(1L, i -> i + 1).limit(n).reduce(Long::sum).get();
    }
    //병렬스트림 - Stream 과 iterate()(스트림의 크기가 고정되지 않음) 메서드 사용 = 114 msecs
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(Long::sum).get();
    }
    //순차스트림 - LongStream 과 rangeClosed() (스트림의 크기가 고정됨) 메서드 사용 = 10 msecs
    public static long rangedSum(long n) {

        return LongStream.rangeClosed(1, n).reduce(Long::sum).getAsLong();
    }
    //병렬스트림 - LongStream 과 rangeClosed() (스트림의 크기가 고정됨) 메서드 사용 = 3 msecs
    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n).parallel().reduce(Long::sum).getAsLong();
    }

    //Sequential Stream = 20 msecs
    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }

    //Parallel Stream = 34 msecs 정상적으로 계산이 이루어지지 않음
    public static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    public static class Accumulator {
        //thread가 공유하는 변수
        private long total = 0;

        public void add(long value) {
            total += value;
        }
    }
}
