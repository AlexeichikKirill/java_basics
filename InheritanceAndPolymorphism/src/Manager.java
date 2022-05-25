public class Manager implements Employee {
    private final static int FIXED_PART = 70_000;
    private final int earningForCompany;

    public Manager() {
        earningForCompany = (int) (Math.random() * 25000) + 115000;
    }

    @Override
    public int getMonthSalary() {
        return FIXED_PART + (int) (earningForCompany * 0.05);
    }
}