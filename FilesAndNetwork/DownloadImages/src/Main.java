import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        try {
            Document doc = Jsoup.connect("https://lenta.ru").get();
            Elements elements = doc.select("img");
            ArrayList<String> https = new ArrayList<>();

            elements.forEach(element -> {
                element.attributes().forEach(attribute -> {
                    if (attribute.getKey().equals("src") && attribute.getValue().matches("https:.+")) {
                        https.add(attribute.getValue().trim());
                    }
                });
            });

            System.out.println("Список Скачанных файлов: ");
            for (int i = 0; i < https.size(); i++) {
                URL url = new URL(https.get(i));
                BufferedImage img = ImageIO.read(url);
                String str = NameImage(https.get(i));
                File file = new File("C:\\Users\\Кирилл Алексейчик\\IdeaProjects\\" +
                        "java_basics\\FilesAndNetwork\\DownloadImages\\DownloadedImage\\" + str);
                ImageIO.write(img, "jpg", file);
                System.out.println("\t" + str);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static String NameImage(String url) {
        String[] fullUrl = url.split("/");
        return fullUrl[fullUrl.length - 1];
    }
}
