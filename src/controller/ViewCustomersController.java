package controller;

import helper.AlertHelper;
import helper.CustomerDB;
import helper.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The ViewCustomersController class is the controller for the view customers scene.
 *
 * @author Joshua Jarabek
 */
public class ViewCustomersController implements Initializable { //Controller for the view customers scene
    /**
     * The constant selectedCustomer.
     */
    public static Customer selectedCustomer; //Customer that is selected in the table
    /**
     * The Customer exit button.
     */
    @FXML
    private Button customerExitButton; //Exit button
    /**
     * The Customer id col.
     */
    @FXML
    private TableColumn<Customer, Integer> customerIdCol; //Customer ID column
    /**
     * The Customer name col.
     */
    @FXML
    private TableColumn<Customer, String> customerNameCol; //Customer name column
    /**
     * The Customer zip col.
     */
    @FXML
    private TableColumn<Customer, String> customerZipCol; //Customer zip column
    /**
     * The Customer phone col.
     */
    @FXML
    private TableColumn<Customer, String> customerPhoneCol; //Customer phone column
    /**
     * The Customer address col.
     */
    @FXML
    private TableColumn<Customer, String> customerAddressCol; //Customer address column
    /**
     * The Customer date created col.
     */
    @FXML
    private TableColumn<Customer, String> customerDateCreatedCol; //Customer date created column
    /**
     * The Customer created by col.
     */
    @FXML
    private TableColumn<Customer, String> customerCreatedByCol; //Customer created by column
    /**
     * The Customer last updated col.
     */
    @FXML
    private TableColumn<Customer, LocalDateTime> customerLastUpdatedCol; //Customer last updated column
    /**
     * The Customer last updated by col.
     */
    @FXML
    private TableColumn<Customer, String> customerLastUpdatedByCol; //Customer last updated by column
    /**
     * The Customer division id col.
     */
    @FXML
    private TableColumn<Customer, Integer> customerDivisionIdCol; //Customer division ID column
    /**
     * The Add customer button.
     */
    @FXML
    private Button addCustomerButton; //Add customer button
    /**
     * The Modify customer button.
     */
    @FXML
    private Button modifyCustomerButton; //Modify customer button
    /**
     * The Delete customer button.
     */
    @FXML
    private Button deleteCustomerButton; //Delete customer button
    /**
     * The Customer table.
     */
    @FXML
    private TableView<Customer> customerTable; //Customer table

    /**
     * On action add customer.
     * This method is called when the add customer button is clicked.
     * The method switches to the add customer scene.
     *
     * @param event the add customer button is clicked
     * @throws IOException the io exception
     */
    @FXML
    void onActionAddCustomer(ActionEvent event) throws IOException { //Method that is called when the add customer button is pressed
        SceneSwitcher.switchScreen(event, "AddCustomer.fxml"); //Switch to the add customer scene
    }

    /**
     * On action delete customer.
     * This method is called when the delete customer button is clicked.
     * The method prompts the user to confirm the deletion of the customer.
     *
     * @param event the event
     * @throws SQLException the sql exception
     * @throws IOException  the io exception
     */
    @FXML
    void onActionDeleteCustomer(ActionEvent event) throws SQLException, IOException { //Method that is called when the delete button is pressed
        selectedCustomer = customerTable.getSelectionModel().getSelectedItem(); //Get the selected customer
        AlertHelper alertHelper = new AlertHelper("Confirmation", "Delete Customer", "Are you sure you want to delete this customer?", Alert.AlertType.CONFIRMATION); //Create a new alert helper
        alertHelper.showAlertButStay(); //Show the alert helper
        Optional<ButtonType> result = alertHelper.getResult(); //Get the result of the alert helper
        if (result.isPresent() && result.get() == ButtonType.OK) { //If the user clicks OK...
            CustomerDB.deleteCustomer(selectedCustomer); //...delete the customer from the database
        }
        customerTable.getItems().clear(); //Clear the table
        customerTable.getItems().addAll(CustomerDB.getAllCustomers()); //Add all customers to the table
    }

    /**
     * On action exit.
     * This method is called when the exit button is clicked.
     * The method switches to the main menu scene.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void onActionExit(ActionEvent event) throws IOException { //Method that is called when the exit button is pressed
        SceneSwitcher.switchScreen(event, "MainMenuView.fxml"); //Switch to the main menu scene
    }

    /**
     * On action modify customer.
     * This method is called when the "modify customer" button is clicked.
     * The method switches to the "modify customer" scene.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void onActionModifyCustomer(ActionEvent event) throws IOException { //Method that is called when the modify button is pressed
        selectedCustomer = customerTable.getSelectionModel().getSelectedItem(); //Get the selected customer
        SceneSwitcher.switchScreen(event, "UpdateCustomerView.fxml"); //Switch to the update customer scene
    }

    /**
     * Initialize.
     * This method is called when the scene is initialized.
     * The method adds all customers to the table.
     *
     * @param url            the url
     * @param resourceBundle the resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //Method that is called when the scene is initialized
        customerTable.setItems(CustomerDB.getAllCustomers()); //Set the customer table to the list of all customers
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("id")); //Set the customer ID column to the customer ID property in the database
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("name")); //Set the customer name column to the customer name property in the database
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("address")); //Set the customer address column to the customer address property in the database
        customerZipCol.setCellValueFactory(new PropertyValueFactory<>("postalCode")); //Set the customer zip column to the customer zip property in the database
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone")); //Set the customer phone column to the customer phone property in the database
        customerDateCreatedCol.setCellValueFactory(new PropertyValueFactory<>("createDate")); //Set the customer date created column to the customer date created property in the database
        customerDivisionIdCol.setCellValueFactory(new PropertyValueFactory<>("divisionId")); //Set the customer division ID column to the customer division ID property in the database
        customerCreatedByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy")); //Set the customer created by column to the customer created by property in the database
        customerLastUpdatedCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdate")); //Set the customer last updated column to the customer last updated property in the database
        customerLastUpdatedByCol.setCellValueFactory(new PropertyValueFactory<>("updatedBy")); //Set the customer last updated by column to the customer last updated by property in the database
        customerTable.getSelectionModel().selectFirst(); //Select the first customer in the table
    }
}
