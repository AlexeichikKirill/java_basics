import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String textErr = "Вы неверно ввели путь, повторите попытку" + "\n";
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите путь папки которую нужно скопировать: ");
            String source = scanner.nextLine();
            File file = new File(source);
            if (!file.exists()) {
                System.out.println(textErr);
                continue;
            }
            System.out.println("Введите путь целевой папки: ");
            String destination = scanner.nextLine();
            File file1 = new File(destination);
            if (!file1.exists()) {
                System.out.println(textErr);
                continue;
            }
            FileUtils.copyFolder(source, destination);
            System.out.println("Копирование выполнено");
            System.out.println("");
        }
    }
}
