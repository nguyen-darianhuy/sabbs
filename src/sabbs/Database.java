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
        List<Listing> listings = new ArrayList<Listing>();
        String ascendingStatement = ascending ? "ASC" : "DESC";
        String showFullStatement = showFull ? "" : "WHERE (id not in (SELECT lid FROM Transactions))";
        ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Listings %s ORDER BY %s %s;",
                                                                showFullStatement, attribute, ascendingStatement));

        int rowCount = 0;
        while (rs.next()) {
            Listing tmp = new Listing(rs.getInt("cusid"), rs.getString("Region"), rs.getString("Address"), rs.getInt("Price"), rs.getInt("Capacity"));
            tmp.updateId(rs.getInt("id"));
            listings.add(tmp);
            rowCount++;
        }
        return listings;
    }

    //Throw our the created listing after creation as fields are not handled by java
    public void insertListing(Listing listing) {
        String sql = "INSERT INTO Listings (cusid, Region, Address, Price, Capacity) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, listing.getCid());
            stmt.setString(2, listing.getRegion());
            stmt.setString(3, listing.getAddress());
            stmt.setInt(4, listing.getPrice());
            stmt.setInt(5, listing.getCapacity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertTransaction(Transaction transaction) {
        String sql = "INSERT INTO TRANSACTION (cid, lid, startDate, endDate) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, transaction.getCid());
            stmt.setInt(2, transaction.getLid());
            stmt.setDate(3, transaction.getStartDate());
            stmt.setDate(4, transaction.getEndDate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
