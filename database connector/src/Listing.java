import java.time.LocalDate;
import java.util.ArrayList;

public class Listing {
    private int id, capacity;
    private String Region, Address, title;
    private Double price;
    public Listing(int id, int capacity, String Region, String Address, String title, Double price){
        this.id = id;
        this.capacity = capacity;
        this.Region = Region;
        this.Address = Address;
        this.title = title;
        this.price = price;
    }

    /*
    stored in a to:from pair format, i.e.
    [01,02,05,06]
    reserved from days 1-2 and 5-6
     */
    public ArrayList<LocalDate> getBookedDates(){

    }
}
