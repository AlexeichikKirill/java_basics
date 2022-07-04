import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
    public static void copyFolder(String sourceDirectory, String destinationDirectory) {
        try {
            File source = new File(sourceDirectory);
            Path dest = Path.of(destinationDirectory);
            File[] folder = source.listFiles();

            if (folder != null) {
                for (File file : folder) {
                    Files.copy(file.toPath(), dest.resolve(file.getName()));
                    copyFolder(file.getAbsolutePath(), dest.resolve(file.getName()).toString());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}