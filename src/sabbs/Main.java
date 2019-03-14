package sabbs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        //launch(args); //run with GUI
        queryDatabaseExample();


    }

    private static void queryDatabaseExample() {
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://ambari-head.csc.calpoly.edu:3306/databois", "databois", "kappa321");
            Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery("SELECT * FROM Customers");
            int rowCount = 0;
            while (rs.next()) {
                String name = rs.getString("name");
                String address = rs.getString("address");
                System.out.printf("(%s, %s)%n", name, address);
                rowCount++;
            }
            System.out.println("Rows: " + rowCount);
        } catch (SQLException e) {
            e.printStackTrace();
        } //close ocnnections(?)
    }
}
