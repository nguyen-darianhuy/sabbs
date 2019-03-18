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

    public List<Listing> queryListingsByAttribute(String attribute, boolean ascending) throws SQLException {
        List<Listing> listings = new ArrayList<Listing>();
        String ascendingStatement = ascending ? "ASC" : "DESC";
        ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Listings ORDER BY %s %s;",
                attribute, ascendingStatement));

        while (rs.next()) {
            Listing tmp = new Listing(rs.getInt("cusid"), rs.getString("Region"), rs.getString("Address"), rs.getInt("Price"), rs.getInt("Capacity"));
            tmp.updateId(rs.getInt("id"));
            listings.add(tmp);
        }
        return listings;
    }
    
    public List<Listing> queryListingsByDate(Date from, Date to) throws SQLException {
        List<Listing> listings = new ArrayList<Listing>();
        ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Listings as L LEFT JOIN (SELECT DISTINCT lid FROM Transactions WHERE endDate < \"%s\" OR startDate > \"%s\") as T ON L.id = T.lid;", from, to));
        while (rs.next()) {
            Listing tmp = new Listing(rs.getInt("cusid"), rs.getString("Region"), rs.getString("Address"), rs.getInt("Price"), rs.getInt("Capacity"));
            tmp.updateId(rs.getInt("id"));
            listings.add(tmp);
        }
        return listings;
    }

    //Throw our the created listing after creation as fields are not handled by java
    public void insertListing(Listing listing) throws SQLException {
        String sql = "INSERT INTO Listings (cusid, Region, Address, Price, Capacity) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, listing.getCid());
        stmt.setString(2, listing.getRegion());
        stmt.setString(3, listing.getAddress());
        stmt.setInt(4, listing.getPrice());
        stmt.setInt(5, listing.getCapacity());
        stmt.executeUpdate();

    }

    public void insertTransaction(Transaction transaction) throws SQLException {
        String sql = "INSERT INTO Transactions (cid, lid, startDate, endDate) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, transaction.getCid());
        stmt.setInt(2, transaction.getLid());
        stmt.setDate(3, transaction.getStartDate());
        stmt.setDate(4, transaction.getEndDate());
        stmt.executeUpdate();
    }

    public void removeTransaction(Transaction transaction) throws SQLException {
        statement.executeUpdate(String.format("DELETE FROM Transactions WHERE id = %s", transaction.getId()));
    }

    public List<Transaction> getTransactionsByCustomer(int cid) throws SQLException{
        List<Transaction> transactions = new ArrayList<>();
        String sql = "select * from Listings lsts inner join (select * from Transactions t where cid = ? and endDate >= current_date) as tmp on tmp.lid = lsts.id;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, cid);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Transaction tmp = new Transaction(rs.getInt("tmp.id"), rs.getInt("cusid"),rs.getInt("lid"),rs.getDate("startDate"),rs.getDate("endDate"),rs.getString("Region"),rs.getString("Address"));
            transactions.add(tmp);
        }
        return transactions;

    }
/*
    public List<Customer> getCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<Customer>();
        String sql = "select * from Customers;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            //System.out.println(rs.getString(2));
            Customer tmp = new Customer(rs.getString(2));
            tmp.setID(rs.getInt(1));
            customers.add(tmp);
        }
        return customers;
    }

    public void insertCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO Customers (name) VALUES (?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, customer.getName());
        stmt.executeUpdate();
    }*/
}
