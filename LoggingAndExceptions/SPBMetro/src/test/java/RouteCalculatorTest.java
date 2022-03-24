import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {

    List<Station> allStations;
    List<Station> connections1;
    List<Station> connections2;
    StationIndex stationIndex = new StationIndex();
    RouteCalculator routeCalculator = new RouteCalculator(stationIndex);

    Line line1 = new Line(1, "Line A");
    Line line2 = new Line(2, "Line B");
    Line line3 = new Line(3, "Line C");

    Station a1 = new Station("A1", line1);
    Station a2 = new Station("A2", line1);
    Station b1 = new Station("B1", line2);
    Station b2 = new Station("B2", line2);
    Station b3 = new Station("B3", line2);
    Station c1 = new Station("C1", line3);
    Station c2 = new Station("C2", line3);

    @Override
    protected void setUp() throws Exception {

        allStations = new ArrayList<>();

        allStations.add(a1);
        allStations.add(a2);
        allStations.add(b1);
        allStations.add(b2);
        allStations.add(b3);
        allStations.add(c1);
        allStations.add(c2);

        line1.addStation(a1);
        line1.addStation(a2);
        line2.addStation(b1);
        line2.addStation(b2);
        line2.addStation(b3);
        line3.addStation(c1);
        line3.addStation(c2);

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        stationIndex.addStation(a1);
        stationIndex.addStation(a2);
        stationIndex.addStation(b1);
        stationIndex.addStation(b2);
        stationIndex.addStation(b3);
        stationIndex.addStation(c1);
        stationIndex.addStation(c2);

        connections1 = new ArrayList<>();
        connections2 = new ArrayList<>();
        connections1.add(a2);
        connections1.add(b1);
        connections2.add(b3);
        connections2.add(c2);

        stationIndex.addConnection(connections1);
        stationIndex.addConnection(connections2);
    }


    public void testCalculateDuration(){
        double actual = RouteCalculator.calculateDuration(allStations);
        double expected = 17.0;

        assertEquals(expected, actual);
    }

    public void testRouteOnTheLine(){
        assertEquals(List.of(b1, b2, b3), routeCalculator.getShortestRoute(b1, b3));
    }

    public void testReversedRouteOneLine(){
        assertEquals(List.of(b3, b2, b1), routeCalculator.getShortestRoute(b3, b1));
    }

    public void testOneStationRoute() {
        assertEquals(List.of(a1), routeCalculator.getShortestRoute(a1, a1));
    }

    public void testRouteWithOneConnection(){
        assertEquals(List.of(a1, a2, b1, b2, b3), routeCalculator.getShortestRoute(a1, b3));
    }

    public void testRouteWithTwoConnections(){
        assertEquals(List.of(a1, a2, b1, b2, b3, c2, c1), routeCalculator.getShortestRoute(a1, c1));
    }

    public void testReversedRouteTwoConnections(){
        assertEquals(List.of(c1, c2, b3, b2, b1, a2, a1), routeCalculator.getShortestRoute(c1, a1));
    }

    @Override
    protected void tearDown() throws Exception {

    }
}
