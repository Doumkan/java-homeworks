import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FileUtils extends SimpleFileVisitor<Path> {

    private String destination;
    private String source;
    private static Set<Path> duplicates = new HashSet<>();
    private static String path = "";

    public FileUtils(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }

    public static void copyFolder(String sourceDirectory, String destinationDirectory) throws IOException {
        Files.walk(Paths.get(sourceDirectory))
                .forEach(source -> {
                    Path destination = Paths.get(destinationDirectory, source.toString()
                            .substring(sourceDirectory.length()));
                    try {
                        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        path = destination + dir.toString().substring(source.length());
        if (!dir.toString().equals(source)) {
            if (!checkIfExists(path)) {
                copyFolder(dir.toString(), path);
            }
            return FileVisitResult.SKIP_SUBTREE;
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        path = destination + file.toString().substring(source.length());
        if (attrs.isRegularFile()) {
            if (!checkIfExists(path)) {
                copyFolder(file.toString(), path);
            }
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {

        String reply = "";

        if (dir.equals(Paths.get(source)) && !duplicates.isEmpty()) {
            System.out.println("These files already exist: ");
            duplicates.forEach(System.out::println);
            System.out.println("Do you want to replace them?");
            reply = new Scanner(System.in).nextLine();
        }
        if (reply.equals("yes")) {
            duplicates.forEach(source -> {
                path = destination + source.toString().substring(source.toString().length());
                try {
                    copyFolder(source.toString(), path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        return super.postVisitDirectory(dir, exc);
    }

    private static boolean checkIfExists(String path) {
        File f = new File(path);
        if (f.exists()) {
            duplicates.add(Paths.get(path));
            return true;
        }
        return false;
    }
}
