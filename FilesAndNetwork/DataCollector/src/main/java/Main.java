import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    private static final String URL_MOSCOW_UNDERGROUND = "https://skillbox-java.github.io/";
    private static final String PATH_FOR_ASSEMBLING_INFO = "src\\assembling.json";
    private static final String PATH_FOR_MAP = "src\\map.json";
    private static final ArrayList<String> PATH_LIST = new ArrayList<>();
    private static final String PATH_RESOURCES = "src\\main\\resources";
    private static final Map<String, String> HAS_CONNECT = new TreeMap<>();

    public static void main(String[] args) throws Exception {
        Map<String, String> outMap = new TreeMap<>();
        Map<String, String> assemblingInfoMap = assemblingText();

        JSONObject jsonObjectForAssemblingInfo = new JSONObject();
        JSONArray jsonArrayForAssemblingInfo = new JSONArray();

        assemblingInfoMap.forEach((name, info) -> outMap.put(name, info + HAS_CONNECT.getOrDefault(name, "hasConnection: false") + "\n"));

        ArrayList<String> allInfoInString = new ArrayList<>();
        outMap.forEach((name, info) -> allInfoInString.add(name + "\n" + info));


        allInfoInString.forEach(stringAllInfo -> {
            String[] splitString = stringAllInfo.split("\n");
            Map<String, String> mapForAssembling = new TreeMap<>();
            for (String string : splitString) {
                if (!string.isEmpty()) {
                    String[] parts = string.split(":");
                    mapForAssembling.put(parts[0].trim(), parts[1].trim());
                }
            }
            jsonArrayForAssemblingInfo.add(mapForAssembling);
        });

        jsonObjectForAssemblingInfo.put("stations", jsonArrayForAssemblingInfo);
        writeToDisk(jsonObjectForAssemblingInfo, PATH_FOR_ASSEMBLING_INFO);

        JSONObject jsonObjectForWrite = new JSONObject();
        parseHTML(URL_MOSCOW_UNDERGROUND).forEach((line, names) ->  {
            JSONArray jsonArrayForObject = new JSONArray();
            names.forEach(name -> {
                String[] spitName = name.split(":");
                jsonArrayForObject.add(spitName[1].trim());
            });
            jsonObjectForWrite.put(line.replace("line:", "").trim(), jsonArrayForObject);
        });

        writeToDisk(jsonObjectForWrite, PATH_FOR_MAP);

        parseHTML(URL_MOSCOW_UNDERGROUND).forEach((line, arrayListName) -> {
            System.out.println(line + "\nКоличество станций - " + arrayListName.size());
        });
    }

    public static void writeToDisk(JSONObject object, String path) throws Exception {
        PrintWriter out = new PrintWriter(new FileWriter(path));
        out.write(object.toString());
        out.close();
    }

    public static Map<String, String> assemblingText() throws Exception {
        Map<String, ArrayList<String>> htmlMap = parseHTML(URL_MOSCOW_UNDERGROUND);
        Map<String, String> outMap = new TreeMap<>();
        int endCycle = findFileFormat(PATH_RESOURCES).size();

        for (int i = 0; i < endCycle; i++) {
            Map<String, String> mapParseFile = parserJsonAndCsvFile(findFileFormat(PATH_RESOURCES).get(i));

            htmlMap.forEach((lineName, arrayNameStation) -> arrayNameStation.forEach(nameStation -> {
                if (outMap.containsKey(nameStation)) {
                    String addValue = "";
                    if (mapParseFile.containsKey(nameStation) && !outMap.get(nameStation).contains(mapParseFile.get(nameStation))) {
                        String[] part = mapParseFile.get(nameStation).split(":");
                        if (!outMap.get(nameStation).contains(part[0])) {
                            addValue += mapParseFile.get(nameStation) + "\n";
                        }
                    }
                    outMap.replace(nameStation, outMap.get(nameStation) + addValue);
                } else {
                    if (mapParseFile.containsKey(nameStation)) {
                        outMap.put(nameStation, lineName + "\n" + mapParseFile.get(nameStation) + "\n");
                    } else {
                        outMap.put(nameStation, lineName + "\n");
                    }
                }
            }));
        }
        return outMap;
    }

    public static ArrayList<String> findFileFormat(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                findFileFormat(f.getAbsolutePath());
            }
        } else if (!file.getAbsolutePath().contains("MACOSX") &&
                !PATH_LIST.contains(file.getAbsolutePath())) {
            PATH_LIST.add(file.getAbsolutePath());
        }
        return PATH_LIST;
    }

    public static Map<String, String> parserJsonAndCsvFile(String path) throws Exception {
        Map<String, String> outMap = new TreeMap<>();
        List<String> lines = Files.readAllLines(Path.of(path));
        if (path.contains("json")) {

            ArrayList<String> arrayName = new ArrayList<>();
            ArrayList<String> arrayList = new ArrayList<>();

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
                outMap.put(arrayName.get(i), arrayList.get(i));
            }
        } else {
            lines.forEach(line -> {
                String[] splitLine = line.split(",");
                String info;
                if (splitLine[1].split("[.]").length > 1) {
                    info = "date: " + splitLine[1];
                } else {
                    info = "depth: " + splitLine[1];
                }
                outMap.put("name: " + splitLine[0], info);
            });
        }
        return outMap;
    }

    public static Map<String, ArrayList<String>> parseHTML(String url) {
        Map<String, ArrayList<String>> outMap = new HashMap<>();
        try {
            Document textHTML = Jsoup.connect(url).maxBodySize(0).get();
            ArrayList<String> strings = new ArrayList<>();
            String htmlName = textHTML.select("div.js-toggle-depend").html();
            String[] splitName = htmlName.split("\n");

            for (String value : splitName) {
                String[] splitName2 = value.split("\"", 3);
                String[] clearNum = splitName2[1].split(" ");
                String numLine = clearNum[clearNum.length - 1].replace("ln-", "");
                strings.add(numLine);
            }


            for (String string : strings) {
                ArrayList<String> stationName = new ArrayList<>();

                String nameLine = "line: " + textHTML.select("div[data-depend$='lines-" + string + "'}]")
                        .text();
                String[] stationsName = textHTML.select("div[data-depend-set=lines-" + string + "]")
                        .text().replaceAll("\\d", "").split("[.]");

                String[] stationsNameHtml = textHTML.select("div[data-depend-set=lines-" + string + "]").html().split("\n");

                for (String html : stationsNameHtml) {
                    if (html.contains("переход")) {
                        String[] s = html.split("name\">");
                        String[] s2 = s[1].split("</span");
                        HAS_CONNECT.put("name: " + s2[0], "hasConnection: true");
                    }
                }

                for (String station : stationsName) {
                    if (station.isEmpty()) {
                        continue;
                    }
                    stationName.add("name: " + station.trim());
                }
                outMap.put(nameLine, stationName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return outMap;
    }
}