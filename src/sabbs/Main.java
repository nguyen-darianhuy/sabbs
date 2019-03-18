package sabbs;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private ListingManager listingManager;

    private FXMLLoader loader;
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        loader = new FXMLLoader();
        listingManager = new ListingManager();
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Simple AirBnb Booking System");

        initRootLayout();
        setPage("Browser.fxml");
    }

    public void setPage(String page) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(page));
            AnchorPane browser = (AnchorPane) loader.load();
            rootLayout.setCenter(browser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() throws SQLException {
        try {
            loader.setLocation(getClass().getResource("RootLayout.fxml"));
            rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            RootController c = loader.getController();
            c.setComboBox(listingManager.getCustomersList());
            c.setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args); //run with GUI
    }
}
