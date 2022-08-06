import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "6Jgfj2NO";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM courses");

            while (resultSet.next()){
                String str = resultSet.getString("name");
                System.out.println(str);
            }

            connection.close();
            statement.close();
            resultSet.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}