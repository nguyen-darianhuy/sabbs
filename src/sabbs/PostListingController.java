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
            Listing newListing = new Listing(1, region, address, intPrice, intCap);
            try {
                database.insertListing(newListing);

                showSuccess();
            }
            catch(SQLException e){
                showDup();
            }
            finally {
                addressField.clear();
                regionField.clear();
                priceField.clear();
                capacityField.clear();
            }
        }
    }

    private void showAlert(){
        String warning = "";
        if(address.equals("")){warning += "     address\n";}
        if(region.equals("")) {warning += "     region\n";}
        if(price.equals("")) {warning += "     price\n";}
        if(capacity.equals("")) {warning += "     capacity";}
        Alert alert = new Alert(AlertType.WARNING, warning, ButtonType.OK);
        alert.setHeaderText("Please provide the following ");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }

    private void showSuccess(){
        String confirm = "You have added a new listing.";
        Alert alert = new Alert(AlertType.CONFIRMATION, confirm, ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setHeaderText("Success");
        alert.show();
    }

    private void showDup(){
        String confirm = "This listing is already in the database.";
        Alert alert = new Alert(AlertType.WARNING, confirm, ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setHeaderText("Duplicate");
        alert.show();
    }

}
