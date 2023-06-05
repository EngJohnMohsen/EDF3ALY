package com.example.edf3aly;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Button Check_btn;

    @FXML
    private Button FinalRegAccPane_btn;

    @FXML
    private Pane FinalRegAcc_pane;

    @FXML
    private ComboBox<?> Gender_combo;

    @FXML
    private TextField RegCheck;

    @FXML
    private TextField RegFirstN;

    @FXML
    private TextField RegLastN;

    @FXML
    private TextField RegNationalID;

    @FXML
    private PasswordField RegPassword;

    @FXML
    private TextField RegPhoneN;

    @FXML
    private TextField RegUserName;

    @FXML
    private Pane createAcc_pane;

    @FXML
    private Pane login_pane;

    @FXML
    private PasswordField password;

    @FXML
    private TextField userName;

    @FXML
    void backToMain(ActionEvent event) {

    }

    @FXML
    void createAcc(ActionEvent event) {

    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        if (userName.getText().equals("") || password.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Fields");
            alert.setHeaderText("Data Fields are empty");
            alert.setContentText("Please fill in the fields.");
            alert.show();
        } else {
//            String userNameText = userName.getText();
//            String passwordText = password.getText();
            User user = new User("Ahmed Mohsen", "5678", "7755", "user1", "password",new Account(Account.AccountType.Savings,"1234",1000));
            try {
                if (user == null || !password.getText().equals(user.getPassword())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Incorrect Login Information");
                    alert.setHeaderText("Incorrect Username or Password");
                    alert.setContentText("Pleaser Try again.");
                    alert.show();
                } else if ((userName.getText().equals(user.getUsername()) && password.getText().equals(user.getPassword()))) {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("User.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.centerOnScreen();
                    stage.setScene(scene);
                    stage.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getCause());
            }
        }
    }

    @FXML
    void registerAcc(ActionEvent event) {

    }

    @FXML
    void registerAcc2(ActionEvent event) {

    }

    @FXML
    void showFinalRegPane(ActionEvent event) {

    }
}