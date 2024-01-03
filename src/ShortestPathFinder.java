import javax.sound.midi.Soundbank;
import java.time.temporal.JulianFields;
import java.util.*;

public class ShortestPathFinder {
    public static void main(String[] args) {
        // check command line arguments
        if (args.length < 2) { printWelcomeText(); return; }

        // parse arguments
        String source = args[0];
        String destination = args[1];

        // create a Graph to store cities and their relative distances
        CityGraph cities = new CityGraph();

        // fill the Graph with cities and their relative distances
        fillGraph(cities);
        // cities.printCityGraph();

        // validate source and destination cities
        if (!cities.getCities().contains(source) || !cities.getCities().contains(destination)) {
            System.out.println("Please enter valid source and/or destination!");
            return;
        }

        // find the shortest path from the source city
        ArrayList<TableEntry> citiesOnPath = findShortestPath(source, cities);

        // print the path from source to destination
        printShortestPath(source, destination, citiesOnPath);
    }

    private static void printWelcomeText()
    {
        System.out.println("Welcome to find shortest path between Dutch cities shown below:\n");

        System.out.println("Amsterdam\n" + "Utrecht\n" + "Groningen\n" + "Haarlem\n" + "Arnhem\n" + "'s-Hertogenbosch\n"
                + "Maastricht\n" + "Zwolle\n" + "Leeuwarden\n" + "Lelystad\n" + "Assen\n" + "Middelburg\n");

        System.out.println("Please give source(i.e. Amsterdam) and destination(i.e. Maastricht) " +
                "as command line arguments!\n");

        System.out.println("Example: \njava .\\ShortestPathFinder.java Amsterdam Maastricht\n");
    }

    private static void printShortestPath(String src, String dst, ArrayList<TableEntry> citiesOnPath)
    {
        // check if source and destination is same
        if (src.equals(dst)) {
            System.out.println("\nTotal distance is 0 km since source and destination are same.\n");
            return;
        }

        String result = dst;
        int totalDistance = 0;
        boolean isFound = false;

        // loop over distance table to build the path
        while (!isFound)
        {
            for (TableEntry te : citiesOnPath)
            {
                if (te.getCity().equals(dst))
                {
                    dst = te.getPreviousCity();
                    result = te.getPreviousCity() + " -> " +  result;

                    if (totalDistance == 0) { totalDistance = te.getShortestDistanceFromSource(); }

                    if (dst.equals(src)) { isFound = true; }

                    break;
                }
            }
        }

        System.out.println("\nThe shortest path is:\n" + result);
        System.out.println("\nTotal distance is:\n" + totalDistance + " km");
    }

    private static ArrayList<TableEntry> findShortestPath(String src, CityGraph cities)
    {
        ArrayList<TableEntry> citiesOnPath = new ArrayList<>();
        ArrayList<String> unvisitedCities = new ArrayList<>();

        // create distance table from city graph
        cities.getCities().forEach(city -> {
            if(city.equals(src)){ citiesOnPath.add(new TableEntry(city, 0,"")); }
            else { citiesOnPath.add(new TableEntry(city, Integer.MAX_VALUE,"")); }

            unvisitedCities.add(city);
        });

        // visit cities to fill the distance table
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
        // create a city graph from a subset of all Dutch cities
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

        // add calculated distances (Retrieved from : https://www.distancecalculator.net/country/netherlands)
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