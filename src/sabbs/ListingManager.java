package sabbs;

import java.util.List;
import java.util.Map;

public class ListingManager {
    private Database database;

    private List<Listing> listings;
    private Map<String, Customer> idToCustomer;
    private Map<String, Listing> idToListing;
    private Map<String, Transaction> idToTransaction;

    public ListingManager() {

    }

    public void sortListing(String )

    public void addListing(Listing listing) {
        database.insertListing(listing);
    }

    public void addBooking(Transaction transaction) {
        database.insertTransaction(transaction);
    }

    public void updateListings() {
        listings = database.queryListings();
    }

    public List<Listing> getListings() {
        return listings;
    }
}
