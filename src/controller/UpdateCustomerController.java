package controller;

import helper.AlertHelper;
import helper.CountriesDB;
import helper.CustomerDB;
import helper.DivisionDB;
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
import java.util.ResourceBundle;

/**
 * The class UpdateCustomerController is used to update a customer.
 *
 * @author Joshua Jarabek
 */
public class UpdateCustomerController implements Initializable { //This class is the controller for the update customer screen.
    /**
     * The Selected customer id.
     */
    public int selectedCustomerId = ViewCustomersController.selectedCustomer.getId(); //The selected customer's ID.
    /**
     * The Selected country.
     */
    public Countries selectedCountry; //The selected country of the customer.
    /**
     * The Modify customer save.
     */
    @FXML
    private Button modifyCustomerSave; //The button to save the customer.
    /**
     * The Modify customer cancel.
     */
    @FXML
    private Button modifyCustomerCancel; //The button to cancel the update customer operation.
    /**
     * The Name.
     */
    @FXML
    private String name; //The name of the customer.
    /**
     * The Phone.
     */
    @FXML
    private String phone; //The phone number of the customer.
    /**
     * The Zip.
     */
    @FXML
    private String zip; //The zip code of the customer.
    /**
     * The Full address.
     */
    @FXML
    private String fullAddress; //The full address of the customer.
    /**
     * The Selected division.
     */
    @FXML
    private Division selectedDivision; //The selected division of the customer.
    /**
     * The Modify customer id.
     */
    @FXML
    private TextField modifyCustomerID; //The text field for the customer ID.
    /**
     * The Modify customer name.
     */
    @FXML
    private TextField modifyCustomerName; //The text field for the name of the customer.
    /**
     * The Modify customer phone.
     */
    @FXML
    private TextField modifyCustomerPhone; //The text field for the phone number of the customer.
    /**
     * The Modify customer street.
     */
    @FXML
    private TextField modifyCustomerStreet; //The text field for the street of the customer.
    /**
     * The Modify customer zip.
     */
    @FXML
    private TextField modifyCustomerZip; //The text field for the zip code of the customer.
    /**
     * The Modify customer country.
     */
    @FXML
    private ComboBox<Countries> modifyCustomerCountry; //The combo box for the country of the customer.
    /**
     * The Modify customer division.
     */
    @FXML
    private ComboBox<Division> modifyCustomerDivision; //The combo box for the division of the customer.
    /**
     * The Divisions list.
     */
    @FXML
    private ObservableList<Division> divisionsList = DivisionDB.getAllDivisions(); //The list of all divisions.
    /**
     * The Countries list.
     */
    @FXML
    private ObservableList<Countries> countriesList = CountriesDB.getAllCountries(); //The list of all countries.

    /**
     * Gets info.
     * When this method is called, the information in the text fields and combo boxes are retrieved and stored in the variables.
     */
    private void getInfo() { //Gets the information from the text fields and combo boxes.
        name = modifyCustomerName.getText().trim(); //Stores the name information in the name variable.
        phone = modifyCustomerPhone.getText().trim(); //Stores the phone information in the phone variable.
        fullAddress = modifyCustomerStreet.getText().trim(); //Stores the street information in the street variable.
        zip = modifyCustomerZip.getText().trim(); //Stores the zip information in the zip variable.
        selectedCountry = modifyCustomerCountry.getSelectionModel().getSelectedItem(); //Stores the country information in the country variable.
        selectedDivision = modifyCustomerDivision.getSelectionModel().getSelectedItem(); //Stores the division information in the division variable.
    }

    /**
     * Is valid boolean.
     * When this method is called, the information in the text fields and combo boxes are checked to see if it is valid.
     *
     * @return the boolean true if the information is valid, false if it is not.
     */
    private boolean isValid() { //Checks if the information is valid.
        getInfo(); //Gets the information from the text fields and combo boxes.
        if(name.isEmpty() || phone.isEmpty() || fullAddress.isEmpty() || zip.isEmpty() || selectedCountry == null || selectedDivision == null) { //If any of the information is empty...
            AlertHelper alertHelper = new AlertHelper("Warning", "Missing information", "Please fill in all the fields.", Alert.AlertType.WARNING); //...show a warning alert and return false.
            alertHelper.showAlertButStay();
            return false;
        } else if (zip.length() != 5) { //If the zip code is not 5 characters long...
            AlertHelper alertHelper = new AlertHelper("Warning", "Invalid zip code", "Please enter a valid zip code.", Alert.AlertType.WARNING); //...show a warning alert and return false.
            alertHelper.showAlertButStay();
            return false;
        } else { //If all the information is valid return true.
            return true;
        }
    }

    /**
     * LAMBDA METHOD: On action select country.
     * When this method is called, the country combo box is updated to show the correct countries for the selected division.
     *
     * <p>
     * - The method is called when the user selects a country in the customer view.
     * - The method needs to call a new method (`updateDivisions`) that is used for updating the division combo box.
     * - The method needs to check the country id to determine which division should be added to the combo box.
     * - The method needs to loop through the division list and add the correct division to the combo box.
     * </p>
     * <p>
     * If this was done with a for-loop, it would be written as follows:
     *
     * <code>
     * for (int i = 0; i < divisionsList.size(); i++) {
     * if (selectedCountry.getId() == 1) {
     * if (divisionsList.get(i).getCountryId() == 1) {
     * modifyCustomerDivision.getItems().add(divisionsList.get(i));
     * }
     * } else if (selectedCountry.getId() == 2) {
     * if (divisionsList.get(i).getCountryId() == 2) {
     * modifyCustomerDivision.getItems().add(divisionsList.get(i));
     * }
     * } else if (selectedCountry.getId() == 3) {
     * if (divisionsList.get(i).getCountryId() == 3) {
     * modifyCustomerDivision.getItems().add(divisionsList.get(i));
     * }
     * }
     * }
     * </code>
     * <p>
     * As you can see, the lambda expression is much more concise and readable compared to the for-loop.
     *
     * @param event the select country box is changed.
     */
    @FXML
    private void onActionSelectCountry(ActionEvent event) { //When the user selects a country from the combo box...
        modifyCustomerDivision.getItems().clear(); //...clear the division combo box.
        Countries selectedCountry = modifyCustomerCountry.getValue(); //Get the selected country.
        if (selectedCountry == null) { //If the selected country is null, return nothing in the division combo box.
            return;
        }
        //LAMBDA EXPRESSION
        if (selectedCountry.getId() == 1) { //If the selected country is the US...
            divisionsList.forEach(division -> { //...for each division in the list...
                if (division.getCountryId() == 1) { //...if the division is in the US...
                    modifyCustomerDivision.getItems().add(division); //...add the division to the division combo box.
                }
            });
        } else if (selectedCountry.getId() == 2) { //If the selected country is the Canada...
            divisionsList.forEach(division -> { //...for each division in the list...
                if (division.getCountryId() == 2) { //...if the division is in the Canada...
                    modifyCustomerDivision.getItems().add(division); //...add the division to the division combo box.
                }
            });
        } else if (selectedCountry.getId() == 3) { //If the selected country is the Mexico...
            divisionsList.forEach(division -> { //...for each division in the list...
                if (division.getCountryId() == 3) { //...if the division is in the Mexico...
                    modifyCustomerDivision.getItems().add(division); //...add the division to the division combo box.
                }
            });
        }
    }

    /**
     * On action cancel.
     * When this method is called, the updating of the customer is cancelled and the user is taken back to the customer view.
     *
     * @param event the cancel button is clicked.
     * @throws IOException the io exception
     */
    @FXML
    private void onActionCancel(ActionEvent event) throws IOException { //When the user clicks the cancel button...
        AlertHelper alert = new AlertHelper("Confirmation", "Cancel Customer Update", "Are you sure you want to cancel the update?", Alert.AlertType.CONFIRMATION); //...show a confirmation alert...
        alert.showAlert(event, "ViewCustomersView.fxml"); //...and open the view customers screen.
    }

    /**
     * On action save.
     * When this method is called, the customer is updated and the user is taken back to the customer view.
     *
     * @param event the event
     * @throws SQLException the sql exception
     * @throws IOException  the io exception
     */
    @FXML
    private void onActionSave(ActionEvent event) throws SQLException, IOException { //When the user clicks the save button...
        if (isValid()) { //If the information is valid...
            for (Customer customer : CustomerDB.getAllCustomers()) { //...for each customer in the database...
                if (customer.getId() == selectedCustomerId) { //...if the customer's ID is the same as the selected customer's ID...
                    customer.setName(modifyCustomerName.getText()); //...set the customer's name to the name in the text field.
                    customer.setAddress(modifyCustomerStreet.getText()); //...set the customer's address to the address in the text field.
                    customer.setPostalCode(modifyCustomerZip.getText()); //...set the customer's postal code to the postal code in the text field.
                    customer.setPhone(modifyCustomerPhone.getText()); //...set the customer's phone number to the phone number in the text field.
                    customer.setDivisionId(modifyCustomerDivision.getSelectionModel().getSelectedItem().getId()); //...set the customer's division ID to the division ID in the combo box.
                    customer.setUpdatedBy(LoginController.getActiveIdString()); //...set the customer's updated by to the ID of the user who updated the customer.
                    customer.setLastUpdate(java.time.LocalDateTime.now()); //...set the customer's last update to the current date and time.
                    CustomerDB.updateCustomer(customer); //Update the customer in the database.
                }
            }
            AlertHelper alert = new AlertHelper("Information","Success", "Customer updated successfully", Alert.AlertType.INFORMATION); //Alert the user that the customer was updated successfully.
            alert.showAlert(event, "ViewCustomersView.fxml"); //Open the view customers screen.
        }
    }

    /**
     * LAMBDA METHOD: Transfer customer.
     * When this method is called, the selected customer is transferred from the customer view to the update customer view.
     *
     * <p>
     * The method takes in a Customer object.
     * The method loops through the database and finds the customer with the same ID as the selected customer.
     * The method then sets the text fields and combo boxes to the customer's information.
     * The method then loops through the divisions list and finds the division with the same ID as the customer's division ID.
     * The method then sets the combo box to the division.
     * The method then loops through the countries list and finds the country with the same ID as the division's country ID.
     * The method then sets the combo box to the country.
     * The method then sets the selected country to the country.
     * The method then uses a lambda expression to loop through the divisions list and add the divisions to the combo box.
     * The method then catches a null pointer exception if the customer's division is null.
     * <p>
     * The lambda expression is important because it allows me to loop through the divisions list and add the divisions to the combo box.
     * </p>
     *
     * @param selectCustomer the select customer
     */
    private void transferCustomer(Customer selectCustomer) { //Transfers the selected customer's information to the text fields and combo boxes.
        for (Customer customer : CustomerDB.getAllCustomers()) { //For each customer in the database...
            if (customer.getId() == selectedCustomerId) { //...if the customer's ID is the same as the selected customer's ID...
                modifyCustomerID.setText(String.valueOf(selectCustomer.getId())); //...set the customer's ID to the ID in the text field.
                modifyCustomerName.setText(selectCustomer.getName()); //...set the customer's name to the name in the text field.
                modifyCustomerStreet.setText(selectCustomer.getAddress()); //...set the customer's address to the address in the text field.
                modifyCustomerZip.setText(selectCustomer.getPostalCode()); //...set the customer's postal code to the postal code in the text field.
                modifyCustomerPhone.setText(selectCustomer.getPhone()); //...set the customer's phone number to the phone number in the text field.
                for (Division division : divisionsList) { //...for each division in the list...
                    if (selectCustomer.getDivisionId() == division.getId()) { //...if the customer's division ID is the same as the division's ID...
                        modifyCustomerDivision.setValue(division); //...set the customer's division to the division in the combo box.
                        for (Countries country : countriesList) { //...for each country in the list...
                            if (division.getCountryId() == country.getId()) { //...if the division's country ID is the same as the country's ID...
                                modifyCustomerCountry.setValue(country); //...set the customer's country to the country in the combo box.
                                selectedCountry = country; //...set the selected country to the country.
                            }
                        }
                    }
                }
                //LAMBDA EXPRESSION
                try { //Try to get the customer's division.
                    if (selectedCountry.getId() == 1) { //If the selected country is the US...
                        divisionsList.forEach(division -> { //...for each division in the list...
                            if (division.getCountryId() == 1) { //...if the division is in the US...
                                modifyCustomerDivision.getItems().add(division); //...add the division to the division combo box.
                            }
                        });

                    } else if (selectedCountry.getId() == 2) { //If the selected country is the Canada...
                        divisionsList.forEach(division -> { //...for each division in the list...
                            if (division.getCountryId() == 2) { //...if the division is in the Canada...
                                modifyCustomerDivision.getItems().add(division); //...add the division to the division combo box.
                            }
                        });

                    } else if (selectedCountry.getId() == 3) { //If the selected country is the Mexico...
                        divisionsList.forEach(division -> { //...for each division in the list...
                            if (division.getCountryId() == 3) { //...if the division is in the Mexico...
                                modifyCustomerDivision.getItems().add(division); //...add the division to the division combo box.
                            }
                        });

                    }
                } catch (NullPointerException e) { //If the customer's division is null...
                    System.out.println("No division found"); //...print to the console that no division was found.
                }
            }
        }
    }

    /**
     * Initialize.
     * This method is called automatically when the fxml file is loaded.
     *
     * @param url            the url
     * @param resourceBundle the resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //When the view is initialized...
        transferCustomer(ViewCustomersController.selectedCustomer); //...transfer the selected customer's information to the text fields and combo boxes.
        modifyCustomerCountry.setItems(countriesList); //...set the country combo box to the list of countries.
        modifyCustomerDivision.setItems(divisionsList); //...set the division combo box to the list of divisions.
    }
}
