import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.nodes.Element;

public class Line extends MosMetro {

    public static void parseLines(Element el) {
        JSONArray arr = new JSONArray();
        el.getElementsByAttributeValueContaining("class",
                "js-metro-line t-metrostation-list-header t-icon-metroln")
                .forEach(p -> {
                    JSONObject line = new JSONObject();
                    line.put("Name", p.text());
                    line.put("Number", p.attr("data-line"));
                    arr.add(line);
                    MosMetro.getA1().put("lines", arr);
                });
        writeToFile(MosMetro.getA1());
    }
}
