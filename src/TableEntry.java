public class TableEntry
{
    private final String city;
    private int shortestDistanceFromSource;
    private String previousCity;

    TableEntry(String city, int shortestDistanceFromSource, String previousCity)
    {
        this.city = city;
        this.shortestDistanceFromSource = shortestDistanceFromSource;
        this.previousCity = previousCity;
    }

    public String getCity() { return this.city; }
    public int getShortestDistanceFromSource() { return this.shortestDistanceFromSource; }
    public String getPreviousCity() { return this.previousCity; }

    public void setShortestDistanceFromSource(int sdfs) { this.shortestDistanceFromSource = sdfs; }
    public void setPreviousCity(String previousCity) { this.previousCity = previousCity; }
}
