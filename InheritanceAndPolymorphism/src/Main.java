public class Main {
    public static void main(String[] args) {
        Company company = new Company();
        company.hireEmployees(company, 180, 80, 10);
        company.printHighestSalaries(company, 15);
        company.printLowestSalaries(company, 15);
        company.fireEmployees(company, 99);
        company.printHighestSalaries(company, 12);
        company.printLowestSalaries(company, 12);
    }
}
