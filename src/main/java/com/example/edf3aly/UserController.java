package com.example.edf3aly;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserController {

    @FXML
    private TextField BillAmount;

    @FXML
    private TextField BillName;

    @FXML
    private Button BuyToTransaction_btn;

    @FXML
    private AnchorPane Buy_pane;

    @FXML
    private Button HistoryToTransaction_Btn;

    @FXML
    private TableView<Statement_Generator> HistoryTree;

    @FXML
    private AnchorPane History_pane;

    @FXML
    private Button Home_btn;

    @FXML
    private AnchorPane Home_pane;

    @FXML
    private Button Logout;

    @FXML
    private Button PayBillToTransaction_Btn;

    @FXML
    private Button PayBill_Btn;

    @FXML
    private AnchorPane PayBill_pane;

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

//        User_AccNum_Change.setText(String.valueOf(Main));
//        User_AccType_Change.setText(LoginController.account.getAccType());
//        User_Name_Change.setText(LoginController.user.getName());
//        User_SSN_Change.setText(String.valueOf(LoginController.user.getSSN()));
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

}
