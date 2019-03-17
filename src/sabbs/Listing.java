package sabbs;
public class Listing{
    private int id;
    private int cid;
    private String region;
    private String address;
    private int price;
    private int capacity;

    public Listing(int cid, String region, String address, int price, int capacity) {
        this.cid = cid;
        this.region = region;
        this.address = address;
        this.price = price;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public int getCid() {
        return cid;
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

    public void updateId(int id){
        this.id = id;
    }

    @Override
    public String toString(){
        return "(\"" + region + "\", \"" + address + "\", " + price + ", " + capacity + ")";
    }
}