package com.example.edf3aly;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;

import static com.example.edf3aly.Main.findUserAccount;

public class UserController implements Initializable {

    Date date = new Date();

    @FXML
    private ComboBox<String> AutomaticBill_Combo;

    @FXML
    private TextField BillAmount;

    @FXML
    private TextField BillName;

    @FXML
    private TextField BillDate;

    @FXML
    private Button BuyItem_Btn;

    @FXML
    private Button BuyToTransaction_btn;

    @FXML
    private AnchorPane Buy_pane;

    @FXML
    private Button HistoryToTransaction_Btn;

    @FXML
    private TableView<Transactions> HistoryTree;

    @FXML
    private AnchorPane History_pane;

    @FXML
    private Button Home_btn;

    @FXML
    private AnchorPane Home_pane;

    @FXML
    private TextField ItemAmount_txt;

    @FXML
    private TextField ItemID_Txt;

    @FXML
    private TextField ItemName_Txt;

    @FXML
    private Button Logout;

    @FXML
    private Button PayBillToTransaction_Btn;

    @FXML
    private Button PayBill_Btn;

    @FXML
    private AnchorPane PayBill_pane;

    @FXML
    private TextField StoreID_txt;

    @FXML
    private Button ToBuyPane_Btn;

    @FXML
    private Button ToHistoryPane_Btn;

    @FXML
    private Button ToPayBillPane_Btn;

    @FXML
    private Button ToTransferPane_Btn;

    @FXML
    private TableColumn<Transactions, Double> TransactionHistory_Amount;

    @FXML
    private TableColumn<Transactions, Date> TransactionHistory_Date;

    @FXML
    private TableColumn<Transactions, UUID> TransactionHistory_ID;

    @FXML
    private TableColumn<Transactions, String> TransactionHistory_type;

    @FXML
    private AnchorPane Transaction_pane;

    @FXML
    private TextField TransferAmount;

    @FXML
    private TextField TransferToAccount;

    @FXML
    private Button TransferToTransaction_btn;

    @FXML
    private Button Transfer_Btn;

    @FXML
    private AnchorPane Transfer_pane;

    @FXML
    private AnchorPane UserDetails_Pane;

    @FXML
    private Button UserToHome_Btn;

    @FXML
    private Label User_AccNum_Change;

    @FXML
    private Label User_AccType_Change;

    @FXML
    private Label User_Name_Change;

    @FXML
    private Label User_SSN_Change;

    @FXML
    private TextField YourAccNoTransfer;

    @FXML
    private Button membr_btn;

    @FXML
    private Label MainPageAccNo;

    @FXML
    private Label MainPageBalance;


    String[] AutomaticBill = new String[]{"Yes", "No"};

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
    
    public void showTransactioPane() {
        Transaction_pane.setVisible(true);
        Home_pane.setVisible(false);
        Transfer_pane.setVisible(false);
        PayBill_pane.setVisible(false);
        Buy_pane.setVisible(false);
        History_pane.setVisible(false);
        UserDetails_Pane.setVisible(false);
    }
    
    public void showTransferPane(){
        Transaction_pane.setVisible(false);
        Home_pane.setVisible(false);
        Transfer_pane.setVisible(true);
        PayBill_pane.setVisible(false);
        Buy_pane.setVisible(false);
        History_pane.setVisible(false);
        UserDetails_Pane.setVisible(false);
    }
    
    public void showPayBillPane(){
        Transaction_pane.setVisible(false);
        Home_pane.setVisible(false);
        Transfer_pane.setVisible(false);
        PayBill_pane.setVisible(true);
        Buy_pane.setVisible(false);
        History_pane.setVisible(false);
        UserDetails_Pane.setVisible(false);
    }
    
    public void showBuyPane(){
        Transaction_pane.setVisible(false);
        Home_pane.setVisible(false);
        Transfer_pane.setVisible(false);
        PayBill_pane.setVisible(false);
        Buy_pane.setVisible(true);
        History_pane.setVisible(false);
        UserDetails_Pane.setVisible(false);
    }
    
    public void showHistoryPane(){
        Transaction_pane.setVisible(false);
        Home_pane.setVisible(false);
        Transfer_pane.setVisible(false);
        PayBill_pane.setVisible(false);
        Buy_pane.setVisible(false);
        History_pane.setVisible(true);
        UserDetails_Pane.setVisible(false);
    }

    public void showUserDetailsPane(){
        Transaction_pane.setVisible(false);
        Home_pane.setVisible(false);
        Transfer_pane.setVisible(false);
        PayBill_pane.setVisible(false);
        Buy_pane.setVisible(false);
        History_pane.setVisible(false);
        UserDetails_Pane.setVisible(true);

    }
    
    public void returnToHome(){
        Home_pane.setVisible(true);
        UserDetails_Pane.setVisible(false);
        Transfer_pane.setVisible(false);
        PayBill_pane.setVisible(false);
        Buy_pane.setVisible(false);
        History_pane.setVisible(false);
        Transaction_pane.setVisible(false);
    }
    
    public void returnToTransaction(ActionEvent event){
        showTransactioPane();
    }

    public void Transfer(ActionEvent event) {
        String accNum = TransferToAccount.getText();
        double amount = Double.parseDouble(TransferAmount.getText());
        String yourAccNum = YourAccNoTransfer.getText();
        UUID transactionID = UUID.randomUUID();
        if(TransferAmount.getText().equals("") || TransferToAccount.getText().equals("") || YourAccNoTransfer.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Transfer Error");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        }
        else{
            try {
                if(!findUserAccount(TransferToAccount.getText(), Main.sysUsers) || !findUserAccount(YourAccNoTransfer.getText(), Main.sysUsers)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Transfer Error");
                    alert.setContentText("Account does not exist");
                    alert.showAndWait();
                }else if(amount <= Main.findUserAccount2(yourAccNum, Main.sysUsers).getAccBalance()){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText("Transfer Confirmation");
                    alert.setContentText("Are you sure you want to transfer " + TransferAmount.getText() + " to " + TransferToAccount.getText() + "?");
                    if (alert.showAndWait().get() == ButtonType.OK) {
                        Transfer transfer = new Transfer(transactionID, amount, Main.findUserAccount2(accNum, Main.sysUsers), Main.findUserAccount2(yourAccNum, Main.sysUsers));
                        Transactions transactions = new Transactions(transactionID, "Transfer", amount, date);
                        if(transfer.TransferMoney()) {
                            Main.user.getAccount().newBalance(Main.user.getAccount().getAccBalance(), amount, "Transfer");
                            MainPageBalance.setText(String.valueOf(Main.user.getAccount().getAccBalance()));
//                            Main.user.getAccount().addTransaction(transactions);
                            Main.user.getAccount().transactionHistory();
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.setTitle("Information");
                            alert1.setHeaderText("Transfer Successful");
                            alert1.setContentText("You have successfully transferred " + TransferAmount.getText() + " to " + TransferToAccount.getText());
                            alert1.showAndWait();
                            showTransactioPane();
                            TransferAmount.clear();
                            TransferToAccount.clear();
                            YourAccNoTransfer.clear();
                        } else {
                            Alert alert1 = new Alert(Alert.AlertType.ERROR);
                            alert1.setTitle("Error");
                            alert1.setHeaderText("Transfer Error");
                            alert1.setContentText("You do not have enough money in your account");
                            alert1.showAndWait();
                        }
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Transfer Error");
                    alert.setContentText("You do not have enough money in your account");
                    alert.showAndWait();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
                exception.getCause();
            }
        }
    }

    public void Pay_Bill(ActionEvent event){
        String billName = BillName.getText();
        double amount = Double.parseDouble(BillAmount.getText());
        int day = Integer.parseInt(BillDate.getText());
        boolean automatic = AutomaticBill_Combo.getValue().equals("Yes");
        boolean notAutomatic = AutomaticBill_Combo.getValue().equals("No");
        UUID transactionID = UUID.randomUUID();
        if(BillAmount.getText().isEmpty() || BillName.getText().isEmpty() || BillDate.getText().isEmpty()
                || AutomaticBill_Combo.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Pay Bill Error");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        }
        else{
            try {
                if(automatic) {
                    if(day == 11) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation");
                        alert.setHeaderText("Pay Bill Confirmation");
                        alert.setContentText("Are you sure you want to pay " + BillAmount.getText() + " to " + BillName.getText() + " automatically every month?");
                        if (alert.showAndWait().get() == ButtonType.OK) {
                            Pay_Bills pay_bills = new Pay_Bills(transactionID, amount, billName, true);
                            pay_bills.setPaymentDay(day);
                            pay_bills.setAccount(Main.user.getAccount());
                            pay_bills.performTransaction();
                            Main.user.getAccount().transactionHistory();
                            Main.user.getAccount().newBalance(Main.user.getAccount().getAccBalance(), amount, "Pay_Bills");
                            MainPageBalance.setText(String.valueOf(Main.user.getAccount().getAccBalance()));
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.setTitle("Information");
                            alert1.setHeaderText("Pay Bill Successful");
                            alert1.setContentText("You have successfully paid " + BillAmount.getText() + " to " + BillName.getText() + " automatically every month");
                            alert1.showAndWait();
                            showTransactioPane();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Pay Bill Error");
                        alert.setContentText("Today is not " + day + "th of the month payment will be done automatically on " + day + "th of the month");
                        alert.showAndWait();
                    }
                } else if(notAutomatic){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText("Pay Bill Confirmation");
                    alert.setContentText("Are you sure you want to pay " + BillAmount.getText() + " to " + BillName.getText() + "?");
                    if (alert.showAndWait().get() == ButtonType.OK) {
                        Pay_Bills pay_bills = new Pay_Bills(transactionID, amount, billName, false);
                        pay_bills.setAccount(Main.user.getAccount());
                        pay_bills.setPaymentDay(day);
                        pay_bills.performTransaction();
                        Main.user.getAccount().newBalance(Main.user.getAccount().getAccBalance(), amount, "Pay_Bills");
                        MainPageBalance.setText(String.valueOf(Main.user.getAccount().getAccBalance()));
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Information");
                        alert1.setHeaderText("Payment unsuccessful");
                        alert1.setContentText("You cannot complete this payment at this time");
                        alert1.showAndWait();
                        showTransactioPane();
                    }
                }
                BillAmount.clear();
                BillName.clear();
                BillDate.clear();
                AutomaticBill_Combo.setValue("Automatic");
            } catch (Exception exception) {
                exception.printStackTrace();
                exception.getCause();
            }
        }
    }

    public void BuyItem(ActionEvent event) {
        int itemID = Integer.parseInt(ItemID_Txt.getText());
        String itemName = ItemName_Txt.getText();
        int itemAmount = Integer.parseInt(ItemAmount_txt.getText());
        int storeID = Integer.parseInt(StoreID_txt.getText());
        UUID transactionID = UUID.randomUUID();
        if(ItemAmount_txt.getText().equals("") || StoreID_txt.getText().equals("") || ItemID_Txt.getText().equals("") || ItemName_Txt.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Buy Error");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        }
        else{
            try {
                if(itemAmount <= Main.user.getAccount().getAccBalance()) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText("Buy Confirmation");
                    alert.setContentText("Are you sure you want to buy " + ItemName_Txt.getText() + "?");
                    if (alert.showAndWait().get() == ButtonType.OK) {
                        Buy_Item buy_item = new Buy_Item(transactionID, itemAmount, itemName, storeID, itemID);
                        buy_item.setAccount(Main.user.getAccount());
                        buy_item.performTransaction();
                        Main.user.getAccount().newBalance(Main.user.getAccount().getAccBalance(), itemAmount, "Buy_Item");
                        MainPageBalance.setText(String.valueOf(Main.user.getAccount().getAccBalance()));
                        Main.user.getAccount().transactionHistory();
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Information");
                        alert1.setHeaderText("Buy Successful");
                        alert1.setContentText("You have successfully bought " + ItemAmount_txt.getText() + " " + ItemID_Txt.getText());
                        alert1.showAndWait();
                        showTransactioPane();
                        ItemName_Txt.clear();
                        ItemAmount_txt.clear();
                        StoreID_txt.clear();
                        ItemID_Txt.clear();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Buy Error");
                    alert.setContentText("You do not have enough money in your account");
                    alert.showAndWait();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
                exception.getCause();
            }
        }
    }

    ObservableList<Transactions> list = FXCollections.observableArrayList(Main.user.getAccount().transactions);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.AutomaticBill_Combo.getItems().addAll(this.AutomaticBill);
        this.AutomaticBill_Combo.setOnAction(this::getAutomatic);

        MainPageAccNo.setText(Main.user.getAccount().getAccNo());
        MainPageBalance.setText(String.valueOf(Main.user.getAccount().getAccBalance()));



        User_AccNum_Change.setText(Main.user.getAccount().getAccNo());
        User_AccType_Change.setText(Main.user.getAccount().getAccType());
        User_Name_Change.setText(Main.user.getName());
        User_SSN_Change.setText(Main.user.getSSN());

        TransactionHistory_ID.setCellValueFactory(new PropertyValueFactory<Transactions, UUID>("transactionID"));
        TransactionHistory_type.setCellValueFactory(new PropertyValueFactory<Transactions, String>("type"));
        TransactionHistory_Amount.setCellValueFactory(new PropertyValueFactory<Transactions, Double>("amount"));
        TransactionHistory_Date.setCellValueFactory(new PropertyValueFactory<Transactions, Date>("date"));
        HistoryTree.getColumns().addAll(TransactionHistory_ID, TransactionHistory_type, TransactionHistory_Amount, TransactionHistory_Date);
        HistoryTree.setEditable(true);
        HistoryTree.setItems(list);

    }

    public void getAutomatic(ActionEvent event) {
        String Auto = (String)this.AutomaticBill_Combo.getValue();
    }


}
