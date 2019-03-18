package sabbs;

import javafx.collections.FXCollections;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

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

    public void queryListingsByAttribute(String attribute, boolean ascending) throws SQLException {
        listings = database.queryListingsByAttribute(attribute, ascending);
    }

    public void queryListingsByDate(Date from, Date to) throws SQLException {
        listings = database.queryListingsByDate(from, to);
    }

    public void queryBookings(int cid) throws SQLException {
        bookings = database.getTransactionsByCustomer(cid);
    }

    public List<Transaction> getBookings() {
        return bookings;
    }

    public List<Listing> getListings() {
        return listings;
    }

    /*public List<Customer> getCustomersList() throws SQLException {
        return database.getCustomers();
    }*/
}
