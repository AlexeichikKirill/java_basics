import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String regexNumb = "[+7]\\d{10}";
        String regexEMail = ".+@.+";

        String[] components = data.split("\\s+");

        Pattern patternNumb = Pattern.compile(regexNumb);
        Matcher matcherNumb = patternNumb.matcher(components[INDEX_PHONE]);

        Pattern patternEMail = Pattern.compile(regexEMail);
        Matcher matcherEMail = patternEMail.matcher(components[INDEX_EMAIL]);

        if (components.length != 4) {
            throw new IllegalArgumentException("Wrong format. Correct format: \n" +
                    "add Василий Петров vasily.petrov@gmail.com +79215637722");
        } else if (!matcherNumb.find()) {
            throw new RuntimeException("Wrong format number. Correct format: +79215637722");
        } else if (!matcherEMail.find()) {
            throw new RuntimeException("Wrong format E-Mail. Correct format: vasily.petrov@gmail.com");
        }
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}