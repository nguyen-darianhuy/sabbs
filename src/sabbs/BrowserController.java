package sabbs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BrowserController {
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
    private void initialize() {
        addressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        regionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRegion()));
        priceColumn.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getPrice()));
        capacityColumn.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getPrice()));
    }

    @FXML
    private void handleBook() {


    }


    public void updateListings(ObservableList<Listing> listings) {
        listingTable.setItems(listings);
    }
}
