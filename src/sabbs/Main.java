package sabbs;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private ListingManager listingManager;

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        listingManager = new ListingManager();
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Simple AirBnb Booking System");

        initRootLayout();
        showBrowser();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showBrowser() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Browser.fxml"));
            AnchorPane browser = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(browser);
            BrowserController c = loader.getController();
            try {
                listingManager.sortListings("Address", true, true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            c.updateListings(FXCollections.observableArrayList(listingManager.getListings()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }


    public static void main(String[] args) {
        launch(args); //run with GUI
        //queryDatabaseExample();
    }

    private static void queryDatabaseExample() {
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://ambari-head.csc.calpoly.edu:3306/databois", "databois", "kappa321");
            Statement statement = connection.createStatement()
        ) {
            List<Listing> listings = new ArrayList<>();
            String attribute = "Region";
            boolean showFull = true;
            boolean ascending = true;
            String ascendingStatement = ascending ? "ASC" : "DESC";
            String showFullStatement = showFull ? "" : "WHERE (id not in (SELECT lid FROM Transactions))";
            ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Listings %s ORDER BY %s %s;",
                    showFullStatement, attribute, ascendingStatement));

            int rowCount = 0;
            while (rs.next()) {
                Listing listing = new Listing(rs.getInt("cusid"), rs.getString("Region"), rs.getString("Address"), rs.getInt("Price"), rs.getInt("Capacity"));
                listing.updateId(rs.getInt("id"));

                listings.add(listing);
                rowCount++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
