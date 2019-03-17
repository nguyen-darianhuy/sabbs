package sabbs;

import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Database {
    private final static String databaseURL = "jdbc:mysql://ambari-head.csc.calpoly.edu/databois";
    private final static String user = "databois";
    private final static String pass = "kappa321";

    private Connection connection;
    private Statement statement;

    public Database() throws SQLException {
        connection = DriverManager.getConnection(databaseURL, user, pass);
        statement = connection.createStatement();
    }

    public List<Listing> queryListings(String attribute, boolean ascending, boolean showFull) throws SQLException {
        List<Listing> listings = new ArrayList<>();
        String ascendingStatement = ascending ? "ASC" : "DESC";
        String showFullStatement = showFull ? "" : "WHERE (id not in (SELECT lid FROM Transactions))";
        ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Listings %s ORDER BY %s %s;",
                                                                showFullStatement, attribute, ascendingStatement));

        int rowCount = 0;
        while (rs.next()) {
            listings.add(new Listing(rs.getString("Region"), rs.getString("Address"), rs.getInt("Price"), rs.getInt("Capacity")));
            rowCount++;
        }
        return listings;
    }

    public void insertListing(Listing listing) {
        /* TODO: insert listing into DB */
    }

    public void insertTransaction(Transaction transaction) {
        /* TODO Insert booking */
    }
}
