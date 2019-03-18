package sabbs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.List;

public class BrowserController {
    public ListingManager listingManager;
    private Listing selectedListing;

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
    private DatePicker fromDateSearch;
    @FXML
    private DatePicker toDateSearch;
    @FXML
    private DatePicker fromDateBook;
    @FXML
    private DatePicker toDateBook;
    @FXML
    private Button bookButton;
    @FXML
    private CheckBox showFullBox;


    private void selectListing(Listing listing) {
        selectedListing = listing;

        boolean disableBook = listing == null;
        bookButton.setDisable(disableBook);
        fromDateBook.setDisable(disableBook);
        toDateBook.setDisable(disableBook);
    }

    @FXML
    private void initialize() throws SQLException {
        addressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        regionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRegion()));
        priceColumn.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getPrice()));
        capacityColumn.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getPrice()));
        listingManager = new ListingManager();

        listingTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectListing(newValue));
        //toDateSearch.valueProperty().addListener(((observable, oldValue, newValue) -> handleDateSearch()));
    }

    @FXML
    private void handleBook() {
        System.out.println(selectedListing);

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

    @FXML
    private void handleDateSearch() {
        if (fromDateSearch == null || toDateSearch == null) return;
        System.out.println(fromDateSearch.getValue());
        System.out.println(toDateSearch.getValue());
    }


    public void updateListings(ObservableList<Listing> listings) {
        listingTable.setItems(listings);
    }
}
