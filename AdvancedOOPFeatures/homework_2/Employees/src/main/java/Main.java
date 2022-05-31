import java.time.LocalDate;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Main {

    private static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        Employee employeeMaxSalary = findEmployeeWithHighestSalary(staff, 2017);
        System.out.println(employeeMaxSalary);
    }

    public static Employee findEmployeeWithHighestSalary(List<Employee> staff, int year) {
        Date date = new Date(year-1900, 0, 1);
        Employee emp = new Employee("Name", 0, date);
        staff.stream().filter((i) -> i.getWorkStart().after(date))
                .max(Comparator.comparing(Employee::getSalary))
                .ifPresent((i) -> {
                    emp.setName(i.getName());
                    emp.setSalary(i.getSalary());
                    emp.setWorkStart(i.getWorkStart());
                });
        return emp;
    }
}