import redis.clients.jedis.Jedis;

public class Main {

    private static final int USERS = 20;

    public static void main(String[] args) throws InterruptedException {

        Jedis jedis = new Jedis();
        System.out.println(jedis.ping());

        RedisStorage storage = new RedisStorage();

        storage.clearDatabase(jedis);
        storage.init(jedis, USERS);
    }


}
