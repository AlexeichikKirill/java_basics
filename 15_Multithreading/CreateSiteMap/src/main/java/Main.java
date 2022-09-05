import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String connectUrl = "https://lenta.ru/";

    public static void main(String[] args) throws IOException {
        sueta(searchLink(connectUrl));
    }

    private static List<String> searchLink(String url) throws IOException {
        List<String> urlList = new ArrayList<>();
        Document document = Jsoup.connect(url).get();
        Elements elements = document.select("a[href*=/]");

        for (Element element : elements) {
            String[] strElem = element.toString().split("href=\"");
            String[] splitStrElem = strElem[1].split("\"", 2);
            String out = splitStrElem[0];
            if (out.startsWith(connectUrl) || out.startsWith("/") ) {
                out = out.startsWith("/") ? connectUrl + out.replaceFirst("/","") : out;
                System.out.println(out);
            }
        }

        return urlList;
    }

    private static void sueta(List<String> urlList) throws IOException {
        for (String url : urlList) {
            searchLink(url);
        }
    }
}
