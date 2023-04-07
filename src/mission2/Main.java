package mission2;

import java.util.Random;

public class Main {

    // 몬스터 1000마리 , 체력은 5000, 흡혈양은 0 ~ 100,  흡혈대상 랜덤, 흡혈양 랜덤, --> 총합이 5000000 이 유지되어야 한다.
    // 1000번 반복
    public static void main(String[] args) throws InterruptedException {
        final int MONSTER_COUNT = 20;
        final int SUCK_COUNT = 1000;

        Monster[] monsters = new Monster[MONSTER_COUNT];
        for (int i = 0; i < MONSTER_COUNT; i++) {
            monsters[i] = new Monster(i, SUCK_COUNT, monsters);
        }

        for (int i = 0; i < MONSTER_COUNT; i++) {
            monsters[i].start();
        }

        for (int i = 0; i < MONSTER_COUNT; i++) {
            monsters[i].join();
        }

        long totalHp = 0;
        for (int i = 0; i < MONSTER_COUNT; i++) {
            totalHp += monsters[i].getHp();
        }

        System.out.println(totalHp);
    }
}
