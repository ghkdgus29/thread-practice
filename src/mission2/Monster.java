package mission2;

import java.util.Random;

public class Monster extends Thread {

    private final static int MONSTER_COUNT = 20;
    private final static int MAX_SUCKAMOUNT = 100;

    private final int id;
    private final int suckCount;
    private final Monster[] monsters;
    Random random = new Random();
    private int hp = 5000;

    public Monster(int id, int suckCount, Monster[] monsters) {
        this.id = id;
        this.suckCount = suckCount;
        this.monsters = monsters;
    }

    @Override
    public void run() {
        for (int i = 0; i < suckCount; i++) {
            int preyIndex = random.nextInt(MONSTER_COUNT);

            while (preyIndex == this.id) {
                preyIndex = random.nextInt(MONSTER_COUNT);
            }

            Monster prey = monsters[preyIndex];

            int suckAmount = random.nextInt(MAX_SUCKAMOUNT) + 1;

            suck(suckAmount);                               // 임계 자원을 분리함으로써 데드락을 피할 수 있다. -> 상대편 임계 자원을 기다릴 필요가 없다.
            prey.bited(suckAmount);
        }
    }

    synchronized public void suck(int suckAmount) {             // 나의 HP가 임계자원
        hp += suckAmount;
    }

    synchronized void bited(int suckAmount) {                   // 먹이감의 HP가 임계자원
        hp -= suckAmount;
    }

    public int getHp() {
        return hp;
    }
}
