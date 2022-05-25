public class Main {
    public static void main(String[] args) {
        Company company = new Company();
        hireEmployees(company);
        printHighestSalaries(15, company);
        printLowestSalaries(15, company);
        fireEmployees(company, 50);
        printHighestSalaries(12, company);
        printLowestSalaries(12, company);
    }

    private static void fireEmployees(Company company, int percentEmployees) {
        if (percentEmployees > 0 && percentEmployees < 100) {
            int countEmployees = company.countEmployees();
            double countFire = (countEmployees * (percentEmployees * 0.01));
            for (int i = 0; i < countFire; i++) {
                int rnd = (int) (Math.random() * company.countEmployees());
                Employee fireEmployee = company.getEmployees().get(rnd);
                company.fire(fireEmployee);
            }
            System.out.println("Уволено " + (int) countFire + " сотрудников");
        } else {
            System.out.println("Введено неверное количество процентов. " +
                    "Допустимое количество от 0 до 100.");
        }
    }

    private static void printHighestSalaries(int count, Company company) {
        System.out.println("Список из " + count + " самых высоких зарплат: ");
        int i = 1;
        for (Employee employee : company.getTopSalaryStaff(count)) {
            System.out.println(i++ + " - " + employee.getMonthSalary());
        }
    }

    private static void printLowestSalaries(int count, Company company) {
        System.out.println("Список из " + count + " самых низких зарплат: ");
        int i = 1;
        for (Employee employee : company.getLowestSalaryStaff(count)) {
            System.out.println(i++ + " - " + employee.getMonthSalary());
        }
    }

    private static void hireEmployees(Company company) {
        for (int i = 0; i < 180; i++) {
            Employee operator = new Operator();
            company.hire(operator);
        }
        for (int i = 0; i < 80; i++) {
            Employee manager = new Manager();
            company.hire(manager);
        }
        for (int i = 0; i < 10; i++) {
            Employee topManager = new TopManager();
            company.hire(topManager);
        }
    }
}
