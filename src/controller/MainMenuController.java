package controller;

import helper.AlertHelper;
import helper.AppointmentDB;
import helper.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**
 * The class MainMenuController is the controller for the main menu.
 * @author Joshua Jarabek
 */
public class MainMenuController implements Initializable { //This class is the controller for the main menu screen.
    @FXML
    private Button exitButton; //The button to exit the application.
    @FXML
    private RadioButton viewAllAppointments; //The radio button to view all appointments.
    @FXML
    private RadioButton appointmentsThisMonth; //The radio button to view appointments this month.
    @FXML
    private TableView<Appointment> appointmentsTable; //The table view for the appointments.
    @FXML
    private RadioButton appointmentsThisWeek; //The radio button to view appointments this week.
    @FXML
    private Button viewCustomers; //The button to view all customers.
    @FXML
    private Button newAppointment; //The button to add a new appointment.
    @FXML
    private Button modifyAppointment; //The button to modify an appointment.
    @FXML
    private Button deleteAppointment; //The button to delete an appointment.
    @FXML
    private Button openReports; //The button to open the reports screen.
    @FXML
    private Label timeZoneLabel; //The label for the time zone.
    @FXML
    private TableColumn<Appointment, Integer> mainTableID; //The table column for the appointment ID.
    @FXML
    private TableColumn<Appointment, String> mainTableTitle; //The table column for the appointment title.
    @FXML
    private TableColumn<Appointment, String> mainTableDescription; //The table column for the appointment description.
    @FXML
    private TableColumn<Appointment, String> mainTableLocation; //The table column for the appointment location.
    @FXML
    private TableColumn<Appointment, String> mainTableContact; //The table column for the appointment contact.
    @FXML
    private TableColumn<Appointment, String> mainTableType; //The table column for the appointment type.
    @FXML
    private TableColumn<Appointment, LocalTime> mainTableStart; //The table column for the appointment start time.
    @FXML
    private TableColumn<Appointment, LocalTime> mainTableEnd; //The table column for the appointment end time.
    @FXML
    private TableColumn<Appointment, String> mainTableCustomerID; //The table column for the appointment customer ID.
    @FXML
    private TableColumn<Appointment, String> mainTableUserID; //The table column for the appointment user ID.
    @FXML
    private TableColumn<Appointment, LocalDateTime> mainTableCreateDate; //The table column for the appointment create date.
    @FXML
    private TableColumn<Appointment, String> mainTableCreatedBy; //The table column for the appointment created by.
    @FXML
    private TableColumn<Appointment, LocalDateTime> mainTableLastUpdate; //The table column for the appointment last update.
    @FXML
    private TableColumn<Appointment, String> mainTableLastUpdatedBy; //The table column for the appointment last updated by.
    @FXML
    private ToggleGroup mainTableToggle; //The toggle group for the table view.
    @FXML
    private ObservableList<Appointment> allAppointments = FXCollections.observableArrayList(); //The observable list for all the appointments.
    @FXML
    private ObservableList<Appointment> thisMonthsAppointments = FXCollections.observableArrayList(); //The observable list for this month's appointments.
    @FXML
    private ObservableList<Appointment> thisWeeksAppointments = FXCollections.observableArrayList(); //The observable list for this week's appointments.
    public static Appointment selectedAppointment; //The selected appointment.

    /**
     * View all appointments.
     * When this method is called in the view all action method, the table view is set to show all appointments.
     */
    private void viewAllAppointments() { //This method is used to view all appointments when the view all appointments radio button is selected.
        appointmentsTable.getItems().addAll(AppointmentDB.getAllAppointments()); //Gets the appointments from the database.
        mainTableID.setCellValueFactory(new PropertyValueFactory<>("appointmentId")); //Sets the appointment ID column.
        mainTableTitle.setCellValueFactory(new PropertyValueFactory<>("title")); //Sets the appointment title column.
        mainTableDescription.setCellValueFactory(new PropertyValueFactory<>("description")); //Sets the appointment description column.
        mainTableLocation.setCellValueFactory(new PropertyValueFactory<>("location")); //Sets the appointment location column.
        mainTableType.setCellValueFactory(new PropertyValueFactory<>("type")); //Sets the appointment type column.
        mainTableStart.setCellValueFactory(new PropertyValueFactory<>("startDateTime")); //Sets the appointment start time column.
        mainTableEnd.setCellValueFactory(new PropertyValueFactory<>("endDateTime")); //Sets the appointment end time column.
        mainTableCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerId")); //Sets the appointment customer ID column.
        mainTableUserID.setCellValueFactory(new PropertyValueFactory<>("userId")); //Sets the appointment user ID column.
        mainTableContact.setCellValueFactory(new PropertyValueFactory<>("contactId")); //Sets the appointment contact ID column.
        mainTableCreateDate.setCellValueFactory(new PropertyValueFactory<>("createDate")); //Sets the appointment create date column.
        mainTableCreatedBy.setCellValueFactory(new PropertyValueFactory<>("createdBy")); //Sets the appointment created by column.
        mainTableLastUpdate.setCellValueFactory(new PropertyValueFactory<>("lastUpdated")); //Sets the appointment last update column.
        mainTableLastUpdatedBy.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy")); //Sets the appointment last updated by column.
        appointmentsTable.getSelectionModel().selectFirst(); //Selects the first appointment in the table.
    }

    /**
     * On action monthly view.
     * When the monthly view radio button is selected, the table view is populated with this month's appointments.
     * @param event the event
     */
    @FXML
    private void onActionMonthlyView(ActionEvent event) { //This method is used to view this month's appointments when the monthly view radio button is selected.
        if (!appointmentsTable.getItems().isEmpty()) { //If the table is not empty...
            appointmentsTable.getItems().clear(); //...clear the table.
        }
        allAppointments.setAll(AppointmentDB.getAllAppointments()); //Gets all the appointments from the database and sets them to the observable list.
        for (Appointment appointment : allAppointments) { //For each appointment in the observable list...
            if (appointment.getStartDateTime().getMonth() == LocalDate.now().getMonth() && appointment.getStartDateTime().getYear() == LocalDate.now().getYear()) { //...if the appointment is this month...
                thisMonthsAppointments.add(appointment); //...add it to the observable list.
            }
        }
        if (thisMonthsAppointments.isEmpty()) { //If there are no appointments this month...
            AlertHelper alertHelper = new AlertHelper("Information", "Appointments this month", "There are no appointments this month.", Alert.AlertType.INFORMATION); //...create a new alert helper.
            alertHelper.showAlertButStay(); //...show the alert.
        } else { //If there are appointments this month...
            appointmentsTable.setItems(thisMonthsAppointments); //Set the table view to the list of appointments for this month.
            appointmentsTable.getSelectionModel().selectFirst(); //Select the first appointment in the table.
        }
    }

    /**
     * On action weekly view.
     * When the weekly view radio button is selected, the table view is populated with this week's appointments.
     * @param event the event
     */
    @FXML
    private void onActionWeeklyView(ActionEvent event) { //This method is used to view this week's appointments when the weekly view radio button is selected.
        if (!appointmentsTable.getItems().isEmpty()) { //If the table is not empty...
            appointmentsTable.getItems().clear(); //...clear the table.
        }
        allAppointments.setAll(AppointmentDB.getAllAppointments()); //Gets all the appointments from the database and sets them to the observable list.
        for (Appointment appointment : allAppointments) { //For each appointment in the observable list...
            if (appointment.getStartDateTime().getDayOfMonth() >= LocalDate.now().getDayOfMonth() && appointment.getStartDateTime().getDayOfMonth() <= LocalDate.now().getDayOfMonth() + 6) { //...if the appointment is this week...
                thisWeeksAppointments.add(appointment); //...add it to the observable list.
            }
        }
        if (thisWeeksAppointments.isEmpty()) { //If there are no appointments this week...
            AlertHelper alertHelper = new AlertHelper("Information", "Appointments this week", "There are no appointments this week.", Alert.AlertType.INFORMATION); //...create a new alert helper.
            alertHelper.showAlertButStay();
        } else { //If there are appointments this week...
            appointmentsTable.setItems(thisWeeksAppointments); //Set the table view to the list of appointments for this week.
            appointmentsTable.getSelectionModel().selectFirst(); //Select the first appointment in the table.
            }
        }

    /**
     * On action modify appointment.
     * When the modify appointment button is clicked, the selected appointment is set to the selected appointment variable and the modify appointment screen is loaded.
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    private void onActionModifyAppointment(ActionEvent event) throws IOException { //This method is used to modify an appointment when the user clicks the modify appointment button.
        int selectedAppointmentId = appointmentsTable.getSelectionModel().getSelectedItem().getAppointmentId(); //Gets the selected appointment's ID and sets it to the variable.
        String selectedTitle = appointmentsTable.getSelectionModel().getSelectedItem().getTitle(); //Gets the selected appointment's title and sets it to the variable.
        String selectedDescription = appointmentsTable.getSelectionModel().getSelectedItem().getDescription(); //Gets the selected appointment's description and sets it to the variable.
        String selectedLocation = appointmentsTable.getSelectionModel().getSelectedItem().getLocation(); //Gets the selected appointment's location and sets it to the variable.
        String selectedType = appointmentsTable.getSelectionModel().getSelectedItem().getType(); //Gets the selected appointment's type and sets it to the variable.
        LocalDateTime selectedStart = appointmentsTable.getSelectionModel().getSelectedItem().getStartDateTime(); //Gets the selected appointment's start date and time and sets it to the variable.
        LocalDateTime selectedEnd = appointmentsTable.getSelectionModel().getSelectedItem().getEndDateTime(); //Gets the selected appointment's end date and time and sets it to the variable.
        int selectedCustomerId = appointmentsTable.getSelectionModel().getSelectedItem().getCustomerId(); //Gets the selected appointment's customer ID and sets it to the variable.
        int selectedUserId = appointmentsTable.getSelectionModel().getSelectedItem().getUserId(); //Gets the selected appointment's user ID and sets it to the variable.
        int selectedContactId = appointmentsTable.getSelectionModel().getSelectedItem().getContactId(); //Gets the selected appointment's contact ID and sets it to the variable.
        LocalDateTime selectedCreateDate = appointmentsTable.getSelectionModel().getSelectedItem().getCreateDate(); //Gets the selected appointment's create date and sets it to the variable.
        String selectedCreatedBy = appointmentsTable.getSelectionModel().getSelectedItem().getCreatedBy(); //Gets the selected appointment's created by and sets it to the variable.
        LocalDateTime selectedLastUpdate = appointmentsTable.getSelectionModel().getSelectedItem().getLastUpdated(); //Gets the selected appointment's last update date and sets it to the variable.
        String selectedLastUpdatedBy = appointmentsTable.getSelectionModel().getSelectedItem().getLastUpdatedBy(); //Gets the selected appointment's last updated by and sets it to the variable.
        selectedAppointment = new Appointment(selectedAppointmentId, selectedTitle, selectedDescription, selectedLocation, selectedType, selectedStart, selectedEnd, selectedCustomerId, selectedUserId, selectedContactId, selectedCreateDate, selectedCreatedBy, selectedLastUpdate, selectedLastUpdatedBy); //Creates a new appointment object with the selected appointment's data.
        SceneSwitcher.switchScreen(event, "UpdateAppointmentView.fxml"); //Switches to the update appointment view.
    }

    /**
     * On action view customers.
     * When the view customers button is clicked, the view customers screen is loaded.
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    private void onActionViewCustomers(ActionEvent event) throws IOException { //This method is used to view all customers when the user clicks the view customers button.
        SceneSwitcher.switchScreen(event, "ViewCustomersView.fxml"); //Switches to the view customers view.
    }

    /**
     * On action delete appointment.
     * When the delete appointment button is clicked, the selected appointment is deleted from the database.
     * @param event the event
     * @throws SQLException the sql exception
     */
    @FXML
    private void onActionDeleteAppointment(ActionEvent event) throws SQLException { //This method is used to delete an appointment when the user clicks the delete appointment button.
        try { //Try to...
            Appointment appointmentToDelete = appointmentsTable.getSelectionModel().getSelectedItem(); //Get the selected appointment and set it to the variable.
            String appointmentTitle = appointmentToDelete.getTitle(); //Get the selected appointment's title and set it to the variable.
            String appointmentType = appointmentToDelete.getType(); //Get the selected appointment's type and set it to the variable.
            AlertHelper alertHelper = new AlertHelper("Confirmation", "Delete appointment", "Are you sure you want to delete the appointment '" + appointmentTitle + "' (" + appointmentType + ")?", Alert.AlertType.CONFIRMATION); //Create a new alert helper.
            alertHelper.showAlertButStay(); //Show the alert.
            Optional<ButtonType> result = alertHelper.getResult(); //Get the result of the alert.
            if (result.isPresent() && result.get() == ButtonType.OK) { //If the user clicked OK...
                AppointmentDB.deleteAppointment(appointmentToDelete.getAppointmentId()); //Delete the appointment from the database.
                appointmentsTable.getItems().remove(appointmentToDelete); //Remove the appointment from the table.
            }
            AlertHelper alertHelper2 = new AlertHelper("Information", "Appointment deleted", "The appointment '" + appointmentTitle + "' (" + appointmentType + ") has been deleted.", Alert.AlertType.INFORMATION); //Create a new alert helper.
            alertHelper2.showAlertButStay(); //Show the alert.
            appointmentsTable.getItems().clear(); //Clears the table view.
            viewAllAppointments(); //Refreshes the appointments table.
        } catch (NullPointerException e) { //If the user didn't select an appointment...
            AlertHelper alertHelper = new AlertHelper("Information", "No appointment selected", "Please select an appointment to delete.", Alert.AlertType.INFORMATION); //Create a new alert helper.
            alertHelper.showAlertButStay(); //Show the alert.
        }
    }

    /**
     * On action quit.
     * When the quit button is clicked, the application is closed and the user is logged out.
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    private void onActionQuit(ActionEvent event) throws IOException { //This method is used to quit the application when the user clicks the quit button.
        AlertHelper alertHelper = new AlertHelper("Confirmation", "Quit", "Are you sure you want to quit?", Alert.AlertType.CONFIRMATION); //Create a new alert helper.
        alertHelper.showAlertButStay(); //Show the alert.
        Optional<ButtonType> result = alertHelper.getResult(); //Get the result of the alert.
        if (result.isPresent() && result.get() == ButtonType.OK) { //If the user clicked OK...
            SceneSwitcher.switchScreen(event, "LoginView.fxml"); //Switches to the login view.
        }
    }

    /**
     * On action reports.
     * When the reports button is clicked, the reports screen is loaded.
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    private void onActionReports(ActionEvent event) throws IOException { //This method is used to view reports when the user clicks the reports button.
        SceneSwitcher.switchScreen(event, "ViewReportsView.fxml"); //Switches to the view reports view.
    }

    /**
     * On action schedule appointment.
     * When the schedule appointment button is clicked, the add appointment screen is loaded.
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    private void onActionScheduleAppointment(ActionEvent event) throws IOException { //This method is used to schedule an appointment when the user clicks the schedule appointment button.
        SceneSwitcher.switchScreen(event, "AddAppointmentView.fxml"); //Switches to the add appointment view.
    }

    /**
     * On action view all.
     * When the view all button is clicked, the table view is cleared and the appointments are refreshed.
     * @param event the event
     */
    @FXML
    private void onActionViewAll(ActionEvent event) { //This method is used to view all appointments when the user clicks the view all button.
        if (!appointmentsTable.getItems().isEmpty()) { //If the appointments table is not empty...
            appointmentsTable.getItems().clear(); //Clear the table.
        } viewAllAppointments(); //Calls the method to view all appointments.
    }

    /**
     * LAMBDA METHOD: gets zone id
     * Gets the zone id from the database.
     * <p> I'm creating a method called getZoneId() that will be used to get the timezone ID, which can be used to set the timezone.
     * I'm creating a Runnable object called setTimezoneId.
     * I'm setting a new thread to get the timezone ID.
     * I'm saving the timezone ID to a String object called a.
     * I'm setting the String object to a value that is returned from the ZoneId.of() method.
     * This method requires a parameter to be passed in, which is the timezone ID.
     * I'm passing in the timezone ID from the TimeZone class, which is retrieved from the getDefault() method.
     * Then, I'm setting the text of the timezone label to the string that contains the timezone ID.
     * Finally, I'm creating and starting a new thread to get the timezone ID. </p>
     */
    public void getZoneId() { //This method is used to get the current time zone.
        Runnable setTimezoneId = () -> {
            String a = String.valueOf(ZoneId.of(TimeZone.getDefault().getID())); //Gets the default timezone ID.
            timeZoneLabel.setText(a); //Sets the timezone label to the timezone ID.
        };
        new Thread(setTimezoneId).start(); //Starts a new thread to get the timezone ID.
    }


    /**
     * Initialize.
     * Initializes the main view.
     * @param url            the url
     * @param resourceBundle the resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //This method is used to initialize the controller.
        viewAllAppointments.setSelected(true); //Sets the view all appointments radio button to be selected.
        getZoneId(); //Calls the method to get the current timezone ID.
        appointmentsTable.getItems().clear(); //Clears the appointments table.
        viewAllAppointments(); //Calls the method to view all appointments.
    }
}
