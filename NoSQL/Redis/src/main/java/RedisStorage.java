import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Random;

public class RedisStorage {
    private Random rand = new Random();
    private final int SLEEP = 1000; //1 second


    public void init(Jedis jedis, int usersNumber) {
        int i = 1;
        while (jedis.zcard("User list") < usersNumber) {

            String user = "Пользователь " + i;

            jedis.zadd("User list", rand.nextInt(), user);
            i++;
        }

        while (true) {
            displayUsers(jedis, usersNumber);
            pauseThread(SLEEP);
        }
    }

    private void displayUsers(Jedis jedis, int usersNumber) {

        List<String> elements = jedis.zrange("User list", 0, -1);
        elements.forEach(e -> {

            if (ifUserPaid()) {
                e = "Пользователь " + generateRandomNumber(usersNumber);
                System.out.println(e + " воспользовался платной услугой");
            }
            System.out.println(e);
            jedis.zadd("User list", usersNumber, e);
            pauseThread(300);
        });
    }

    private boolean ifUserPaid() {
        int x = rand.nextInt(100);
        return x <= 10;
    }

    private int generateRandomNumber(int bound) {
        return rand.nextInt(bound);
    }

    private void pauseThread(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void clearDatabase(Jedis jedis) {
        int i = 0;
        while (jedis.zcard("User list") > 0) {
            jedis.zrem("User list", "Пользователь " + i);
            i++;
        }
    }
}
