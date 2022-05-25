public class TopManager implements Employee {
    private static final int FIXED_PART = 120_000;
    private Company company = new Company();

    @Override
    public int getMonthSalary() {
        return FIXED_PART + company.getIncome() > 10_000_000 ? (int) (FIXED_PART * 1.5) : 0;
    }
}