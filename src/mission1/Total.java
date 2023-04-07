package mission1;

public class Total {

    private long total;

    public long getTotal() {
        return total;
    }
    synchronized public void addTotal(long i) {
        total += i;
    }
}
