/*
This program calculates the size of all files in a directory
 */
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the path to the folder");
        String folderPath = scanner.nextLine();

        FileUtils.sizeFormatter(FileUtils.calculateFolderSize(folderPath));
    }
}