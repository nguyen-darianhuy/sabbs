package sabbs;
public class Listing{
    private int id;
    private String region;
    private String address;
    private int price;
    private int capacity;

    public Listing(String region, String address, int price, int capacity) {
        this.region = region;
        this.address = address;
        this.price = price;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public String getRegion() {
        return region;
    }

    public String getAddress() {
        return address;
    }

    public int getPrice() {
        return price;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString(){
        return "(\"" + region + "\", \"" + address + "\", " + price + ", " + capacity + ")";
    }
}