import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static final String TEXT_FILE = "src/main/resources/sitemap.txt";
    private static final String URL = "https://lenta.ru";

    public static void main(String[] args) throws IOException {
        SitemapNode sitemapRoot = new SitemapNode(URL);
        new ForkJoinPool().invoke(new RecursiveParsing(sitemapRoot));

        FileOutputStream stream = new FileOutputStream(TEXT_FILE);
        String result = createSitemapString(sitemapRoot, 0);
        stream.write(result.getBytes());
        stream.flush();
        stream.close();
    }

    public static String createSitemapString(SitemapNode node, int depth) {
        String tabs = String.join("", Collections.nCopies(depth, "\t"));
        StringBuilder result = new StringBuilder(tabs + node.getLink());
        node.getChildren().forEach(child -> {
            result.append("\n").append(createSitemapString(child, depth + 1));
        });
        return result.toString();
    }
}
