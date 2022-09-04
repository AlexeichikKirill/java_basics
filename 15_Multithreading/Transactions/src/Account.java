import java.util.ArrayList;
import java.util.List;

public class Account {

    private long money;
    private String accNumber;
    private final List<String> blockAccNumber = new ArrayList<>();
    private final String textBlock = "Ваш аккаунт заблокирован.";

    public Account(String accNumber) {
        this.accNumber = accNumber;

    }

    public long getMoney() {
        if (blockAccNumber.contains(accNumber)){
            System.out.println(textBlock);
        }
        return money;
    }

    public void setMoney(long money) {
        if (blockAccNumber.contains(accNumber)){
            System.out.println(textBlock);
        } else {
            this.money = money;
        }
    }

    public String getAccNumber() {
        if (blockAccNumber.contains(accNumber)){
            System.out.println(textBlock);
        }
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        if (blockAccNumber.contains(accNumber)){
            System.out.println(textBlock);
        } else {
            this.accNumber = accNumber;
        }
    }

    public void blockAccount() {
        blockAccNumber.add(accNumber);
    }
}
