package sabbs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

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
        Listing tmp = new Listing(5,"test","123 test",50,5);
        try {
            Database tmpdB = new Database();
            tmpdB.insertListing(tmp);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        launch(args); //run with GUI
        //queryDatabaseExample();
    }

    private static void queryDatabaseExample() {
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://ambari-head.csc.calpoly.edu:3306/databois", "databois", "kappa321");
            Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery("SELECT * FROM Customers");
            int rowCount = 0;
            while (rs.next()) {
                String name = rs.getString("name");
                String address = rs.getString("address");
                System.out.printf("(%s, %s)%n", name, address);
                rowCount++;
            }
            System.out.println("Rows: " + rowCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
