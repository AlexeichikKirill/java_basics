public class TopManager implements Employee {
    private static final int FIXED_PART = 120_000;

    @Override
    public int getMonthSalary() {
        return FIXED_PART + Company.getIncome() > 10_000_000 ? (int) (FIXED_PART * 1.5) : 0;
    }
}