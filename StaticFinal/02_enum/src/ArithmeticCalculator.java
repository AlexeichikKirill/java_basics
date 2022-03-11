public class ArithmeticCalculator {

    public final Operation operation;

    public ArithmeticCalculator(int a, int b, Operation operation) {
        this.operation = operation;

        switch (operation) {
            case ADD -> System.out.println("Сумма: " + (a + b));
            case MULTIPLY -> System.out.println("Умножение: " + (a * b));
            case SUBTRACT -> System.out.println("Вычитание: " + (a - b));
        }
    }
}
