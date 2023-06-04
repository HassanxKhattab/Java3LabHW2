/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Model.Account;
import View.ViewManager;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Yahya
 */
public class AccountsManagmentController implements Initializable {

    @FXML
    private Button usersManagmentPageBtn;
    @FXML
    private Button accountsPageBtn;
    @FXML
    private Button operationsPageBtn;
    @FXML
    private Button createNewAccountrBtn;
    @FXML
    private Button showAllAccountsBtn;
    @FXML
    private Button updateSelectedAccountBtn;
    @FXML
    private Button deleteSelectedAccountBtn;
    @FXML
    private Button searchAccountBtn;
    @FXML
    private TextField accontSearchTF;

    private TableView<Account> accountsTableView;
    @FXML
    private TableColumn<Account, Integer> idCol;
    @FXML
    private TableColumn<Account, String> usernameCol;
    @FXML
    private TableColumn<Account, String> accountNumberCol;
    @FXML
    private TableColumn<Account, String> balanceCol;
    @FXML
    private TableColumn<Account, String> carancyCol;
    @FXML
    private TableColumn<Account, String> createdCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        usernameCol.setCellValueFactory(new PropertyValueFactory("username"));
        accountNumberCol.setCellValueFactory(new PropertyValueFactory("password"));
        carancyCol.setCellValueFactory(new PropertyValueFactory("email"));
        createdCol.setCellValueFactory(new PropertyValueFactory("gender"));

    }

    @FXML
    private void showUsersManagmentPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneToUsersManagment();
    }

    @FXML
    private void showAccountsPage(ActionEvent event) {
    }

    @FXML
    private void showOperationsPage(ActionEvent event) {
    }

    @FXML
    private void showAccountCreationPage(ActionEvent event) {
    }

    @FXML
    private void showAllAccounts(ActionEvent event) {
    }

    @FXML
    private void updateSelectedAccount(ActionEvent event) {
        //check if there is an Accunt selected from the TableView
        if (accountsTableView.getSelectionModel().getSelectedItem() != null) {
            //store the selected user from the TableView in new user object
            Account selectedAccount = accountsTableView.getSelectionModel().getSelectedItem();

            //show an confirmation alert and make the deletion on confirm event
            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("User delete");
            deleteConfirmAlert.setContentText("Are you sure to delete this user ?");

            try {
                selectedAccount.update();
            } catch (SQLException ex) {
                Logger.getLogger(AccountsManagmentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AccountsManagmentController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Alert deletedSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
            deletedSuccessAlert.setTitle("Account updated");
            deletedSuccessAlert.setContentText("Account updated");
            deletedSuccessAlert.show();
        }
    }

    @FXML
    private void deleteSelectedAccount(ActionEvent event) {
        //check if there is an Accunt selected from the TableView
        if (accountsTableView.getSelectionModel().getSelectedItem() != null) {
            //store the selected user from the TableView in new user object
            Account selectedAccount = accountsTableView.getSelectionModel().getSelectedItem();

            //show an confirmation alert and make the deletion on confirm event
            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("User delete");
            deleteConfirmAlert.setContentText("Are you sure to delete this user ?");
            deleteConfirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    try {
                        selectedAccount.delete();
                    } catch (SQLException ex) {
                        Logger.getLogger(AccountsManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AccountsManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    Alert deletedSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
                    deletedSuccessAlert.setTitle("Account deleted");
                    deletedSuccessAlert.setContentText("Account deleted");
                    deletedSuccessAlert.show();
                }
            });

        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an user");
            warnAlert.setContentText("Please select an user from the table view");
            warnAlert.show();
        }
    }

    @FXML
    private void searchForAnAccount(ActionEvent event) {
    }

}
