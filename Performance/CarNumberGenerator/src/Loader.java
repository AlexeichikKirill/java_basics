public class Loader {

    public static void main(String[] args) {

        int countRegCode = 100;
        int countThread = 2;

        start(countRegCode, countThread);

    }

    private static void start(int countRegCode, int countThread) {
        for (int i = 0; i < countThread; i++) {
            int count = countRegCode / countThread;
            int first = count * i;
            int second = first + count;
            new Threads(first, second).start();
            if (countRegCode % countThread != 0) {
                int f = countRegCode % countThread;
                new Threads(countRegCode - f, second + f).start();
            }
        }
    }

    public static String padNumber(int number, int numberLength) {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
            numberStr = '0' + numberStr;
        }

        return numberStr;
    }

}
