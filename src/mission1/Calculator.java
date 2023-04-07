package mission1;

public class Calculator extends Thread {

    private final int order;
    private final int range;
    private final Total total;

    public Calculator(int order, int range, Total total) {
        this.order = order;
        this.range = range;
        this.total = total;
    }

    @Override                                       // 공유 자원에 쓰레드 개수만큼 접근하므로 비교적 빠르다.
    public void run() {
        long sum = 0;
        for (int i = order * range + 1; i <= order * range + range; i++) {
            sum += i;
        }

        total.addTotal(sum);
        Main.atomicSum.getAndAdd(sum);
    }

//    @Override                                     // 공유 자원에 매번 접근하므로 느리다.
//    public void run() {
//        for (int i = order * range + 1; i <= order * range + range; i++) {
//            total.addTotal(i);
//            mission1.Main.atomicSum.getAndAdd(i);
//        }
//    }
}
