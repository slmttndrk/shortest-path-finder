import java.util.*;

public class CityGraph
{
    private Map<String, List<Road>> cityMap;

    public CityGraph()
    {
        cityMap = new HashMap<>();
    }

    public void addCity (String cityName)
    {
        cityMap.put(cityName, new ArrayList<>());
    }

    public Set<String> getCities ()
    {
        return cityMap.keySet();
    }

    public void addRoad (String sourceCityName, String destinationCityName, int distance)
    {
        cityMap.get(sourceCityName).add(new Road(destinationCityName, distance));
        cityMap.get(destinationCityName).add(new Road(sourceCityName, distance));
    }

    public List<Road> getRoads (String cityName)
    {
        return cityMap.get(cityName);
    }

    public void printCityGraph ()
    {
        cityMap.forEach((city, roads) -> {
            System.out.print("City " + city + " -> ");
            roads.forEach(road ->  System.out.print("(" + road.targetCity + ", " + road.distance + ") "));
            System.out.println();
        });
    }
}
