import java.io.File;
import java.text.DecimalFormat;

public class FileUtils {

    public static long calculateFolderSize(String path) {

        File folder = new File(path);
        if (folder.isFile()) {
            return folder.length();
        }
        long sum = 0;
        try {
            File[] files = folder.listFiles();

            for (File file : files) {
                sum += calculateFolderSize(file.getPath());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sum;
    }

    public static void sizeFormatter(long sum) {

        double sizeInKb = sum / 1024;
        double sizeInMb = sizeInKb / 1024;
        double sizeInGb = sizeInMb / 1024;

        DecimalFormat format = new DecimalFormat("#.##");

        if (sum < 1024) {
            System.out.println("Size of all files: " + sum + " byte");
        } else if (sizeInGb >= 1) {
            System.out.println("Size of all files: " + format.format(sizeInGb) + " Gb");
        } else if (sizeInMb >= 1) {
            System.out.println("Size of all files: " + format.format(sizeInMb) + " Mb");
        } else if (sizeInKb >= 1) {
            System.out.println("Size of all files: " + format.format(sizeInKb) + " Kb");
        }
    }
}