package com.example.edf3aly;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserController {

    @FXML
    private Button Class_btn;

    @FXML
    private Button Home_btn;

    @FXML
    private AnchorPane Home_pane;

    @FXML
    private Button Logout;

    @FXML
    private Button membr_btn;

    @FXML
    void logout(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setHeaderText("Logging Out");
            alert.setContentText("Are you sure you want to Log Out?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(this.getClass().getResource("Login.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }


        } catch (Exception exception) {
            exception.printStackTrace();
            exception.getCause();
        }
    }

    @FXML
    void showHomePane(ActionEvent event) {

    }

}
