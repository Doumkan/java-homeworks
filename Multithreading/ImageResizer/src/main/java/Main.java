import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the path of the source folder");
        String srcFolder = scanner.nextLine();

        System.out.println("Enter the path of the destination folder");
        String dstFolder = scanner.nextLine();

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        int cores = 8;
        int pieces = files.length / cores;
        int j = 0;

        for (int i = 1; i <= cores; i++) {
            int arraySize = pieces;
            if (i == cores) {
                arraySize = files.length - j;
            }

            File[] newArr = new File[arraySize];
            System.arraycopy(files, j, newArr, 0, newArr.length);
            ImageResizer newResizer = new ImageResizer(newArr, dstFolder, start);
            newResizer.start();

            j = i * pieces;
        }
    }
}
