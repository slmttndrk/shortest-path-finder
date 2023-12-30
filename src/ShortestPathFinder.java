import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShortestPathFinder {
    public static void main(String[] args) {
        System.out.println("Welcome to find shortest path between Dutch cities.");

        // create a Graph to store cities and their relative distances
        ArrayList<City> cities = new ArrayList<>();

        // fill the Graph with cities and their relative distances
        fillGraph(cities);
        // cities.forEach(c -> System.out.println(c.getName()));

        //TODO Implement Dijkstra's shortest path algorithm

        //TODO Add welcome text to navigate users
    }

    private static void fillGraph(ArrayList<City> cities)
    {
        Map<String, Integer> neighbours = new HashMap<>();

        neighbours.put("Utrecht", 53);
        neighbours.put("Haarlem", 20);
        neighbours.put("Lelystad", 58);
        cities.add(new City("Amsterdam", Integer.MAX_VALUE, neighbours));
        neighbours.clear();

        neighbours.put("Amsterdam", 53);
        neighbours.put("Arnhem", 70);
        neighbours.put("'s-Hertogenbosch", 56);
        neighbours.put("'Zwolle", 91);
        cities.add(new City("Utrecht", Integer.MAX_VALUE, neighbours));
        neighbours.clear();

        neighbours.put("Assen", 28);
        neighbours.put("Leeuwarden", 63);
        cities.add(new City("Groningen", Integer.MAX_VALUE, neighbours));
        neighbours.clear();

        neighbours.put("Amsterdam", 20);
        cities.add(new City("Haarlem", Integer.MAX_VALUE, neighbours));
        neighbours.clear();

        neighbours.put("Utrecht", 70);
        neighbours.put("'s-Hertogenbosch", 63);
        neighbours.put("Zwolle", 68);
        cities.add(new City("Arnhem", Integer.MAX_VALUE, neighbours));
        neighbours.clear();

        neighbours.put("Utrecht", 56);
        neighbours.put("Arnhem", 63);
        neighbours.put("Maastricht", 127);
        neighbours.put("Middelburg", 150);
        cities.add(new City("'s-Hertogenbosch", Integer.MAX_VALUE, neighbours));
        neighbours.clear();

        neighbours.put("'s-Hertogenbosch", 127);
        cities.add(new City("Maastricht", Integer.MAX_VALUE, neighbours));
        neighbours.clear();

        neighbours.put("Utrecht", 91);
        neighbours.put("Arnhem", 68);
        neighbours.put("Leeuwarden", 96);
        neighbours.put("Lelystad", 53);
        neighbours.put("Assen", 78);
        cities.add(new City("Zwolle", Integer.MAX_VALUE, neighbours));
        neighbours.clear();

        neighbours.put("Groningen", 63);
        neighbours.put("Zwolle", 96);
        cities.add(new City("Leeuwarden", Integer.MAX_VALUE, neighbours));
        neighbours.clear();

        neighbours.put("Amsterdam", 58);
        neighbours.put("Zwolle", 53);
        cities.add(new City("Lelystad", Integer.MAX_VALUE, neighbours));
        neighbours.clear();

        neighbours.put("Groningen", 28);
        neighbours.put("Zwolle", 78);
        cities.add(new City("Assen", Integer.MAX_VALUE, neighbours));
        neighbours.clear();

        neighbours.put("'s-Hertogenbosch", 150);
        cities.add(new City("Middelburg", Integer.MAX_VALUE, neighbours));
        neighbours.clear();
    }
}