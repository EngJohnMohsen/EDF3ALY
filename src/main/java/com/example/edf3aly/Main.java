package com.example.edf3aly;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Main extends Application implements Initializable {

    @FXML
    private TextField AccBalance;

    @FXML
    public Button Check_btn;

    @FXML
    public Button FinalRegAccPane_btn;

    @FXML
    public Pane FinalRegAcc_pane;

    @FXML
    private ComboBox<String> Account_Type;

    @FXML
    public TextField RegCheck;

    @FXML
    public TextField RegFirstN;

    @FXML
    public TextField RegLastN;

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

    @FXML
    private Button goBack_btn;

    @FXML
    private Button RegCreateAcc_btn;

    @FXML
    private TextField RegName;

    public String name;
    public String ssn;
    public String phoneNo;
    public String username;
    public String passWord;
    public Account account;

    //Premium account begins with 77 and Regular account begins with 88 and VIP account begins with 99
    public static Account myUserAcc = new Account(Account.AccountType.Credit, "77555400", 15000.00);
    public static Account myUserAcc2 = new Account(Account.AccountType.Savings, "88565401", 2000.00);
    public static Account myUserAcc3 = new Account(Account.AccountType.Checking, "99165481", 600000.00);

    public static User myUser = new User("Tamer", "1345", "123400", "Tam1", "wordpass", myUserAcc);
    public static User myUser2 = new User("Ahmed", "8647", "004321", "Ahm1", "wrongpass", myUserAcc2);
    public static User myUser3 = new User("Mohamed", "5279", "001234", "Mo1", "Mo1", myUserAcc3);

    static List<User> sysUsers = new ArrayList<>();

    String[] AccountType = new String[]{"Credit", "Savings", "Payments"};

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
        if (RegCheck.getText().equals("") || RegUserName.getText().equals("") || RegPassword.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Fields");
            alert.setHeaderText("Fields are empty");
            alert.setContentText("Please fill in the field.");
            alert.show();
        } else {
            try {
                String ID = RegCheck.getText();
                ssn = ID;
                if (findUserID(ID, sysUsers) != null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Account already exists");
                    alert.setContentText("The National ID you entered belongs to an account");
                    alert.show();
                    createAcc_pane.setVisible(false);
                    login_pane.setVisible(true);
                } else {
                    createAcc_pane.setVisible(false);
                    FinalRegAcc_pane.setVisible(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void registerAcc2() {
        if (!this.RegName.getText().equals("") && !this.AccBalance.getText().equals("") &&
                !this.RegCheck.getText().equals("") && !this.RegPhoneN.getText().equals("") && this.Account_Type.getValue() != null) {
            try {
                String userName = this.RegUserName.getText();
                String password = this.RegPassword.getText();
                String name = this.RegName.getText();
                String nationalID = this.RegCheck.getText();
                String phoneNumber = this.RegPhoneN.getText();
                String accountType = this.Account_Type.getValue().toString();
                double balance = Double.parseDouble(this.AccBalance.getText());
//                Account myUserAcc = new Account(accountType, balance);
                User user = new User(name, nationalID, phoneNumber, userName, password,myUserAcc);
                if (user.register(sysUsers)) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Account successfully created");
//                    String var10001 = this.RegName.getText();
//                    alert.setContentText(var10001 + " " + this.RegLastN.getText() + " successfully added");
                    alert.show();
                    this.RegCheck.clear();
                    this.RegUserName.clear();
                    this.RegPassword.clear();
                    this.RegName.clear();
                    this.RegCheck.clear();
                    this.RegPhoneN.clear();
                    this.AccBalance.clear();
                    this.Account_Type.setValue("Account Type");
                    this.FinalRegAcc_pane.setVisible(false);
                    this.login_pane.setVisible(true);
                }
            } catch (Exception var10) {
                var10.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Fields");
            alert.setHeaderText("Fields are empty");
            alert.setContentText("Please fill in the fields.");
            alert.show();
        }

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.Account_Type.getItems().addAll(this.AccountType);
        this.Account_Type.setOnAction(this::getAccountType);
    }

    public void getAccountType(ActionEvent event) {
        String Account = (String)this.Account_Type.getValue();
    }
}