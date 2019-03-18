package sabbs;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import java.sql.SQLException;

public class BookingsController {
    public ListingManager listingManager;
    private Transaction selectedBooking;

    @FXML
    private TableView<Transaction> bookingTable;
    @FXML
    private TableColumn<Transaction, String> addressColumn;
    @FXML
    private TableColumn<Transaction, String> regionColumn;
    @FXML
    private TableColumn<Transaction, String> fromColumn;
    @FXML
    private TableColumn<Transaction, String> toColumn;
    @FXML
    private Button removeButton;

    private void selectBooking(Transaction transaction) {
        selectedBooking = transaction;

        boolean disableBook = transaction == null;
        removeButton.setDisable(disableBook);
    }

    @FXML
    private void initialize() {
        addressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        regionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRegion()));
        fromColumn.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getStartDate()));
        toColumn.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getEndDate()));

        bookingTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectBooking(newValue));

        try {
            listingManager = new ListingManager();
            listingManager.queryBookings(102);
            updateBookings();
        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRemove() {
        System.out.println(selectedBooking.getId());
        try {
            listingManager.removeBooking(selectedBooking);
            String confirm = "You have successfully removed a booking.";
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, confirm, ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Successfully Removed");
            alert.show();

            listingManager.queryBookings(102);
            updateBookings();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void updateBookings() {
        bookingTable.setItems(FXCollections.observableArrayList(listingManager.getBookings()));
    }
}
