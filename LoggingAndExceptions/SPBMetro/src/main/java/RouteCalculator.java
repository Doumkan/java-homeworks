import core.Station;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class RouteCalculator {
    private final StationIndex stationIndex;

    private static final double INTER_STATION_DURATION = 2.5;
    private static final double INTER_CONNECTION_DURATION = 3.5;

    public RouteCalculator(StationIndex stationIndex) {
        this.stationIndex = stationIndex;
    }

    public List<Station> getShortestRoute(Station from, Station to) {
        List<Station> route = getRouteOnTheLine(from, to);
        if (route != null) {
            return route;
        }

        route = getRouteWithOneConnection(from, to);
        if (route != null) {
            return route;
        }

        route = getRouteWithTwoConnections(from, to);
        return route;
    }

    public static double calculateDuration(List<Station> route) {
        double duration = 0;
        Station previousStation = null;
        for (int i = 0; i < route.size(); i++) {
            Station station = route.get(i);
            if (i > 0) {
                duration += previousStation.getLine().equals(station.getLine()) ?
                        INTER_STATION_DURATION : INTER_CONNECTION_DURATION;
            }
            previousStation = station;
        }
        return duration;
    }

    private List<Station> getRouteOnTheLine(Station from, Station to) { //
        if (!from.getLine().equals(to.getLine())) {     //маршрут на одной линии
            return null;
        }
        List<Station> route = new ArrayList<>();
        List<Station> stations = from.getLine().getStations();  //получаем станции заданной линии
        int direction = 0;
        for (Station station : stations) {          //перебираем станции
            if (direction == 0) {
                if (station.equals(from)) {         //находим точку отправления
                    direction = 1;
                } else if (station.equals(to)) {    //или точку прибытия
                    direction = -1;
                }
            }

            if (direction != 0) {            //если что-то находим, то добавляем
                route.add(station);          //станцию в маршрут
            }

            if ((direction == 1 && station.equals(to)) ||           //дошли до цели -
                    (direction == -1 && station.equals(from))) {    //вышли из цикла
                break;
            }
        }
        if (direction == -1) {          //если на пути перебора станций первой попалась
            Collections.reverse(route); //точка доставки, то переворачиваем коллекцию
        }                               //чтоб поехать в другую сторону
        return route;
    }

    private List<Station> getRouteWithOneConnection(Station from, Station to) {
        if (from.getLine().equals(to.getLine())) {         //маршрут с одной пересадкой
            return null;
        }

        List<Station> route = new ArrayList<>();

        List<Station> fromLineStations = from.getLine().getStations();  //получаем линии точки отправления
        List<Station> toLineStations = to.getLine().getStations();      //и точки доставки
        for (Station srcStation : fromLineStations) {        //перебираем эти станции
            for (Station dstStation : toLineStations) {
                if (isConnected(srcStation, dstStation)) {   //находим точку пересечения
                    ArrayList<Station> way = new ArrayList<>();     //собираем маршрут по линиям
                    way.addAll(getRouteOnTheLine(from, srcStation));  //отправление-пересечение
                    way.addAll(getRouteOnTheLine(dstStation, to));    //пересечение-конец
                    if (route.isEmpty() || route.size() > way.size()) {
                        route.clear();
                        route.addAll(way);       //добавили в маршрут
                    }
                }
            }
        }
        if(route.size() == 0){route = null;}
        return route;
    }

    private boolean isConnected(Station station1, Station station2) {   //пересечение двух станций
        Set<Station> connected = stationIndex.getConnectedStations(station1);   //с разных линий
        return connected.contains(station2);
    }

    private List<Station> getRouteViaConnectedLine(Station from, Station to) {  //средняя линия
        Set<Station> fromConnected = stationIndex.getConnectedStations(from);   //между двумя пересадками
        Set<Station> toConnected = stationIndex.getConnectedStations(to);
        for (Station srcStation : fromConnected) {
            for (Station dstStation : toConnected) {
                if (srcStation.getLine().equals(dstStation.getLine())) {
                    return getRouteOnTheLine(srcStation, dstStation);
                }
            }
        }
        return null;
    }

    private List<Station> getRouteWithTwoConnections(Station from, Station to) {
        if (from.getLine().equals(to.getLine())) { //две пересадки
            return null;
        }

        ArrayList<Station> route = new ArrayList<>();

        List<Station> fromLineStations = from.getLine().getStations(); //получаем станции линий
        List<Station> toLineStations = to.getLine().getStations();

        for (Station srcStation : fromLineStations) {
            for (Station dstStation : toLineStations) {
                List<Station> connectedLineRoute =
                        getRouteViaConnectedLine(srcStation, dstStation); //находим соединяющую линию
                if (connectedLineRoute == null) {
                    continue;
                }
                List<Station> way = new ArrayList<>();      //добавляем все в лист
                way.addAll(getRouteOnTheLine(from, srcStation));
                way.addAll(connectedLineRoute);
                way.addAll(getRouteOnTheLine(dstStation, to));
                if (route.isEmpty() || route.size() > way.size()) {
                    route.clear();
                    route.addAll(way);
                }
            }
        }
        return route;
    }
}