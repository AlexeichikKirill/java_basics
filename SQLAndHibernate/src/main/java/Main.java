import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        HQL();
    }

    public static void HQL() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();


        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "FROM " + Purchaselist.class.getSimpleName() ;
        List<Purchaselist> purchases = session.createQuery(hql).getResultList();

        for (Purchaselist purchase : purchases) {
            LinkedPurchaseList purchaseList = new LinkedPurchaseList();
            purchaseList.setId(new KeyP(purchase.getStudentName(), purchase.getCourseName()));
            purchaseList.setStudentName(purchase.getStudentName());
            purchaseList.setCourseName(purchase.getCourseName());

            session.save(purchaseList);
        }

        session.getTransaction().commit();
        sessionFactory.close();
    }

    public static void Entity(int courseNumber) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Course course = session.get(Course.class, 1);

        Purchaselist purchaselist = session.get(Purchaselist.class, new KeyP(course.getStudents().get(1).getName(), course.getName()));

        System.out.println(purchaselist.getPrice());

        transaction.commit();
        sessionFactory.close();
    }

    public static void Hibernate(int courseNumber) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Course course = session.get(Course.class, courseNumber);
        System.out.println("Курс: " + course.getName() + "\n Количество студентов: " + course.getStudentsCount());

        sessionFactory.close();
    }

    public static void SQLQuarry() {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "6Jgfj2NO";
        ArrayList<String> courseName = new ArrayList<>();
        Map<String, Integer> countBuyer = new HashMap<>();

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT course_name, COUNT(*) FROM purchaseList GROUP BY course_name");

            while (resultSet.next()) {
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

                    while (result.next()) {
                        String date = result.getString("subscription_date").trim();
                        integers.add(Integer.valueOf(date.trim()));
                    }


                    for (int i = integers.get(0); i <= integers.get(integers.size() - 1); i++) {
                        countMonth += 1;
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