package sabbs;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import java.sql.SQLException;

public class PostListingController {

    private String address;
    private String region;
    private String price;
    private String capacity;
    private Database database;

    public PostListingController() throws SQLException
    {
        database = new Database();
    }

    @FXML
    private TextField addressField;

    @FXML
    private TextField regionField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField capacityField;

    @FXML
    private void submit()
    {
        address = addressField.getText();
        region = regionField.getText();
        price = priceField.getText();
        capacity = capacityField.getText();
        if(address.equals("") || region.equals("") || price.equals("") || capacity.equals("")){
            showAlert();
        }
        else{
            int intPrice = Integer.parseInt(price);
            int intCap = Integer.parseInt(capacity);
            Listing newListing = new Listing(region, address, intPrice, intCap);
            database.insertListing(newListing);
        }
    }

    private void showAlert(){
        String warning = "Please provide the following: \n";
        if(address.equals("")){warning += "     address\n";}
        if(region.equals("")) {warning += "     region\n";}
        if(price.equals("")) {warning += "     price\n";}
        if(capacity.equals("")) {warning += "     capacity";}
        Alert alert = new Alert(AlertType.INFORMATION, warning, ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }

}
