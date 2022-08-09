import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "6Jgfj2NO";
        ArrayList<String> courseName = new ArrayList<>();
        Map<String, Integer> countBuyer = new HashMap<>();

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT course_name, COUNT(*) FROM purchaseList GROUP BY course_name");

            while (resultSet.next()){
                String string = resultSet.getString("course_name").trim();
                Integer count = Integer.valueOf(resultSet.getString("COUNT(*)"));
                courseName.add(string);
                countBuyer.put(string, count);
            }

            courseName.forEach(name -> {
                try {
                    int countMonth = 0;
                    double dou = countBuyer.get(name);
                    ResultSet result = statement.executeQuery("SELECT pl.course_name, MONTH(pl.subscription_date) subscription_date " +
                            "FROM PurchaseList pl WHERE pl.course_name = \"" +
                            name + "\" ORDER BY pl.subscription_date");
                    ArrayList<Integer> integers = new ArrayList<>();

                    while (result.next()){
                        String date = result.getString("subscription_date").trim();
                        integers.add(Integer.valueOf(date.trim()));
                    }


                    for (int i = integers.get(0); i <= integers.get(integers.size() - 1); i++) {
                        countMonth+=1;
                    }

                    String formattedDou = new DecimalFormat("#0.00").format(dou / countMonth);
                    System.out.println(name + "  -  " + formattedDou);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            connection.close();
            statement.close();
            resultSet.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}