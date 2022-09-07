import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

public class Main {

    private static final String connectUrl = "https://skillbox.ru/";

    public static void main(String[] args){

        LinkExecutor linkExecutor = new LinkExecutor(connectUrl);
        String result = new ForkJoinPool(10).invoke(linkExecutor);

        try {
            FileWriter writer = new FileWriter("src/res/SiteMap");
            writer.write(result);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(result);
    }
}
