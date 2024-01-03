import java.time.temporal.JulianFields;
import java.util.*;

public class ShortestPathFinder {
    public static void main(String[] args) {
        System.out.println("Welcome to find shortest path between Dutch cities. \n");

        // create a Graph to store cities and their relative distances
        CityGraph cities = new CityGraph();

        // fill the Graph with cities and their relative distances
        fillGraph(cities);
        // cities.printCityGraph();

        //TODO Implement Dijkstra's shortest path
        String source = "Amsterdam";
        String destination = "Maastricht";
        ArrayList<TableEntry> citiesOnPath = findShortestPath(source, cities);
        printShortestPath(source, destination, citiesOnPath);

        //TODO Add welcome text to navigate users
    }

    private static void printShortestPath(String src, String dst, ArrayList<TableEntry> citiesOnPath)
    {
        System.out.println("The shortest path is:");
        int totalDistance = 0;
        String result = dst;


        boolean isFound = false;
        while (!isFound)
        {
            for (TableEntry te : citiesOnPath)
            {
                if (te.getCity().equals(dst))
                {
                    dst = te.getPreviousCity();
                    result = te.getPreviousCity() + " -> " +  result;

                    if (totalDistance == 0)
                        totalDistance = te.getShortestDistanceFromSource();

                    if (dst.equals(src))
                    {
                        isFound = true;
                    }
                    break;
                }
            }
        }

        System.out.println(result);
        System.out.println("\nTotal distance is:\n" + totalDistance);
    }

    private static ArrayList<TableEntry> findShortestPath(String src, CityGraph cities)
    {
        ArrayList<TableEntry> citiesOnPath = new ArrayList<>();
        ArrayList<String> visitedCities = new ArrayList<>();
        ArrayList<String> unvisitedCities = new ArrayList<>();

        cities.getCities().forEach(city -> {
            if(city.equals(src)){ citiesOnPath.add(new TableEntry(city, 0,"")); }
            else { citiesOnPath.add(new TableEntry(city, Integer.MAX_VALUE,"")); }

            unvisitedCities.add(city);
        });

        while (!unvisitedCities.isEmpty())
        {
            String currentCity = findSmallestKnownDistance(citiesOnPath, unvisitedCities);
            for (Road neighbour : cities.getRoads(currentCity))
            {
                if(unvisitedCities.contains(neighbour.targetCity))
                {
                    TableEntry currentCityRow = citiesOnPath
                            .stream()
                            .filter(te -> te.getCity().equals(currentCity))
                            .toList().get(0);

                    TableEntry neighbourRow = citiesOnPath
                            .stream()
                            .filter(te -> te.getCity().equals(neighbour.targetCity))
                            .toList().get(0);

                    int distance = currentCityRow.getShortestDistanceFromSource() + neighbour.distance;
                    if(distance < neighbourRow.getShortestDistanceFromSource())
                    {
                        neighbourRow.setShortestDistanceFromSource(distance);
                        neighbourRow.setPreviousCity(currentCity);
                    }
                }
            }

            visitedCities.add(currentCity);
            unvisitedCities.remove(currentCity);
        }

        return citiesOnPath;
    }

    private static String findSmallestKnownDistance(ArrayList<TableEntry> citiesOnPath, ArrayList<String> unvisitedCities)
    {
        int smallestKnownDistance = Integer.MAX_VALUE;

        for (TableEntry tableEntry : citiesOnPath)
            if (tableEntry.getShortestDistanceFromSource() < smallestKnownDistance)
                if (unvisitedCities.contains(tableEntry.getCity()))
                    smallestKnownDistance = tableEntry.getShortestDistanceFromSource();

        String closestCity = "";

        for (TableEntry tableEntry : citiesOnPath)
            if (tableEntry.getShortestDistanceFromSource() == smallestKnownDistance)
                if (unvisitedCities.contains(tableEntry.getCity()))
                    closestCity = tableEntry.getCity();

        return closestCity;
    }

    private static void fillGraph(CityGraph cities)
    {
        cities.addCity("Amsterdam");
        cities.addCity("Utrecht");
        cities.addCity("Groningen");
        cities.addCity("Haarlem");
        cities.addCity("Arnhem");
        cities.addCity("'s-Hertogenbosch");
        cities.addCity("Maastricht");
        cities.addCity("Zwolle");
        cities.addCity("Leeuwarden");
        cities.addCity("Lelystad");
        cities.addCity("Assen");
        cities.addCity("Middelburg");

        cities.addRoad("Amsterdam", "Utrecht", 53);
        cities.addRoad("Amsterdam", "Haarlem", 20);
        cities.addRoad("Amsterdam", "Lelystad", 58);

        cities.addRoad("Utrecht", "Arnhem", 70);
        cities.addRoad("Utrecht", "'s-Hertogenbosch", 56);
        cities.addRoad("Utrecht", "Zwolle", 91);

        cities.addRoad("Groningen", "Assen", 28);
        cities.addRoad("Groningen", "Leeuwarden", 63);

        cities.addRoad("Arnhem", "'s-Hertogenbosch", 63);
        cities.addRoad("Arnhem", "Zwolle", 68);

        cities.addRoad("'s-Hertogenbosch", "Maastricht", 127);
        cities.addRoad("'s-Hertogenbosch", "Middelburg", 150);

        cities.addRoad("Zwolle", "Leeuwarden", 96);
        cities.addRoad("Zwolle", "Lelystad", 53);
        cities.addRoad("Zwolle", "Assen", 78);
    }
}