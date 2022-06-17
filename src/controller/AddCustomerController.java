package controller;

import helper.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Countries;
import model.Customer;
import model.Division;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * This class is the controller for the add customer screen.
 * It handles the user input and calls the model to add the customer to the database.
 * @author Joshua Jarabek
 */
public class AddCustomerController implements Initializable { //This class is the controller for the add customer screen.
    ObservableList<Division> divisionsList = DivisionDB.getAllDivisions(); //The list of all divisions.
    @FXML
    private Button addCustomerSave; //The button to save the customer.
    @FXML
    private Button addCustomerCancel; //The button to cancel the add customer operation.
    @FXML
    private TextField addCustomerID; //The text field for the customer ID.
    @FXML
    private String name; //The name of the customer.
    @FXML
    private String phone; //The phone number of the customer.
    @FXML
    private String street; //The street of the customer.
    @FXML
    private String zip; //The zip code of the customer.
    @FXML
    private Countries selectedCountry; //The selected country of the customer.
    @FXML
    private Division selectedDivision; //The selected division of the customer.
    @FXML
    private TextField addCustomerName; //The text field for the name of the customer.
    @FXML
    private TextField addCustomerPhone; //The text field for the phone number of the customer.
    @FXML
    private TextField addCustomerStreet; //The text field for the street of the customer.
    @FXML
    private TextField addCustomerZip; //The text field for the zip code of the customer.
    @FXML
    private ComboBox<Countries> addCustomerCountry; //The combo box for the country of the customer.
    @FXML
    private ComboBox<Division> addCustomerDivision; //The combo box for the division of the customer.

    /**
     * Gets info.
     * This method gets the info from the text fields and combo boxes.
     */
    private void getInfo() { //Gets the information from the text fields and combo boxes.
        name = addCustomerName.getText().trim(); //Stores the name information in the name variable.
        phone = addCustomerPhone.getText().trim(); //Stores the phone information in the phone variable.
        street = addCustomerStreet.getText().trim(); //Stores the street information in the street variable.
        zip = addCustomerZip.getText().trim(); //Stores the zip information in the zip variable.
        selectedCountry = addCustomerCountry.getSelectionModel().getSelectedItem(); //Stores the country information in the country variable.
        selectedDivision = addCustomerDivision.getSelectionModel().getSelectedItem(); //Stores the division information in the division variable.
    }

    /**
     * Is valid boolean.
     * This method checks if the information is valid.
     * @return true if the information is valid, false if not.
     */
    private boolean isValid() { //Checks if the input info is valid.
        getInfo(); //Gets the information from the view.
        if (name.isEmpty() || phone.isEmpty() || street.isEmpty() || zip.isEmpty() || selectedCountry == null || selectedDivision == null) { //If any of the information is empty...
            AlertHelper alertHelper = new AlertHelper("Warning", "Missing Information", "Please fill in all of the fields", Alert.AlertType.WARNING); //...create an alert to warn the user and return false.
            alertHelper.showAlertButStay(); //...show the alert and return false.
            return false;
        } else if (zip.length() > 5) { //If the zip code is longer than 5 characters...
            AlertHelper alertHelper = new AlertHelper("Warning", "Invalid Zip Code", "The zip code must be 5 characters or less", Alert.AlertType.WARNING); //...create an alert to warn the user and return false.
            alertHelper.showAlertButStay(); //...show the alert and return false.
            return false;
        } else if (phone.length() > 10) {
            AlertHelper alertHelper = new AlertHelper("Warning", "Invalid Phone Number", "The phone number must be 10 characters or less", Alert.AlertType.WARNING);
            alertHelper.showAlertButStay();
            return false;
        } else { //If all the inputs are valid, it returns true.
            return true;
        }
    }

    /**
     * LAMBDA EXPRESSION: Sets selected country.
     * This method sets the selected country and division.
     *
     * <p>
     * The user selects a country from the country combo box.
     * If the user selects a country, the addCustomerDivision combo box is cleared.
     * The selected country is put into a variable called selectedCountry.
     * The selectedCountry variable is checked for null.
     * If the selectedCountry variable is not null, the lambda expression is run.
     * If the country ID is 1, for all of the divisions in the division list, if the division's country ID is 1, add it to the division combo box.
     * Repeat the above steps for country ID 2 and 3.
     * </p>
     */
    @FXML
    private void setSelectedCountry() { //When this method is called, it sets the selected country and division to the combo boxes.
        addCustomerDivision.getItems().clear(); //Clears the division combo box.
        Countries selectedCountry = addCustomerCountry.getValue(); //Gets the selected country.
        if (selectedCountry == null) { //If the selected country is null, it sets the division combo box to empty.
            return;
        }
        if (selectedCountry.getId() == 1) { //If the selected country ID is 1...
            divisionsList.forEach(division -> { //...for each division in the list...
                if (division.getCountryId() == 1) { //...if the division country ID is 1...
                    addCustomerDivision.getItems().add(division); //...add the division to the division combo box.
                }
            });
        } else if (selectedCountry.getId() == 2) { //If the selected country ID is 2...
            divisionsList.forEach(division -> { //...for each division in the list...
                if (division.getCountryId() == 2) { //...if the division's country ID is 2...
                    addCustomerDivision.getItems().add(division); //...add the division to the division combo box.
                }
            });
        } else if (selectedCountry.getId() == 3) { //If the selected country ID is 3...
            divisionsList.forEach(division -> { //...for each division in the list...
                if (division.getCountryId() == 3) { //...if the division's country ID is 3...
                    addCustomerDivision.getItems().add(division); //...add the division to the division combo box.
                }
            });
        }
    }

    /**
     * On action cancel.
     * This method is called when the cancel button is clicked.
     * It cancels adding a customer.
     * @param event the event that is called when the button is clicked.
     * @throws IOException the io exception
     */
    @FXML
    public void onActionCancel(ActionEvent event) throws IOException { //When the cancel button is clicked, it asks the user if they are sure they want to cancel.
        AlertHelper alert = new AlertHelper("Confirmation", "Cancellation", "Are you sure you want to cancel?", Alert.AlertType.CONFIRMATION); //Creates a new alert to confirm the cancellation.
        alert.showAlert(event, "MainMenuView.fxml"); //Shows the confirmation alert and returns to the main menu.
    }

    /**
     * On action save.
     * This method is called when the save button is clicked.
     * It saves the customer.
     * @param event the event of the save button.
     * @throws SQLException the sql exception
     * @throws IOException  the io exception
     */
    @FXML
    public void onActionSave(ActionEvent event) throws SQLException, IOException { //When the save button is clicked, it adds the customer to the database.
        if (isValid()) { //If the all inputs are valid, the information is stored in their respective variables and the customer is added to the database.
            int id = 0; //The ID of the customer is set to 0, because the database will assign a new ID.
            String name = addCustomerName.getText(); //Sets the text in the name text field to the name variable.
            String address = addCustomerStreet.getText(); //Sets the text in the address text field to the address variable.
            String postalCode = addCustomerZip.getText(); //Sets the text in the postal code text field to the postal code variable.
            String phone = addCustomerPhone.getText(); //Sets the text in the phone text field to the phone variable.
            int divisionId = addCustomerDivision.getSelectionModel().getSelectedItem().getId(); //Sets the selected division ID to the division ID variable.
            LocalDateTime createDate = LocalDateTime.now(); //Sets the current date and time to the creation date variable.
            String createdBy = LoginController.getActiveIdString(); //Sets the ID of the user who created the customer to the created by variable.
            String updatedBy = LoginController.getActiveIdString(); //Sets the ID of the user who updated the customer to the updated by variable.
            LocalDateTime lastUpdate = LocalDateTime.now(); //Sets the current date and time to the last update variable.
            Customer customer = new Customer(id, name, address, postalCode, phone, divisionId, createDate, createdBy, updatedBy, lastUpdate); //Creates a new customer object with the variables above.
            CustomerDB.addCustomer(customer); //Adds the customer object to the database.
            AlertHelper alertHelper = new AlertHelper("Information", "Success", "Customer added successfully", Alert.AlertType.INFORMATION); //Alerts the user that the customer was added.
            alertHelper.showAlert(event, "MainMenuView.fxml"); //Returns to the main menu.
        }
    }

    /**
     * Initialize.
     * This method is called when the view is initialized.
     * @param url            the url
     * @param resourceBundle the resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //This method is called when the view is loaded.
        addCustomerCountry.setItems(CountriesDB.getAllCountries()); //Sets the country combo box to the list of all countries.
    }
}
