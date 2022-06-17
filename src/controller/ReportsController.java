package controller;

import helper.AppointmentDB;
import helper.ContactDB;
import helper.SceneSwitcher;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;
import model.Contact;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * The class ReportsController is used to handle the reports page.
 *
 * @author Joshua Jarabek
 */
public class ReportsController implements Initializable { //This class is the controller for the reports screen.
    /**
     * The Reports table.
     */
    @FXML
    private TableView<Appointment> reportsTable; //The table view for the appointments in the reports screen.
    /**
     * The Reports appointment id.
     */
    @FXML
    private TableColumn<Appointment, Integer> reportsAppointmentID; //The table column for the appointment ID in the reports table.
    /**
     * The Reports title.
     */
    @FXML
    private TableColumn<Appointment, String> reportsTitle; //The table column for the title of the appointment in the reports table.
    /**
     * The Reports type.
     */
    @FXML
    private TableColumn<Appointment, String> reportsType; //The table column for the type of the appointment in the reports table.
    /**
     * The Reports description.
     */
    @FXML
    private TableColumn<Appointment, String> reportsDescription; //The table column for the description of the appointment in the reports table.
    /**
     * The Reports start.
     */
    @FXML
    private TableColumn<Appointment, LocalDateTime> reportsStart; //The table column for the start time of the appointment in the reports table.
    /**
     * The Reports end.
     */
    @FXML
    private TableColumn<Appointment, LocalDateTime> reportsEnd; //The table column for the end time of the appointment in the reports table.
    /**
     * The Reports customer.
     */
    @FXML
    private TableColumn<Appointment, Integer> reportsCustomer; //The table column for the customer ID of the appointment in the reports table.
    /**
     * The Type combo box.
     */
    @FXML
    private ComboBox<String> typeComboBox; //The combo box for the type of the appointment in the reports table.
    /**
     * The Month combo box.
     */
    @FXML
    private ComboBox<String> monthComboBox; //The combo box for the month of the appointment in the reports table.
    /**
     * The Contact combo box.
     */
    @FXML
    private ComboBox<Contact> contactComboBox; //The combo box for the contact of the appointment in the reports table.
    /**
     * The Get total button.
     */
    @FXML
    private Button getTotalButton; //The button to get the total number of appointments in the report.
    /**
     * The Total label.
     */
    @FXML
    private Label totalLabel; //The label for the total number of appointments in the report.
    /**
     * The Back button.
     */
    @FXML
    private Button backButton; //The button to go back to the main screen.
    /**
     * The Appointments.
     */
    @FXML
    private ObservableList<Appointment> appointments = AppointmentDB.getAllAppointments(); //The list of all the appointments in the database.
    /**
     * The Total by day label.
     */
    @FXML
    private Label totalByDayLabel; //The label for the total number of appointments by day in the report.
    /**
     * The Day combo box.
     */
    @FXML
    private ComboBox<String> dayComboBox; //The combo box for the day of the appointment in the reports table.
    /**
     * The Appointment count.
     */
    public int appointmentCount = 0; //Sets the initial appointment count to 0.
    /**
     * The Day count.
     */
    public int dayCount = 0; //Sets the initial day count to 0.

    /**
     * On type box.
     * When the type combo box is changed, it sets the type of the appointment report to the selected value.
     *
     * @param event the type combo box is changed.
     */
    @FXML
    private void onTypeBox(ActionEvent event) {} //This method is called when the Type combo box is changed.

    /**
     * On month box.
     * When the month combo box is changed, it sets the month of the appointment report to the selected value.
     *
     * @param event the month combo box is changed.
     */
    @FXML
    private void onMonthBox(ActionEvent event) {} //This method is called when the Month combo box is changed.

    /**
     * On get total appointments.
     * When the get total appointments button is clicked, it gets the total number of appointments in the report.
     *
     * @param event the event
     */
    @FXML
    private void onGetTotalAppointments(ActionEvent event) { //This method is called when the get total button is clicked.
        appointmentCount = 0; //Sets the appointment count to 0.
        try { //Tries to get the total number of appointments.
            for (Appointment appointment : appointments) { //For each appointment in the list of appointments...
                if ((typeComboBox.getSelectionModel().getSelectedItem().equals(appointment.getType())) && (monthComboBox.getSelectionModel().getSelectedItem().equals(appointment.getStartDateTime().getMonth().toString()))) { //...if the type of the appointment is the same as the type in the combo box...
                    appointmentCount++; //...add one to the appointment count.
                }
            }
            totalLabel.setText("Total Appointments: " + appointmentCount); //Sets the total label to the number of appointments.
        } catch (NullPointerException e) { //If the combo box is not selected...
            totalLabel.setText("Total Appointments: " + appointmentCount); //...set the total label to the number of appointments.
        }
    }

    /**
     * On select contact.
     * When the contact combo box is changed, it sets the contact of the appointment report to the selected value.
     *
     * @param event the contact combo box is changed.
     */
    @FXML
    private void onSelectContact(ActionEvent event) { //This method is called when the contact combo box is changed.
        reportsTable.getItems().clear(); //Clears the table.
        for (Appointment appointment : appointments) { //For each appointment in the list of appointments...
            if (contactComboBox.getSelectionModel().getSelectedItem().getId() == appointment.getContactId()) { //...if the contact ID of the appointment is the same as the contact ID in the combo box...
                reportsTable.getItems().add(appointment); //...add the appointment to the table.
            }
        }
    }

    /**
     * On day combo.
     * When the day combo box is changed, it sets the day of the appointment report to the selected value.
     *
     * @param event the day combo box is changed.
     */
    @FXML
    private void onDayCombo(ActionEvent event) { //This method is called when the day combo box is changed.
        dayCount = 0; //Sets the day count to 0.
        for (Appointment appointment : appointments) { //For each appointment in the list of appointments...
            if (appointment.getStartDateTime().getDayOfWeek().toString().equals(dayComboBox.getSelectionModel().getSelectedItem())) { //...if the day of the appointment is the same as the day in the combo box...
                dayCount++; //...add one to the day count.
            }
        } totalByDayLabel.setText("Total Appointments: " + dayCount); //Sets the total by day label to the number of appointments.
    }

    /**
     * On back button.
     * When the back button is clicked, it goes back to the main screen.
     *
     * @param event the back button is clicked.
     * @throws IOException the io exception
     */
    @FXML
    public void onBackButton(ActionEvent event) throws IOException { //This method is called when the back button is clicked.
        SceneSwitcher.switchScreen(event, "MainMenuView.fxml"); //Switches to the main menu screen.
        }

    /**
     * Initialize.
     * This method is called when the view is loaded.
     *
     * @param url            the url
     * @param resourceBundle the resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //This method is called when the screen is initialized.
        typeComboBox.getItems().add("Planning Session"); //Adds planning session to the type combo box.
        typeComboBox.getItems().add("De-Briefing"); //Adds de-briefing to the type combo box.
        typeComboBox.getItems().add("Training"); //Adds training to the type combo box.
        typeComboBox.getItems().add("All Staff"); //Adds all staff to the type combo box.
        monthComboBox.getItems().add("JANUARY"); //Adds January to the month combo box.
        monthComboBox.getItems().add("FEBRUARY"); //Adds February to the month combo box.
        monthComboBox.getItems().add("MARCH"); //Adds March to the month combo box.
        monthComboBox.getItems().add("APRIL"); //Adds April to the month combo box.
        monthComboBox.getItems().add("MAY"); //Adds May to the month combo box.
        monthComboBox.getItems().add("JUNE"); //Adds June to the month combo box.
        monthComboBox.getItems().add("JULY"); //Adds July to the month combo box.
        monthComboBox.getItems().add("AUGUST"); //Adds August to the month combo box.
        monthComboBox.getItems().add("SEPTEMBER"); //Adds September to the month combo box.
        monthComboBox.getItems().add("OCTOBER"); //Adds October to the month combo box.
        monthComboBox.getItems().add("NOVEMBER"); //Adds November to the month combo box.
        monthComboBox.getItems().add("DECEMBER"); //Adds December to the month combo box.
        dayComboBox.getItems().add("SUNDAY"); //Adds Sunday to the day combo box.
        dayComboBox.getItems().add("MONDAY"); //Adds Monday to the day combo box.
        dayComboBox.getItems().add("TUESDAY"); //Adds Tuesday to the day combo box.
        dayComboBox.getItems().add("WEDNESDAY"); //Adds Wednesday to the day combo box.
        dayComboBox.getItems().add("THURSDAY"); //Adds Thursday to the day combo box.
        dayComboBox.getItems().add("FRIDAY"); //Adds Friday to the day combo box.
        dayComboBox.getItems().add("SATURDAY"); //Adds Saturday to the day combo box.
        contactComboBox.setItems(ContactDB.getAllContacts()); //Sets the contact combo box to the list of all contacts.
        reportsAppointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentId")); //Sets the appointment ID column to the appointment ID in the database.
        reportsTitle.setCellValueFactory(new PropertyValueFactory<>("title")); //Sets the title column to the title in the database.
        reportsType.setCellValueFactory(new PropertyValueFactory<>("type")); //Sets the type column to the type in the database.
        reportsDescription.setCellValueFactory(new PropertyValueFactory<>("description")); //Sets the description column to the description in the database.
        reportsStart.setCellValueFactory(new PropertyValueFactory<>("startDateTime")); //Sets the start column to the start date and time in the database.
        reportsEnd.setCellValueFactory(new PropertyValueFactory<>("endDateTime")); //Sets the end column to the end date and time in the database.
        reportsCustomer.setCellValueFactory(new PropertyValueFactory<>("customerId")); //Sets the customer column to the customer in the database.
    }
}
