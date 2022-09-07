import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class LinkExecutor extends RecursiveTask<String> {

    private String url;
    private final ArrayList<LinkExecutor> writeArrayList = new ArrayList<>();
    private final List<String> attributeUrlList = new ArrayList<>();

    public LinkExecutor(String url) {
        this.url = url;
    }

    @Override
    protected String compute() {
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("a[href*=/]");

            for (Element element : elements) {
                String attributeUrl = element.absUrl("href");

                if (!attributeUrl.isEmpty() && attributeUrl.startsWith(url) &&
                        !writeArrayList.contains(attributeUrl) && !attributeUrl.contains("#")
                && !attributeUrlList.contains(attributeUrl)) {
                    LinkExecutor linkExecutor = new LinkExecutor(attributeUrl);
                    linkExecutor.fork();
                    Thread.sleep((150));
                    writeArrayList.add(linkExecutor);
                    attributeUrlList.add(attributeUrl);
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        attributeUrlList.sort(Comparator.naturalOrder());
        StringBuilder out = new StringBuilder();
        for (String s : attributeUrlList) {
                out.append(s).append("\n\t");
        }

        return out.toString();
    }
}