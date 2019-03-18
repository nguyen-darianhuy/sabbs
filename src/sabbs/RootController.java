package sabbs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;

public class RootController {

    private Main main;

    @FXML
    private ComboBox comboBox;

    @FXML
    private void handleBrowser() {
        main.setPage("Browser.fxml");
    }

    @FXML
    private void handlePostListing() {
        main.setPage("PostListing.fxml");
    }

    @FXML
    private void handleBookings() {
        main.setPage("Bookings.fxml");
    }

    public void setMain(Main main) {
        this.main = main;
    }


    public void setComboBox(List<Customer> customerList){
        comboBox.setItems(FXCollections.observableArrayList(customerList));
    }
}
