import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class Bank {

    public Map<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();

    private final Map<Account, Account> blackAccounts = new HashMap<>();

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        Account from = accounts.get(fromAccountNum);
        Account to = accounts.get(toAccountNum);

        if (from.getMoney() >= amount) {
            from.setMoney(from.getMoney() - amount);
            to.setMoney(to.getMoney() + amount);
            if (amount > 50_000) {
                if (isFraud(fromAccountNum, toAccountNum, amount)){
                    blackAccounts.put(from, to);
                    from.blockAccount();
                    to.blockAccount();
                    System.out.println("Счета заблокированы. Удачного дня)");
                }
            }
        } else {
            System.out.println("Нет такой суммы на балансе. ГДЕ ДЕНЬГИ?");
        }


    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public long getSumAllAccounts() {
        AtomicLong sum = new AtomicLong();
        accounts.values().forEach(s -> sum.addAndGet(s.getMoney()));
        return sum.get();
    }
}
