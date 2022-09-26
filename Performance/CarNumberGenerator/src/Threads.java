import java.io.FileOutputStream;

public class Threads extends Thread {

    private int firstRegCode;
    private int secondRegCode;

    public Threads(int firstRegCode, int secondRegCode) {
        this.firstRegCode = firstRegCode;
        this.secondRegCode = secondRegCode;
    }

    @Override
    public void run() {

        long start = System.currentTimeMillis();

        try {

            FileOutputStream writer = new FileOutputStream("res/numbersReg" + firstRegCode + "-" + (secondRegCode - 1) + ".txt");

            char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

            for (; firstRegCode < secondRegCode; firstRegCode++) {
                StringBuilder builder = new StringBuilder();
                for (int number = 1; number < 1000; number++) {
                    for (char firstLetter : letters) {
                        for (char secondLetter : letters) {
                            for (char thirdLetter : letters) {
                                builder.append(firstLetter).append(Loader.padNumber(number, 3));
                                builder.append(secondLetter).append(thirdLetter);
                                builder.append(Loader.padNumber(firstRegCode, 2)).append("\n");
                            }
                        }
                    }
                }
                writer.write(builder.toString().getBytes());
            }

            writer.flush();
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println((System.currentTimeMillis() - start) + " ms");
    }
}
