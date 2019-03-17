package sabbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Database {
    private final static String databaseURL = "jdbc:mysql://ambari-head.csc.calpoly.edu/databois";
    private final static String user = "databois";
    private final static String pass = "kappa321";

    private Connection connection;
    private Statement statement;

    public Database() throws SQLException{
        connection = DriverManager.getConnection(databaseURL, user, pass);
        statement = connection.createStatement();
    }

    public List<Listing> queryListings() {
        /* TO DO */
        return Collections.emptyList();
    }

    public void insertListing(Listing listing) {
        /* TO DO: insert listing into DB */
    }

    public void insertTransaction(Transaction transaction) {
        /* TODO */
    }
}
