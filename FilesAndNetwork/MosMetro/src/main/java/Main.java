/*
Program parsing the web page of moscow metro. The html code of some station pages are in 'data' directory. The result
is written to the .json file in 'resources' directory.
The program find the names and numbers of lines, the corresponding stations and the connections between the stations.
 */

import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {

        MosMetro mosMetro = new MosMetro();
        mosMetro.parseHtmlFile("data/metro.html");
    }
}
