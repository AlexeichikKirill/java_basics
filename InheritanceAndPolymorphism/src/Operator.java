public class Operator implements Employee {
    private final static int FIXED_PART = 50_000;

    @Override
    public int getMonthSalary() {
        return FIXED_PART;
    }
}
