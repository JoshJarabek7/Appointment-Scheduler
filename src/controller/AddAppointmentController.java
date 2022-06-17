package controller;

import helper.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;
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
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


/**
 * This class is the controller for the add appointment view.
 * It handles the user input and calls the model to add the appointment.
 * @author Joshua Jarabek
 */
public class AddAppointmentController implements Initializable { //Controller for the add appointment view
    @FXML
    private Button addAppointmentCancel; //Button for canceling the add appointment view.
    @FXML
    private Button addAppointmentSave; //Button for adding the appointment.
    @FXML
    private TextField addAppointmentID; //Non-editable text field for the appointment id.
    @FXML
    private ComboBox<Customer> addAppointmentCustomerID; //Combo box for the customer id.
    @FXML
    private ComboBox<User> addAppointmentUser; //Combo box for the user id.
    @FXML
    private TextField addAppointmentTitle; //Text field for the appointment title.
    @FXML
    private TextField addAppointmentDescription; //Text field for the appointment description.
    @FXML
    private TextField addAppointmentLocation; //Text field for the appointment location.
    @FXML
    private ComboBox<Contact> addAppointmentContact; //Combo box for the contact id.
    @FXML
    private ComboBox<String> addAppointmentType; //Combo box for the appointment type.
    @FXML
    private DatePicker addAppointmentDate; //Date picker for the appointment date.
    @FXML
    private ComboBox<LocalTime> addAppointmentStart; //Combo box for the appointment start time.
    @FXML
    private ComboBox<LocalTime> addAppointmentEnd; //Combo box for the appointment end time.
    @FXML
    private ObservableList<String> types = FXCollections.observableArrayList(); //List of appointment types.
    @FXML
    private ObservableList<Appointment> appointmentsList = AppointmentDB.getAllAppointments(); //List of all appointments.
    private LocalTime start = LocalTime.of(4, 0); //Earliest start time in combo box.
    private LocalTime end = LocalTime.of(23, 0); //Earliest end time in combo box.
    private Customer customer; //Customer object for the selected customer.
    private User user; //User object for the selected user.
    private Contact contact; //Contact object for the selected contact.
    private String type; //String for the selected appointment type.
    private String title; //String for the appointment title.
    private String description; //String for the appointment description.
    private String location; //String for the appointment location.
    private LocalTime startTime; //Local time for the appointment start time.
    private LocalTime endTime; //Local time for the appointment end time.
    private LocalDate date; //Local date for the appointment date.
    private LocalDateTime startDateTime; //Local date time for the appointment start date time.
    private LocalDateTime endDateTime; //Local date time for the appointment end date time.

    /**
     * Get Info
     * This method is used for getting the information from the user and creating the appointment.
     */
    private void getInfo() { //Method for getting the appointment information from the view.
        customer = addAppointmentCustomerID.getSelectionModel().getSelectedItem(); //Info from the customer combo box.
        user = addAppointmentUser.getSelectionModel().getSelectedItem(); //Info from the user combo box.
        title = addAppointmentTitle.getText().trim(); //Info from the title text field.
        description = addAppointmentDescription.getText().trim(); //Info from the description text field.
        location = addAppointmentLocation.getText().trim(); //Info from the location text field.
        contact = addAppointmentContact.getSelectionModel().getSelectedItem(); //Info from the contact combo box.
        type = addAppointmentType.getSelectionModel().getSelectedItem(); //Info from the type combo box.
        date = addAppointmentDate.getValue(); //Info from the date picker.
        startTime = addAppointmentStart.getValue(); //Info from the start time combo box.
        endTime = addAppointmentEnd.getValue(); //Info from the end time combo box.
    }

    /**
     * Validate time boolean.
     * This method is used for validating the time.
     * @return boolean as true if the start time is before the end time, false otherwise.
     */
    private boolean validateTime() { //Method for validating the time entered in according to the business hours.
        LocalDateTime possibleStart = LocalDateTime.of(date, startTime); //Local date time for the start time.
        LocalDateTime possibleEnd = LocalDateTime.of(date, endTime); //Local date time for the end time.
        return TimeConversion.compareWithBusinessHours(possibleStart) && TimeConversion.compareWithBusinessHours(possibleEnd); //Returns true if the appointment time is valid.
    }

    /**
     * Validate info boolean.
     * This method is used for validating the information entered by the user.
     * @return the boolean as true if the appointment information is valid, false otherwise.
     */
    private boolean validateInfo() { //Returns true if the input info is valid.
        getInfo(); //Gets the info from the view.
        if (customer == null || user == null || title.isEmpty() || description.isEmpty() || location.isEmpty() || contact == null || type == null || date == null || startTime == null || endTime == null) { //If any of the info is invalid...
            AlertHelper alertHelper = new AlertHelper("Error", "Missing Information", "Please fill in all fields.", Alert.AlertType.ERROR); //Alerts the user that the info is missing.
            alertHelper.showAlertButStay(); //Shows the alert.
            return false;
        } else if (startTime.isAfter(endTime)) { //If the start time is after the end time...
            AlertHelper alertHelper = new AlertHelper("Error", "Invalid Time", "The start time must be before the end time.", Alert.AlertType.ERROR); //Alerts the user that the start time is after the end time.
            alertHelper.showAlertButStay(); //Shows the alert.
            return false;
        } else if (endTime.isBefore(startTime) || (endTime.equals(startTime))) { //If the end time is before the start time or the same as the start time...
            AlertHelper alertHelper = new AlertHelper("Error", "Invalid Time", "The end time must be after the start time.", Alert.AlertType.ERROR); //Alerts the user that the end time is before the start time.
            return false;
        } else if (!validateTime()) { //If the appointment time is invalid...
            String sb =
                    "Start time must be between 8:00 AM and 10:00 PM EST." + "\n" +
                    "Your start time in EST is " + TimeConversion.formatTime(TimeConversion.convertLocalToEastern(LocalDateTime.of(date, startTime))) + "\n" +
                    "Your end time in EST is " + TimeConversion.formatTime(TimeConversion.convertLocalToEastern(LocalDateTime.of(date, endTime))) + "\n" +
                    "Your current time in EST is " + TimeConversion.formatTime(TimeConversion.convertLocalToEastern(LocalDateTime.now()));
            AlertHelper alertHelper = new AlertHelper("Error", "Invalid Time", sb, Alert.AlertType.ERROR); //Alerts the user that the appointment time is invalid.
            return false;
        } else if (!overlap()) { //If the appointment overlaps with another appointment...
            AlertHelper alertHelper = new AlertHelper("Error", "Overlap", "The appointment overlaps with another appointment.", Alert.AlertType.ERROR); //...alert the user that the appointment overlaps with another appointment.
            return true;
        } else { //If the info is valid...
            startDateTime = LocalDateTime.of(date, startTime); //...set the start date time to the start date time.
            endDateTime = LocalDateTime.of(date, endTime); //...set the end date time to the end date time and return true.
            return true;
        }
    }

    /**
     * Overlap boolean.
     * This method is used for checking if the appointment overlaps with another appointment.
     * @return the boolean as true if the appointment does not overlap with another appointment, false otherwise.
     */
    private boolean overlap() { //Checks if the appointment time overlaps with another appointment.
        startDateTime = LocalDateTime.of(date, startTime); //Local date time for the start time.
        endDateTime = LocalDateTime.of(date, endTime); //Local date time for the end time.
        for (Appointment appointment : appointmentsList) { //For each appointment in the list...
            if (appointment.getCustomerId() == customer.getId()) { //If the appointment is for the same customer...
                if (startDateTime.isAfter(appointment.getStartDateTime().minusMinutes(1)) && startDateTime.isBefore(appointment.getEndDateTime())) { //If the start time is between the start and end time of the appointment...
                    AlertHelper alertHelper = new AlertHelper("Error", "Overlap", "This appointment overlaps with another appointment.", Alert.AlertType.ERROR); //...alert the user that the appointment time overlaps with another appointment.
                    alertHelper.showAlertButStay(); //Shows the error alert.
                    return false;
                } else if (endDateTime.isAfter(appointment.getStartDateTime().minusMinutes(1)) && endDateTime.isBefore(appointment.getEndDateTime())) { //If the end time is between the start and end time of the appointment...
                    AlertHelper alertHelper = new AlertHelper("Error", "Overlap", "Appointment overlaps with another appointment.", Alert.AlertType.ERROR); //...alert the user that the appointment time overlaps with another appointment.
                    alertHelper.showAlertButStay(); //Shows the error alert.
                    return false;
                }
            }
        } return true; //If the appointment time does not overlap with another appointment, return true.
    }

    /**
     * On action cancel.
     * This method is called when the user clicks the cancel button.
     * @param event the event that triggered the method.
     * @throws IOException the io exception
     */
    @FXML
    public void onActionCancel(ActionEvent event) throws IOException { //Method is called when the cancel appointment button is clicked.
        AlertHelper alertHelper = new AlertHelper("Confirmation","Confirm Cancellation", "Are you sure you want to cancel?", Alert.AlertType.CONFIRMATION); //Creates a confirmation alert.
        alertHelper.showAlert(event, "MainMenuView.fxml"); //Shows the confirmation alert.
    }

    /**
     * On action save.
     * This method is called when the user clicks the save button.
     * @param event the event that triggered the method.
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    @FXML
    public void onActionSave(ActionEvent event) throws IOException, SQLException { //Method is called when the save appointment button is clicked.
        if (validateInfo()) { //If the info is valid...
            int appointmentId = 0; //Initializes the appointment id to 0, because it's autoincrement in the database.
            String title = addAppointmentTitle.getText(); //Gets the title from the view.
            String description = addAppointmentDescription.getText(); //Gets the description from the view.
            String location = addAppointmentLocation.getText(); //Gets the location from the view.
            LocalDateTime startDateTime = LocalDateTime.of(addAppointmentDate.getValue(), addAppointmentStart.getValue()); //Gets the start time from the view.
            LocalDateTime endDateTime = LocalDateTime.of(addAppointmentDate.getValue(), addAppointmentEnd.getValue()); //Gets the end time from the view.
            String type = addAppointmentType.getSelectionModel().getSelectedItem(); //Gets the type from the view.
            int contactId = addAppointmentContact.getSelectionModel().getSelectedItem().getId(); //Gets the contact id from the view.
            int customerId = addAppointmentCustomerID.getSelectionModel().getSelectedItem().getId(); //Gets the customer id from the view.
            int userId = addAppointmentUser.getSelectionModel().getSelectedItem().getId(); //Gets the user id from the view.
            LocalDateTime createDate = LocalDateTime.now(); //Gets the current date and time to be used as the creation date.
            String createdBy = LoginController.getActiveIdString(); //Gets the user id of the current user who is creating the appointment.
            LocalDateTime lastUpdated = LocalDateTime.now(); //Gets the current date and time to be used as the last updated date.
            String lastUpdatedBy = LoginController.getActiveIdString(); //Gets the user id of the current user to be used in the last updated section the appointment.
            Appointment appointment = new Appointment(appointmentId, title, description, location, type, startDateTime, endDateTime, customerId, userId, contactId, createDate, createdBy, lastUpdated, lastUpdatedBy); //Creates an appointment object.
            AppointmentDB.addAppointment(appointment); //Adds the appointment to the database.
            AlertHelper alertHelper = new AlertHelper("Information", "Success!", "Your appointment has been successfully created!", Alert.AlertType.INFORMATION); //Creates an information alert.
            alertHelper.showAlert(event, "MainMenuView.fxml"); //Shows the information alert and returns to the main menu.
        }
    }

    /**
     * Initialize.
     * This method is called when the view is initialized.
     * @param url            the url
     * @param resourceBundle the resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //Method is called when the view is initialized.
        types.addAll("All Staff", "Department Meeting", "Planning Session", "De-Briefing", "Scrum Meeting"); //Adds the types to the combo box.
        addAppointmentType.setItems(types); //Sets the types to the combo box.
        addAppointmentTitle.requestFocus(); //Sets the focus to the title text field.
        addAppointmentContact.setItems(ContactDB.getAllContacts()); //Sets the contacts to the combo box.
        addAppointmentUser.setItems(UserDB.getAllUsers()); //Sets the users to the combo box.
        addAppointmentCustomerID.setItems(CustomerDB.getAllCustomers()); //Sets the customers to the combo box.
        addAppointmentDate.setConverter(new StringConverter<>() { //Sets the date picker to the current date.
            String pattern = "MM-dd-yyyy"; //Sets the pattern.
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern); //Sets the format.
            {addAppointmentDate.setPromptText("Pick a date");} //Sets the prompt text.
            @Override
            public String toString(LocalDate localDate) { //Converts the date to a string.
                if (localDate == null) { //If the date is null...
                    return ""; //...return an empty string.
                } else { //If the date is not null...
                    return dateFormatter.format(localDate); //...return the date in the format.
                }
            }
            @Override
            public LocalDate fromString(String s) { //Converts the string to a date.
                if (s != null && !s.isEmpty()) { //If the string is not null and not empty...
                    return LocalDate.parse(s, dateFormatter); //...return the date in the format.
                } else { //If the string is null or empty...
                    return null; //...return null.
                }
            }
        });
        addAppointmentDate.setValue(LocalDate.now()); //Sets the date to the current date.
        date = addAppointmentDate.getValue(); //Gets the date from the view.
        TimeConversion.populateTimes(addAppointmentStart, start, end); //Populates the start times.
        TimeConversion.populateTimes(addAppointmentEnd, start, end); //Populates the end times.
        addAppointmentStart.getSelectionModel().selectFirst(); //Selects the first item in the combo box.
        addAppointmentEnd.getSelectionModel().selectFirst(); //Selects the first item in the combo box.
    }
}
