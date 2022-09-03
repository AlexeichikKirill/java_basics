import java.io.File;

public class Main {
    private static final int newWidth = 300;

    public static void main(String[] args) {

        int countCores = Runtime.getRuntime().availableProcessors();

        String srcFolder = "C:\\Users\\Кирилл Алексейчик\\src";
        String dstFolder = "C:\\Users\\Кирилл Алексейчик\\dsc";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        int onePart = files.length / countCores;
        int lastPart = files.length % countCores;

        for (int i = 1; i <= countCores; i++) {

            onePart = (i == (countCores - lastPart) && lastPart > 0) ? onePart + 1 : onePart;

            File[] filesOut = new File[onePart];

            System.arraycopy(files, (i - 1) * onePart, filesOut, 0, onePart);
            new Thread(new ImageResizer(filesOut, newWidth, dstFolder, start)).start();
        }
    }
}
