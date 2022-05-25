import java.util.*;

public class Company {

    private List<Employee> employees = new ArrayList<>();

    public void hire(Employee employee) {
        this.employees.add(employee);
    }

    public void hireAll(Collection<Employee> employees) {
        this.employees.addAll(employees);
    }

    public void fire(Employee employee) {
        this.employees.remove(employee);
    }

    public int getIncome() {
        return 15_000_000;
    }

    public List<Employee> getTopSalaryStaff(int count) {
        return getFilteredList(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o2.getMonthSalary() - o1.getMonthSalary();
            }
        }, count);
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        return getFilteredList(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getMonthSalary() - o2.getMonthSalary();
            }
        }, count);
    }

    public List<Employee> getFilteredList(Comparator<Employee> comparator, int count) {
        List<Employee> result = new ArrayList<>();
        if (count < getCountEmployees()) {
            List<Employee> copyList = new ArrayList<>(employees);
            Collections.sort(copyList, comparator);
            for (int i = 0; i < count; i++) {
                result.add(copyList.get(i));
            }
        }
        return result;
    }

    public int getCountEmployees() {
        return employees.size();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void printHighestSalaries(Company company, int count) {
        System.out.println("Список из " + count + " самых высоких зарплат: ");
        int i = 1;
        for (Employee employee : company.getTopSalaryStaff(count)) {
            System.out.println(i++ + " - " + employee.getMonthSalary());
        }
    }

    public void printLowestSalaries(Company company, int count) {
        System.out.println("Список из " + count + " самых низких зарплат: ");
        int i = 1;
        for (Employee employee : company.getLowestSalaryStaff(count)) {
            System.out.println(i++ + " - " + employee.getMonthSalary());
        }
    }

    public void hireEmployees(Company company, int countOperator, int countManager, int countTopManager) {
        for (int i = 0; i < countOperator; i++) {
            Employee operator = new Operator();
            company.hire(operator);
        }
        for (int i = 0; i < countManager; i++) {
            Employee manager = new Manager();
            company.hire(manager);
        }
        for (int i = 0; i < countTopManager; i++) {
            Employee topManager = new TopManager();
            company.hire(topManager);
        }
    }

    public void fireEmployees(Company company, int percentEmployees) {
        if (percentEmployees > 0 && percentEmployees < 100) {
            int countEmployees = company.getCountEmployees();
            double countFire = (countEmployees * (percentEmployees * 0.01));
            for (int i = 0; i < countFire; i++) {
                int rnd = (int) (Math.random() * company.getCountEmployees());
                Employee fireEmployee = company.getEmployees().get(rnd);
                company.fire(fireEmployee);
            }
            System.out.println("Уволено " + (int) countFire + " сотрудников");
        } else {
            System.out.println("Введено неверное количество процентов. " +
                    "Допустимое количество от 0 до 100.");
        }
    }
}