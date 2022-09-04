import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        List<String> numAcc = new ArrayList<>();

        for (int i = 1; i <= 100000; i++) {
            Account account = new Account(i + "ASSBECK" + (int) (Math.random() * 120 * 100));
            account.setMoney((long) (Math.random() * 55000 ));
            bank.accounts.put(account.getAccNumber(), account);
        }

        numAcc.addAll(bank.accounts.keySet());

        System.out.println("Сумма денег на всех счетах: " + bank.getSumAllAccounts() +
                "\nОстаток на счету: "+ bank.accounts.get(numAcc.get(1)).getMoney() + " (До транзакции)");


        for (int i = 1; i < bank.accounts.size() - 1; i++) {
            bank.transfer(numAcc.get(i), numAcc.get(i + 1), 50001);
        }


        System.out.println("    Номер аккаунта: "+ bank.accounts.get(numAcc.get(1)).getAccNumber() +
                "\n    Остаток на счету: " + bank.getBalance(bank.accounts.get(numAcc.get(1)).getAccNumber()));
        System.out.println("Сумма денег на всех счетах: " + bank.getSumAllAccounts());
    }
}
