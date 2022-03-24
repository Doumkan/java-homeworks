import org.json.simple.JSONObject;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class Station extends MosMetro {

    public static void parseStations(Element el) {
        JSONObject s1 = new JSONObject();
        el.getElementsByClass(
                "js-metro-stations t-metrostation-list-table")
                .forEach(j -> {
                    List<String> listOfStations = new ArrayList<>();
                    j.getElementsByClass("name")
                            .forEach(k -> listOfStations.add(k.text()));

                    String numberOfLine = j.attr("data-line");

                    s1.put(numberOfLine, listOfStations);
                    MosMetro.getA1().put("stations", s1);
                });

        writeToFile(MosMetro.getA1());
    }
}
