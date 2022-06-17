package controller;

import helper.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;


/**
 * The class UpdateAppointmentController is the controller for the update appointment view.
 */
public class UpdateAppointmentController implements Initializable { //This class is the controller for the Update Appointment Screen.
    public static Appointment selectedAppointment = MainMenuController.selectedAppointment; //Creates a static appointment object for the selected appointment.
    @FXML
    private Button modifyAppointmentCancel; //The button to cancel the updating of the appointment.
    @FXML
    private Button modifyAppointmentSave; //The button to save the updated appointment.
    @FXML
    private TextField modifyAppointmentID; //The text field for the appointment ID of the appointment.
    @FXML
    private ComboBox<Customer> modifyAppointmentCustomerID; //The combo box for the customer ID of the appointment.
    @FXML
    private ComboBox<User> modifyAppointmentUser; //The combo box for the user of the appointment.
    @FXML
    private TextField modifyAppointmentTitle; //The text field for the title of the appointment.
    @FXML
    private TextField modifyAppointmentDescription; //The text field for the description of the appointment.
    @FXML
    private TextField modifyAppointmentLocation; //The text field for the location of the appointment.
    @FXML
    private ComboBox<Contact> modifyAppointmentContact; //The combo box for the contact of the appointment.
    @FXML
    private ComboBox<String> modifyAppointmentType; //The combo box for the type of the appointment.
    @FXML
    private DatePicker modifyAppointmentDate; //The date picker for the date of the appointment.
    @FXML
    private ComboBox<LocalTime> modifyAppointmentStart; //The combo box for the start time of the appointment.
    @FXML
    private ComboBox<LocalTime> modifyAppointmentEnd; //The combo box for the end time of the appointment.
    @FXML
    private ObservableList<String> types = FXCollections.observableArrayList(); //Creates an observable list of all the types of appointments.
    @FXML
    private ObservableList<Customer> customers = FXCollections.observableArrayList(); //Creates an observable list for all the customers.
    @FXML
    private ObservableList<User> users = FXCollections.observableArrayList(); //Creates an observable list for all the users.
    @FXML
    private ObservableList<Contact> contacts = FXCollections.observableArrayList(); //Creates an observable list for all the contacts.
    @FXML
    private ObservableList<Appointment> appointmentList = AppointmentDB.getAllAppointments(); //Creates an observable list of all appointments.
    private LocalTime start = LocalTime.of(4, 0); //Create a local time object for the business start time.
    private LocalTime end = LocalTime.of(23, 0); //Creates a local time object for the business end time.
    private Customer customer; //Creates a customer object.
    private User user; //Creates a user object.
    private String title; //Creates a string for the title of the appointment.
    private String description; //Creates a string for the description of the appointment.
    private String location; //Creates a string for the location of the appointment.
    private Contact contact; //Creates a contact object.
    private String type; //Creates a string for the type of the appointment.
    private LocalDate date; //Creates a local date object for the date of the appointment.
    private LocalTime startTime; //Creates a local time object for the start time of the appointment.
    private LocalTime endTime; //Creates a local time object for the end time of the appointment.
    private LocalDateTime startDateTime; //Creates a local date time object for the start date time of the appointment.
    private LocalDateTime endDateTime; //Creates a local date time object for the end date time of the appointment.

    /**
     * Gets info.
     * When this method is called, it gets the info from the fields and sets the variables to the info.
     */
    private void getInfo() { //This method gets the information from the user input fields.
        customer = modifyAppointmentCustomerID.getSelectionModel().getSelectedItem(); //Gets the information from the combo box for the customer ID and sets it to the customer object.
        user = modifyAppointmentUser.getSelectionModel().getSelectedItem(); //Gets the information from the combo box for the user and sets it to the user object.
        title = modifyAppointmentTitle.getText().trim(); //Gets the information from the text field for the title and sets it to the title string.
        description = modifyAppointmentDescription.getText().trim(); //Gets the information from the text field for the description and sets it to the description string.
        location = modifyAppointmentLocation.getText().trim(); //Gets the information from the text field for the location and sets it to the location string.
        contact = modifyAppointmentContact.getSelectionModel().getSelectedItem(); //Gets the information from the combo box for the contact and sets it to the contact object.
        type = modifyAppointmentType.getSelectionModel().getSelectedItem(); //Gets the information from the combo box for the type and sets it to the type string.
        date = modifyAppointmentDate.getValue(); //Gets the information from the date picker for the date and sets it to the date local date object.
        startTime = modifyAppointmentStart.getValue(); //Gets the information from the combo box for the start time and sets it to the start time local time object.
        endTime = modifyAppointmentEnd.getValue(); //Gets the information from the combo box for the end time and sets it to the end time local time object.
    }

    /**
     * Is valid boolean.
     * When this method is called, it checks if the appointment time is within business hours.
     *
     * @return the boolean value, true if the appointment time is within business hours, false if it is not.
     */
    private boolean isValid() { //This method checks if the user input for the appointment times are valid.
        LocalDateTime potentialStartDateTime = LocalDateTime.of(date, startTime); //Creates a local date time object for the potential start date time of the appointment using the date and start time.
        LocalDateTime potentialEndDateTime = LocalDateTime.of(date, endTime); //Creates a local date time object for the potential end date time of the appointment using the date and end time.
        return TimeConversion.compareWithBusinessHours(potentialStartDateTime) && TimeConversion.compareWithBusinessHours(potentialEndDateTime); //Returns true if the potential start date time and potential end date time are within business hours.
    }

    /**
     * Is overlap boolean.
     * When this method is called, it checks if the appointment time is within the time of another appointment.
     *
     * @return the boolean value, true if the appointment time is not within the time of another appointment, false if it is.
     */
    private boolean isOverlap() { //This method checks if the user input for the appointment times overlap with other appointments that the customer has.
        startDateTime = LocalDateTime.of(date, startTime); //Sets the start date time to the local date time object for the start date time of the appointment.
        endDateTime = LocalDateTime.of(date, endTime); //Sets the end date time to the local date time object for the end date time of the appointment.
        for (Appointment appointment : appointmentList) { //Loops through all the appointments.
            if (appointment.getCustomerId() == customer.getId()) { //Checks if there's any overlap for the same customer.
                if (startDateTime.isAfter(appointment.getStartDateTime().minusMinutes(1)) && startDateTime.isBefore(appointment.getEndDateTime())) { //Checks if the start date time is after the start date time minus 1 minute and before the end date time.
                    AlertHelper alertHelper = new AlertHelper("Error", "Invalid start time", "The start time of the appointment overlaps with another appointment.", Alert.AlertType.ERROR); //Creates an alert helper object for the error message.
                    alertHelper.showAlertButStay(); //Shows the alert.
                    return false;
                } else if (endDateTime.isAfter(appointment.getStartDateTime().minusMinutes(1)) && endDateTime.isBefore(appointment.getEndDateTime())) { //Checks if the end date time is after the start date time minus 1 minute and before the end date time.
                    AlertHelper alertHelper = new AlertHelper("Error", "Invalid end time", "The end time of the appointment overlaps with another appointment.", Alert.AlertType.ERROR); //Creates an alert helper object for the error message.
                    alertHelper.showAlertButStay(); //Shows the alert.
                    return false;
                }
            }
        }
        return true; //Returns true if there's no overlap.
    }

    /**
     * Is valid input boolean.
     * When this method is called, it checks if the user input is valid.
     *
     * @return the boolean true if the user input is valid, false if it is not.
     */
    private boolean isValidInput() { //This method checks if all user inputs are valid and returns true if they are.
        getInfo(); //Gets the information from the user input fields.
        System.out.println("Are these times valid: " + isValid()); //Prints the information to the console.
        if (title.isEmpty() || description.isEmpty() || location.isEmpty() || type.isEmpty() || date == null || startTime == null || endTime == null || customer == null || user == null || contact == null) { //Checks if the user input for the appointment is empty.
            AlertHelper alertHelper = new AlertHelper("Error", "Invalid input", "Please fill in all the fields.", Alert.AlertType.ERROR); //Creates an alert helper object for the error message.
            alertHelper.showAlertButStay(); //Shows the alert.
            return false;
        } else if (startTime.isAfter(endTime) || startTime.equals(endTime)) { //Checks if the start time is after the end time or if the start time is the same as the end time.
            AlertHelper alertHelper = new AlertHelper("Error", "Invalid input", "The start time must be before the end time.", Alert.AlertType.ERROR); //Creates an alert helper object for the error message.
            alertHelper.showAlertButStay(); //Shows the alert.
            return false;
        } else if (endTime.isBefore(startTime) || endTime.equals(startTime)) { //Checks if the end time is before the start time or if the end time is the same as the start time.
            AlertHelper alertHelper = new AlertHelper("Error", "Invalid input", "The end time must be after the start time.", Alert.AlertType.ERROR); //Creates an alert helper object for the error message.
            alertHelper.showAlertButStay(); //Shows the alert.
            return false;
        } else if (!isValid()) { //Alerts the user that the appointment times are not within business hours and returns false.
            String sb = "Appointment must be between 8:00 AM and 10:00 PM EST.\n" +
                    "Your start time in EST is " + TimeConversion.formatTime(TimeConversion.convertLocalToEastern(LocalDateTime.of(date, startTime))) + "\n" +
                    "Your end time in EST is " + TimeConversion.formatTime(TimeConversion.convertLocalToEastern(LocalDateTime.of(date, endTime))) + "\n" +
                    "Your current time is " + TimeConversion.formatTime(LocalTime.now()) + "\n" +
                    "EST is " + TimeConversion.formatTime(TimeConversion.convertLocalToEastern(LocalDateTime.now()));
            AlertHelper alertHelper = new AlertHelper("Error", "Invalid hours", sb, Alert.AlertType.ERROR); //Creates an alert helper object for the error message.
            return false;
        } else if (!isOverlap()) { //Alerts the user that the appointment times overlap and returns false.
            AlertHelper alertHelper = new AlertHelper("Error", "Invalid input", "The appointment times overlap with another appointment.", Alert.AlertType.ERROR); //Creates an alert helper object for the error message.
            alertHelper.showAlertButStay(); //Shows the alert.
            return false;
        } else { //Returns true if the user input for the appointment is valid.
            startDateTime = LocalDateTime.of(date, startTime);
            endDateTime = LocalDateTime.of(date, endTime);
            return true;
        }
    }

    /**
     * On action cancel.
     * When this method is called, it cancels the appointment and returns to the previous screen.
     *
     * @param event the cancel button is pressed.
     * @throws IOException the io exception
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException { //This method is called when the user clicks the cancel button.
        AlertHelper alertHelper = new AlertHelper("Confirmation", "Cancel Appointment", "Are you sure you want to cancel?", Alert.AlertType.CONFIRMATION); //Alerts the user that they are about to cancel and asks if they are sure.
        alertHelper.showAlert(event, "MainMenuView.fxml"); //Returns to the main menu.
    }

    /**
     * On action save.
     * When this method is called, it saves the appointment and returns to the previous screen.
     *
     * @param event the save button is pressed.
     * @throws SQLException the sql exception
     * @throws IOException  the io exception
     */
    @FXML
    void onActionSave(ActionEvent event) throws SQLException, IOException { //This method is called when the user clicks the save button.
        if (isValidInput()) { //Checks if the user inputs for the appointment are all valid.
            for (Appointment appointment : appointmentList) { //Loops through all the appointments.
                if (appointment.getAppointmentId() == selectedAppointment.getAppointmentId()) { //Checks if the appointment id is the same as the selected appointment id.
                    appointment.setCustomerId(Integer.parseInt(modifyAppointmentCustomerID.getSelectionModel().getSelectedItem().toString())); //Sets the customer id to the input customer id.
                    appointment.setUserId(Integer.parseInt(modifyAppointmentUser.getSelectionModel().getSelectedItem().toString())); //Sets the user id to the input user id.
                    appointment.setTitle(modifyAppointmentTitle.getText()); //Sets the title to the input title.
                    appointment.setDescription(modifyAppointmentDescription.getText()); //Sets the description to the input description.
                    appointment.setLocation(modifyAppointmentLocation.getText()); //Sets the location to the input location.
                    appointment.setContactId(modifyAppointmentContact.getSelectionModel().getSelectedItem().getId()); //Sets the contact id to the input contact id.
                    appointment.setType(modifyAppointmentType.getSelectionModel().getSelectedItem()); //Sets the type to the input type.
                    appointment.setStartDateTime(LocalDateTime.of(modifyAppointmentDate.getValue(), modifyAppointmentStart.getValue())); //Sets the start date time to the input start date time.
                    appointment.setEndDateTime(LocalDateTime.of(modifyAppointmentDate.getValue(), modifyAppointmentEnd.getValue())); //Sets the end date time to the input end date time.
                    appointment.setLastUpdated(LocalDateTime.now()); //Sets the last updated to the current date and time.
                    appointment.setLastUpdatedBy(LoginController.getActiveIdString()); //Sets the last updated by to the current user id.
                    AppointmentDB.updateAppointment(appointment); //Updates the appointment in the database.
                }
            }
            AlertHelper alertHelper = new AlertHelper("Information", "Success", "Appointment updated successfully", Alert.AlertType.INFORMATION); //Alerts the user that the appointment was updated successfully.
            alertHelper.showAlert(event, "MainMenuView.fxml"); //Returns to the main menu.
        }
    }

    /**
     * Initialize.
     * When this method is called, it initializes the update appointment view.
     *
     * @param url the url
     * @param rb  the rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { //This method is called when the view is loaded.
        modifyAppointmentID.setText(String.valueOf(selectedAppointment.getAppointmentId())); //Sets the appointment id to the selected appointment id.
        types.addAll("All Staff", "Department Meeting", "Planning Session", "De-Briefing", "Scrum Meeting"); //Adds all the types to the type combo box.
        modifyAppointmentType.setItems(types); //Sets the type combo box to the types.
        int selectedAppointmentType = types.indexOf(selectedAppointment.getType()); //Gets the index of the selected appointment type.
        modifyAppointmentType.getSelectionModel().select(selectedAppointmentType); //Selects the selected appointment type.
        modifyAppointmentTitle.setText(selectedAppointment.getTitle()); //Sets the title to the selected appointment title.
        modifyAppointmentDescription.setText(selectedAppointment.getDescription()); //Sets the description to the selected appointment description.
        modifyAppointmentLocation.setText(selectedAppointment.getLocation()); //Sets the location to the selected appointment location.
        modifyAppointmentDate.setValue(selectedAppointment.getStartDateTime().toLocalDate()); //Sets the date to the selected appointment start date time.
        modifyAppointmentStart.setValue(selectedAppointment.getStartDateTime().toLocalTime()); //Sets the start time to the selected appointment start date time.
        modifyAppointmentStart.getSelectionModel().select(selectedAppointment.getStartDateTime().toLocalTime()); //Selects the selected appointment start time.
        modifyAppointmentEnd.setValue(selectedAppointment.getEndDateTime().toLocalTime()); //Sets the end time to the selected appointment end date time.
        modifyAppointmentEnd.getSelectionModel().select(selectedAppointment.getEndDateTime().toLocalTime()); //Selects the selected appointment end time.
        modifyAppointmentCustomerID.setItems(CustomerDB.getAllCustomers()); //Sets the customer id combo box to all the customers.
        modifyAppointmentCustomerID.getSelectionModel().select(selectedAppointment.getCustomerId()); //Selects the selected appointment customer id.
        modifyAppointmentContact.setItems(ContactDB.getAllContacts()); //Sets the contact combo box to all the contacts.
        modifyAppointmentContact.getSelectionModel().select(selectedAppointment.getContactId()); //Selects the selected appointment contact id.
        modifyAppointmentUser.setItems(UserDB.getAllUsers()); //Sets the user combo box to all the users.
        for (User user : UserDB.getAllUsers()) { //Loops through all the users.
            if (user.getId() == selectedAppointment.getUserId()) { //Checks if the user id is the same as the selected appointment user id.
                modifyAppointmentUser.getSelectionModel().select(user); //Selects the selected appointment user.
            }
        }
        date = modifyAppointmentDate.getValue(); //Sets the date to the selected appointment date.
        TimeConversion.populateTimes(modifyAppointmentStart, start, end); //Populates the start time combo box with all the times.
        TimeConversion.populateTimes(modifyAppointmentEnd, start, end); //Populates the end time combo box with all the times.
    }
}