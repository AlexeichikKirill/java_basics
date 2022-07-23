import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    private static final String URL = "https://skillbox-java.github.io/";
    private static final String PATH = "src\\source.json";
    private static final ArrayList<String> PATH_LIST = new ArrayList<>();
    private static final String PATH_RESOURCES = "src\\main\\resources";
    private static final String JSON_FORMAT = "json";
    private static final String CSV_FORMAT = "csv";

    public static void main(String[] args) throws Exception {
        Map<String, ArrayList<String>> html = parseHTML(URL);
        Map<String, String> stringMap = new TreeMap<>();


        int end = findFileFormat(PATH_RESOURCES, JSON_FORMAT).size();

        for (int i = 0; i < end; i++) {
            Map<String, String> json = parseJsonFile(findFileFormat(PATH_RESOURCES, JSON_FORMAT).get(i));

            html.forEach((lineName, arrayNameStation) -> {
                arrayNameStation.forEach(nameStation -> {
                    if (stringMap.containsKey(nameStation)) {
                        if (json.containsKey(nameStation) && !stringMap.get(nameStation).contains(json.get(nameStation))) {
//                            String oldValue = stringMap.get(nameStation);
//                            stringMap.remove(nameStation);
//                            stringMap.put(nameStation, oldValue + json.get(nameStation) + "\n");
                            stringMap.replace(nameStation, stringMap.get(nameStation) + json.get(nameStation) + "\n");
                        }
                    } else {
                        stringMap.put(nameStation, lineName + "\n");
                    }
                });
            });
        }

        stringMap.forEach((s, s2) -> System.out.println(s + "\n" + s2));
    }

    public static ArrayList<String> findFileFormat(String path, String findFormat) {
        File file = new File(path);
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                findFileFormat(f.getAbsolutePath(), findFormat);
            }
        } else if (file.getName().contains(findFormat) && !file.getAbsolutePath().contains("MACOSX")) {
            PATH_LIST.add(file.getAbsolutePath());
        }
        return PATH_LIST;
    }

    public static Map<String, String> parseJsonFile(String path) throws Exception {
        List<String> lines = Files.readAllLines(Path.of(path));

        ArrayList<String> arrayName = new ArrayList<>();
        ArrayList<String> arrayList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        lines.forEach(s -> {
            if (s.trim().length() > 5) {
                String str = s.replaceAll("\"", "").trim();
                if (s.contains("name")) {
                    arrayName.add(str.replaceAll("station_name", "name").replaceAll(",", ""));
                } else {
                    arrayList.add(str.replaceAll("depth_meters", "depth"));
                }
            }
        });
        for (int i = 0; i < arrayList.size(); i++) {
            map.put(arrayName.get(i), arrayList.get(i));
        }
        return map;
    }

    public static Map<String, ArrayList<String>> parseHTML(String url) {
        Map<String, ArrayList<String>> map = new HashMap<>();
        try {
            Document textHTML = Jsoup.connect(url).maxBodySize(0).get();
            ArrayList<String> strings = new ArrayList<>();
            String nameLn = textHTML.select("div.js-toggle-depend").html();
            String[] s = nameLn.split("\n");

            for (int i = 0; i < s.length; i++) {
                String[] a = s[i].split("\"", 3);
                String[] t = a[1].split(" ");
                String out = t[t.length -1].replace("ln-", "");
                strings.add(out);
            }


            for (String string : strings) {
                ArrayList<String> stationName = new ArrayList<>();

                String nameLine = "line: " + textHTML.select("div[data-depend$='lines-" + string + "'}]")
                        .text();
                String[] stations = textHTML.select("div[data-depend-set=lines-" + string + "]")
                        .text().replaceAll("\\d", "").split("[.]");

                for (String station : stations) {
                    if (station.isEmpty()) {
                        continue;
                    }
                    stationName.add("name: " + station.trim());
                }

                map.put(nameLine, stationName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }

}