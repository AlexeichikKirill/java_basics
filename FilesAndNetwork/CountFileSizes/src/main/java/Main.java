import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                long sizeAllFile = FileUtils.calculateFolderSize(input);
                String output = FileUtils.calculateSize(sizeAllFile);
                System.out.println(output);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
