package sabbs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import java.sql.Date;
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

        listingTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectListing(newValue));

        listingManager = new ListingManager();
        handleFull();
    }

    @FXML
    private void handleBook() {
        System.out.println(selectedListing.getId());
        try {
            listingManager.addBooking(new Transaction(102, selectedListing.getId(), Date.valueOf(fromDateBook.getValue()), Date.valueOf(toDateBook.getValue())));
        } catch (SQLException e) {
            String confirm = "This listing has already been booked during that timeframe.";
            Alert alert = new Alert(Alert.AlertType.WARNING, confirm, ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Duplicate");
            alert.show();
        }
    }

    @FXML
    private void handleFull() {
        try {
            listingManager.sortListings("Address", true, showFullBox.isSelected());
            updateListings();
        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDateSearch() {
        if (fromDateSearch.getValue() == null || toDateSearch.getValue() == null) return;
        try {
            listingManager.getListingsByDate(Date.valueOf(fromDateSearch.getValue()), Date.valueOf(toDateSearch.getValue()));
            updateListings();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateListings() {
        listingTable.setItems(FXCollections.observableArrayList(listingManager.getListings()));
    }
}
