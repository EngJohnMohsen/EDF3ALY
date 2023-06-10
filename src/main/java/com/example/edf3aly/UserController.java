package com.example.edf3aly;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import static com.example.edf3aly.Main.findUser;
import static com.example.edf3aly.Main.findUserAccount;

public class UserController implements Initializable {

    @FXML
    private ComboBox<String> AutomaticBill_Combo;

    @FXML
    private TextField BillAmount;

    @FXML
    private TextField BillName;

    @FXML
    private Button BuyItem_Btn;

    @FXML
    private Button BuyToTransaction_btn;

    @FXML
    private AnchorPane Buy_pane;

    @FXML
    private Button HistoryToTransaction_Btn;

    @FXML
    private TableView<String> HistoryTree;

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

        User_AccNum_Change.setText(Main.user.getAccount().getAccNo());
        User_AccType_Change.setText(Main.user.getAccount().getAccType());
        User_Name_Change.setText(Main.user.getName());
        User_SSN_Change.setText(Main.user.getSSN());
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
        if(TransferAmount.getText().isEmpty() || TransferToAccount.getText().isEmpty() || YourAccNoTransfer.getText().isEmpty()){
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
                        if(transfer.transferMoney()) {
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.setTitle("Information");
                            alert1.setHeaderText("Transfer Successful");
                            alert1.setContentText("You have successfully transferred " + TransferAmount.getText() + " to " + TransferToAccount.getText());
                            alert1.showAndWait();
                            showTransactioPane();
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
        UUID transactionID = UUID.randomUUID();
        if(BillAmount.getText().isEmpty() || BillName.getText().isEmpty() || AutomaticBill_Combo.getValue() != null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Pay Bill Error");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        }
        else{
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Pay Bill Confirmation");
                alert.setContentText("Are you sure you want to pay " + BillAmount.getText() + " to " + BillName.getText() + "?");
                if (alert.showAndWait().get() == ButtonType.OK) {
                    //Pay Bill
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Information");
                    alert1.setHeaderText("Pay Bill Successful");
                    alert1.setContentText("You have successfully paid " + BillAmount.getText() + " to " + BillName.getText());
                    alert1.showAndWait();
                    showTransactioPane();
                }
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
        if(ItemAmount_txt.getText().isEmpty() || StoreID_txt.getText().isEmpty() || ItemID_Txt.getText().isEmpty() || ItemName_Txt.getText().isEmpty()){
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
                    alert.setContentText("Are you sure you want to buy " + ItemAmount_txt.getText() + " " + StoreID_txt.getText() + "?");
                    if (alert.showAndWait().get() == ButtonType.OK) {
                        Buy_Item buy_item = new Buy_Item(transactionID, itemAmount, itemName, storeID, itemID);
                        buy_item.performTransaction();
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Information");
                        alert1.setHeaderText("Buy Successful");
                        alert1.setContentText("You have successfully bought " + ItemAmount_txt.getText() + " " + StoreID_txt.getText());
                        alert1.showAndWait();
                        showTransactioPane();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.AutomaticBill_Combo.getItems().addAll(this.AutomaticBill);
        this.AutomaticBill_Combo.setOnAction(this::getAutomatic);

        MainPageAccNo.setText(Main.user.getAccount().getAccNo());
        MainPageBalance.setText(String.valueOf(Main.user.getAccount().getAccBalance()));

    }

    public void getAutomatic(ActionEvent event) {
        String Auto = (String)this.AutomaticBill_Combo.getValue();
    }


}
