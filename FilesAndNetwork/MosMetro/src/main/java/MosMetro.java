import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class MosMetro {

    private static JSONObject a1 = new JSONObject();

    public static JSONObject getA1() {
        return a1;
    }

    public void parseHtmlFile(String source) throws ParseException {
        String html = parseFile(source);
        Element el = Jsoup.parse(html).getElementById("metrodata");
        Line.parseLines(el);
        Station.parseStations(el);
        Connections.parseConnections(el);
    }

    public static String parseFile(String path) {
        StringBuilder sb = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> sb.append(line + "\n"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }

    protected static void writeToFile(JSONObject a1) {
        ObjectMapper mapper = new ObjectMapper();
        DefaultPrettyPrinter pp = new DefaultPrettyPrinter();
        File file = new File("src/main/resources/mosmetro.json");
        try {
            mapper.writer(pp).writeValue(file, a1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
