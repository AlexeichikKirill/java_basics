import java.io.File;

public class FileUtils {

    public static long calculateFolderSize(String path) {
        File folder = new File(path);
        long sum = 0;
        if (folder.isFile()) {
            return folder.length();
        }
        File[] files = folder.listFiles();
        for (File file : files) {
            sum += calculateFolderSize(file.getAbsolutePath());
        }
        return sum;
    }

    public static String calculateSize(long size) {
        char[] multipliers = {'B', 'K', 'M', 'G', 'T'};
        for (int i = 0; i < multipliers.length; i++) {
            double value = size / Math.pow(1024, i);
            if (value < 1024) {
                return "" + Math.round(value) + multipliers[i] + (i > 0 ? "b" : "");
            }
        }
        return "Enchantment";
    }
}
