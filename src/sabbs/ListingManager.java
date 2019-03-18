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

    public ListingManager() throws SQLException {
        database = new Database();
        listings = FXCollections.observableArrayList();
    }

    public void addListing(Listing listing) throws SQLException {
        database.insertListing(listing);
    }

<<<<<<< HEAD
    public void addBooking(Transaction transaction) throws SQLException{
=======
    public void addBooking(Transaction transaction) throws SQLException {
>>>>>>> 973765c4327bd91d1eb13f3f7769785963d5a639
        database.insertTransaction(transaction);
    }

    public void sortListings(String attribute, boolean ascending, boolean showFull) throws SQLException {
        listings = database.queryListings(attribute, ascending, showFull);
    }

    public void getListingsByDate(Date from, Date to) throws SQLException {
        listings = database.queryDateListings(from, to);
    }

    public List<Listing> getListings() {
        return listings;
    }

    public List<Customer> getCustomersList() throws SQLException {
        return database.getCustomers();
    }
}
