public class Arithmetic {
    private int firstNumber;
    private int secondNumber;

    private Arithmetic(int firstNumber, int secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    private void sum(int firstNumber, int secondNumber) {
        int sum = 0;
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        sum = firstNumber + secondNumber;
        System.out.println("Сумма чисел = " + sum);
    }

    private void multiplication(int firstNumber, int secondNumber) {
        int multiplication = 0;
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        multiplication = firstNumber * secondNumber;
        System.out.println("Произведение чисел = " + multiplication);
    }

    private void maxNumber(int firstNumber, int secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        if (firstNumber > secondNumber) {
            System.out.println(firstNumber);
        } else {
            System.out.println(secondNumber);
        }
    }

    private void minNumber(int firstNumber, int secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        if (firstNumber < secondNumber) {
            System.out.println(firstNumber);
        } else {
            System.out.println(secondNumber);
        }
    }
}
