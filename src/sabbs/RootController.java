package sabbs;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.sql.SQLException;

public class RootController {


    private Main main;

    @FXML
    private void handleBrowser() {
        main.showBrowser();
    }

    @FXML
    private void handlePostListing() {
        main.showPostListing();
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
