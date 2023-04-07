package mission1;

import mission1.Calculator;
import mission1.Total;

import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static AtomicLong atomicSum = new AtomicLong();

    public static void main(String[] args) throws InterruptedException {

        final int max = 30000000;
        final int threadCount = 10000;
        Total total = new Total();
        Calculator[] threads = new Calculator[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Calculator(i, max/threadCount, total);
            threads[i].start();
        }

        for (int i = 0; i < threadCount; i++) {
            threads[i].join();
        }

        System.out.println(total.getTotal());           // 공유 객체에 Synchronized 키워드를 붙여서 해결
        System.out.println(atomicSum);                  // AtomicLong 사용해서 해결
    }

}
