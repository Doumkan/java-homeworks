import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the original file path");
        String source = scanner.nextLine();

        System.out.println("Enter the destination path");
        String destination = scanner.nextLine();

        FileUtils.copyFolder(source, destination);
    }
}
