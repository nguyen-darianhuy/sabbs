public class Listing{
    int id;
    String region;
    String address;
    int price;
    int capacity;

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

    public String toString(){
        return "(\"" + region + "\", \"" + address + "\", " + price + ", " + capacity + ")";
    }
}