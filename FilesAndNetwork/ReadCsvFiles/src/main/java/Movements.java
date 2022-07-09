import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Movements {
    private final ArrayList<Operetion> operations = new ArrayList<>();

    public Movements(String pathMovementsCsv) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(pathMovementsCsv));
            lines.remove(0);
            for (String line : lines) {
                String[] fragments = line.replace("\"", "").split(",", 8);
                if (fragments.length > 8) {
                    System.out.println("Wrong line :" + line);
                    continue;
                }
                operations.add(new Operetion(fragments[0], fragments[1],
                        fragments[2], fragments[3], fragments[4], fragments[5],
                        Double.parseDouble(fragments[6].replace(",", ".")),
                        Double.parseDouble(fragments[7].replace(",", "."))));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public double getExpenseSum() {
        double sum = 0;
        for (Operetion operetion : operations) {
            sum += operetion.expense;
        }
        return sum;
    }

    public double getIncomeSum() {
        double sum = 0;
        for (Operetion operetion : operations) {
            sum += operetion.coming;
        }
        return sum;
    }

    public ArrayList<String> getExpOnOrg() {
        ArrayList<String> finalSrt = new ArrayList<>();
        Map<String, Double> map = new HashMap<>();
        for (Operetion operetion : operations) {
            String[] str = operetion.description.replace("\\", "/")
                    .replaceAll("[^A-Za-z0-9/.]&\\s+", " ").split("/", 2);
            String[] sos = str[1].split("[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}", 2);
            if (operetion.expense != 0.0) {
                String organization = sos[0].replace("/", " ").trim();
                double expense = operetion.expense;
                if (map.containsKey(organization)) {
                    double value = map.get(organization).intValue() + expense;
                    map.remove(organization, expense);
                    map.put(organization, expense + value);
                    continue;
                }
                map.put(organization, expense);
            }
        }
        for (String name : map.keySet()){
            String value = String.format("%.1f",map.get(name));
            finalSrt.add(name + " = " + value);
        }
        finalSrt.sort(String::compareTo);
        return finalSrt;
    }
}
