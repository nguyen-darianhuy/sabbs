package sabbs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.util.List;

public class BrowserController {
    public ListingManager listingManager;
    @FXML
    private TableView<Listing> listingTable;
    @FXML
    private TableColumn<Listing, String> addressColumn;
    @FXML
    private TableColumn<Listing, String> regionColumn;
    @FXML
    private TableColumn<Listing, String> priceColumn;
    @FXML
    private TableColumn<Listing, String> capacityColumn;
    @FXML
    private Button bookButton;
    @FXML
    private CheckBox showFullBox;

    @FXML
    private void initialize() throws SQLException {
        addressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        regionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRegion()));
        priceColumn.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getPrice()));
        capacityColumn.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getPrice()));
        listingManager = new ListingManager();
    }

    @FXML
    private void handleBook() {


    }

    @FXML
    private void handleFull() throws SQLException{
        if(showFullBox.isSelected()) {
            listingManager.sortListings("Address", true, true);
            updateListings(FXCollections.observableArrayList(listingManager.getListings()));
        }
        else{
            listingManager.sortListings("Address", true, false);
            updateListings(FXCollections.observableArrayList(listingManager.getListings()));
        }
    }


    public void updateListings(ObservableList<Listing> listings) {
        listingTable.setItems(listings);
    }
}
