import java.util.Date;
import java.util.List;
import java.util.Random;

import redis.clients.jedis.Jedis;

public class Storage {

    private final Jedis jedis;
    private List<String> users;
    private static final String KEY = "Users";
    private int countUsers;
    private String lastDonatUser;
    private int countDonates = 1;

    public Storage(Jedis jedis) {
        this.jedis = jedis;
    }

    public void start(int countUsers) throws InterruptedException {
        this.countUsers = countUsers;
        init(countUsers);
        outPrint();
    }

    private void init(int countUsers) throws InterruptedException {
        clearBase();
        for (int i = 1; i <= countUsers; i++) {
            Thread.sleep(1);
            jedis.zadd(KEY, new Date().getTime(), String.valueOf(i));
        }

        this.users = jedis.zrange(KEY, 0, countUsers);
    }

    private void outPrint() throws InterruptedException {
        for (String user : users) {
            String compare = user.trim();
            if (!compare.equals(lastDonatUser)) {
                System.out.println("-- На главной странице показываем пользователя " + user);
//                Thread.sleep(1000);
                if ((Math.random() < 0.2 | countDonates > 10) & countDonates > 5) {
                    donate();
                    countDonates = 1;
                } else {
                    countDonates++;
                }
            }
        }
        Thread.sleep(1000);
        outPrint();
    }

    private void donate() {
        int randomUser = new Random().nextInt(users.size());
        randomUser = randomUser <= 0 ? 1 : randomUser;
        lastDonatUser = String.valueOf(randomUser);

        System.out.println("> Пользователь " + randomUser + " оплатил платную услугу");
    }

    private void clearBase() {
        jedis.del(KEY);
    }
}