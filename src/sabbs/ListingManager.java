package sabbs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ListingManager {
    private Database database;
    private List<Listing> listings;
    private List<Transaction> bookings;

    public ListingManager() throws SQLException {
        database = new Database();
        listings = FXCollections.observableArrayList();
    }

    public void addListing(Listing listing) throws SQLException {
        database.insertListing(listing);
    }

    public void addBooking(Transaction transaction) throws SQLException {
        database.insertTransaction(transaction);
    }

    public void removeBooking(Transaction transaction) throws SQLException {
        database.removeTransaction(transaction);
    }

    public void sortListings(String attribute, boolean ascending, boolean showFull) throws SQLException {
        listings = database.queryListings(attribute, ascending, showFull);
    }

    public void getListingsByDate(Date from, Date to) throws SQLException {
        listings = database.queryDateListings(from, to);
    }

    public void queryBookings(int cid) throws SQLException {
        //TODO get bookings by customer id
    }

    public List<Transaction> getBookings() {
        return bookings;
    }

    public List<Listing> getListings() {
        return listings;
    }

    public List<Customer> getCustomersList() throws SQLException {
        return database.getCustomers();
    }
}
