package sabbs;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ListingManager {
    private Database database;
    private List<Listing> listings;

    public ListingManager() throws SQLException {
        database = new Database();
        listings = Collections.emptyList();
    }

    public void addListing(Listing listing) {
        database.insertListing(listing);
    }

    public void addBooking(Transaction transaction) {
        database.insertTransaction(transaction);
    }

    public void sortListings(String attribute, boolean ascending, boolean showFull) {
        listings = database.queryListings(attribute, ascending, showFull);
    }

    public List<Listing> getListings() {
        return listings;
    }
}
