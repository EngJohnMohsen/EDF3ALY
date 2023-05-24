package com.example.edf3aly;

import javafx.application.Application;
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
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @FXML
    public Button Check_btn;

    @FXML
    public Button FinalRegAccPane_btn;

    @FXML
    public Pane FinalRegAcc_pane;

    @FXML
    public ComboBox<?> Gender_combo;

    @FXML
    public TextField RegCheck;

    @FXML
    public TextField RegFirstN;

    @FXML
    public TextField RegLastN;

    @FXML
    public TextField RegNationalID;

    @FXML
    public PasswordField RegPassword;

    @FXML
    public TextField RegPhoneN;

    @FXML
    public TextField RegUserName;

    @FXML
    public Pane createAcc_pane;

    @FXML
    public Pane login_pane;

    @FXML
    public PasswordField password;

    @FXML
    public TextField userName;

    public String name;
    public String ssn;
    public String phoneNo;
    public String username;
    public String passWord;
    public Account account;

    static User myUser = new User("Tamer", "1345", "123400", "Tam1", "wordpass");
    static User myUser2 = new User("Ahmed", "8647", "004321", "Ahm1", "wrongpass");
    static User myUser3 = new User("Mohamed", "5279", "001234", "Mo1", "Mo1");

    static List<User> sysUsers = new ArrayList<>();

    public static void main(String[] args) {
        sysUsers.add(myUser);
        sysUsers.add(myUser2);
        sysUsers.add(myUser3);

        launch();
    }

    public static User findUser(String userName, List<User> userList) {
        if (!userList.isEmpty()) {
            for (User user : userList) {
                if (user.getUsername().equals(userName)) {
                    System.out.println("Found");
                    return user;
                } else {
                    System.out.println("Not Found");
                }
            }
        }
        return null;
    }

    public static User findUserID(String nationalID, List<User> userList) {

        if (!userList.isEmpty()) {
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getSSN().equals(nationalID)) {
                    System.out.println("Found");
                    return userList.get(i);
                } else {
                    System.out.println("Not Found");
                }
            }
        }
        return null;
    }

    @FXML
    public void showFinalRegPane() {
        FinalRegAcc_pane.setVisible(true);
        createAcc_pane.setVisible(false);
    }

    @FXML
    public void createAcc() {
        login_pane.setVisible(false);
        createAcc_pane.setVisible(true);
    }

    @FXML
    public void backToMain() {
        login_pane.setVisible(true);
        createAcc_pane.setVisible(false);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Welcome!");
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void login(ActionEvent event) throws IOException {
        if (userName.getText().equals("") || password.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Fields");
            alert.setHeaderText("Data Fields are empty");
            alert.setContentText("Please fill in the fields.");
            alert.show();
        } else {
            String userNameText = userName.getText();
            String passwordText = password.getText();
            User user = (User) findUser(userNameText, sysUsers);
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
    public void registerAcc(ActionEvent event) {
        if (RegCheck.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Field");
            alert.setHeaderText("Field are empty");
            alert.setContentText("Please fill in the field.");
            alert.show();
        } else {
            try {
                String ID = RegCheck.getText();
                ssn = ID;
                if (findUser(ID, sysUsers) != null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Account already exists");
                    alert.setContentText("The National ID you entered belongs to an account");
                    alert.show();
                    createAcc_pane.setVisible(false);
                    login_pane.setVisible(true);
                } else {
                    RegUserName.setDisable(false);
                    RegPassword.setDisable(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}