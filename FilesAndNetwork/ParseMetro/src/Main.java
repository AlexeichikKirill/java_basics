import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;


public class Main {
    private static final String url = "https://skillbox-java.github.io/";
    private static final String path = "src\\source.json";

    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect(url).maxBodySize(0).get();

            Map<String, Map<String, ArrayList>> map = new HashMap<>();
            Map<String, ArrayList> mapN = new HashMap<>();
            String[] strings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                    "11", "11A", "12", "14", "15", "D1", "D2"};

            for (int i = 0; i < strings.length; i++) {
                ArrayList<String> arrayList = new ArrayList<>();
                String nameLine = doc.select("div[data-depend$='lines-" + strings[i] + "'}]").text();
                String[] stations = doc.select("div[data-depend-set=lines-" + strings[i] + "]").
                        text().replaceAll("\\d", "").split("[.]");

                for (String station : stations) {
                    if (station.isEmpty()) {
                        continue;
                    }
                    arrayList.add(station.trim());
                }
                mapN.put(nameLine, arrayList);
            }
            map.put("station", mapN);
            JSONObject object = new JSONObject(map);

            PrintWriter out = new PrintWriter(new FileWriter(path));
            out.write(object.toString());
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
