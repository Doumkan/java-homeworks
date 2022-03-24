import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ImageDownload {

    public static void imageDownloader(String path) throws IOException {

        List<String> imgUrls = Jsoup.connect(path).get()
                .select("img")
                .stream().map(p -> p.absUrl("src"))
                .collect(Collectors.toList());

        downloadImages(imgUrls);
    }

    private static void downloadImages(List<String> imgUrls) {
        imgUrls.forEach(i -> {
            try (InputStream in = new URL(i).openStream()) {
                Files.copy(in, Paths.get(getImgName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static String getImgName() {
        String path = "/Users/usr/Desktop/folder/image";
        for (int i = 0; ; i++) {
            String number = i > 0 ? Integer.toString(i) : "";
            String imgName = path + number + ".jpg";
            if (!new File(imgName).exists()) {
                return imgName;
            }
        }
    }
}
