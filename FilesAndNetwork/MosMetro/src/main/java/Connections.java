import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Connections extends MosMetro {
    private static List<String[]> stationsList = new ArrayList();
    private static JSONArray arr1 = new JSONArray();

    public static void parseConnections(Element el) throws ParseException {
        String path = "data/biblioteka.html"; //connection with station Arbatskaya (there are 2 stations with this name)
        String path2 = "data/borovitskaya.html"; //same connections shouldn't repeat
        String path3 = "data/krylatskoe.html"; //no connections
        String path4 = "data/kunts.html"; //three different stations but with the same name


        Document doc1 = Jsoup.parse(parseFile(path));
        Document doc2 = Jsoup.parse(parseFile(path2));
        Document doc3 = Jsoup.parse(parseFile(path3));
        Document doc4 = Jsoup.parse(parseFile(path4));
        findConnectedStations(doc1);
        findConnectedStations(doc2);
        findConnectedStations(doc3);
        findConnectedStations(doc4);
    }

    private static void findConnectedStations(Document doc) throws ParseException {
        List<String> connectedStations = new ArrayList<>();

        String currentStation = doc.getElementsByClass("t-content-1column b-crumbs")
                .select("span.item").text();

        doc.getElementsMatchingOwnText("Переход на станцию")
                .forEach(a -> connectedStations.add(a.child(0).text()));
        getAllStations();
        if(connectedStations.size() > 0) {
            createConnections(currentStation, connectedStations);
        }
    }

    private static String getJsonFile() {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/mosmetro.json"));
            lines.forEach(line -> builder.append(line));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }

    //gets the list of all stations and adds them to ArrayList<String[stationName, numberLine]>
    private static void getAllStations() throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());

        JSONObject stationsObject = (JSONObject) jsonData.get("stations");
        stationsObject.keySet().forEach(a -> {
            String lineNumber = a.toString();
            JSONArray stationsArray = (JSONArray) stationsObject.get(a);
            stationsArray.forEach(e -> {
                String stationName = e.toString();
                String[] temp = new String[]{stationName, lineNumber};
                stationsList.add(temp);
            });
        });
    }

    private static void createConnections(String currentStation, List<String> connectedStations){
        TreeMap<String, String> temp = new TreeMap<>();
        JSONArray arr = new JSONArray();

        stationsList.forEach(a ->{
            if(a[0].equals(currentStation)){
                temp.put(a[1], currentStation);
            }
            for (int i = 0; i < connectedStations.size(); i++) {
                if(a[0].equals(connectedStations.get(i)) && !temp.containsValue(connectedStations.get(i))){
                    temp.put(a[1], connectedStations.get(i));
                }
            }
        });

        temp.forEach((key, value) -> {
            JSONObject b1 = new JSONObject();
            b1.put("line", key);
            b1.put("station", value);
            arr.add(b1);
        });
        temp.clear();
        arr1.add(arr);
        MosMetro.getA1().put("connections", arr1);
        writeToFile(getA1());
    }
}