public class Main {
    private static final String path =
            "C:\\Users\\Кирилл Алексейчик\\IdeaProjects\\" +
                    "java_basics\\FilesAndNetwork\\files\\movementList.csv";

    public static void main(String[] args) {
        Movements movements = new Movements(path);
        System.out.println("Сумма расходов: " + movements.getExpenseSum() +
                "\nСумма доходов: " + movements.getIncomeSum() +
                "\n\nСуммы расходов по организациям: \n");
        movements.getExpOnOrg().forEach(System.out::println);
    }
}
