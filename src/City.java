import java.util.Map;

public class City {
    private String name;
    private int distance;
    private Map<String, Integer> neighbours;

    City(String name, int distance, Map<String, Integer> neighbours)
    {
        this.name = name;
        this.distance = distance;
        this.neighbours = neighbours;
    }

    public void setName(String name) { this.name = name; }

    public String getName() { return this.name; }

    public void setDistance(int distance) { this.distance = distance; }

    public int getDistance() { return this.distance; }

    public void setNeighbours(Map<String, Integer> neighbours) { this.neighbours = neighbours; }

    public Map<String, Integer> getNeighbours() { return this.neighbours; }

}
