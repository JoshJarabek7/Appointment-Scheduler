package helper;

import controller.LoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Class for the appointment database
 *
 * @author Joshua Jarabek
 */
public class AppointmentDB { //Class for the appointment database
    /**
     * The constant appointments.
     */
    @FXML
    private static ObservableList<Appointment> appointments = FXCollections.observableArrayList(); //Observable list for the appointments

    /**
     * Method for getting all appointments
     *
     * @return ObservableList<Appointment>  observable list
     */
    public static ObservableList<Appointment> getAllAppointments(){ //Method for getting all appointments
        try { //Try to get all appointments
            appointments.clear(); //Clear the list
            String getAllAppointmentsStatement = "SELECT * FROM Appointments"; //Statement for getting all appointments
            PreparedStatement ps = JDBC.getConnection().prepareStatement(getAllAppointmentsStatement); //Prepare the statement
            ResultSet resultSet = ps.executeQuery(); //Execute the statement
            while (resultSet.next()) { //While there are results...
                int appointmentId = resultSet.getInt("Appointment_ID"); //Get the appointment id
                String title = resultSet.getString("Title"); //Get the title
                String description = resultSet.getString("Description"); //Get the description
                String location = resultSet.getString("Location"); //Get the location
                String type = resultSet.getString("Type"); //Get the type
                LocalDateTime startDateTime = resultSet.getTimestamp("Start").toLocalDateTime(); //Get the start date and time
                LocalDateTime endDateTime = resultSet.getTimestamp("End").toLocalDateTime(); //Get the end date and time
                int contactId = resultSet.getInt("Contact_ID"); //Get the contact id
                int customerId = resultSet.getInt("Customer_ID"); //Get the customer id
                int userId = resultSet.getInt("User_ID"); //Get the user id
                LocalDateTime createDate = resultSet.getTimestamp("Create_Date").toLocalDateTime(); //Get the create date
                String createdBy = resultSet.getString("Created_By"); //Get the created by
                LocalDateTime lastUpdated = resultSet.getTimestamp("Last_Update").toLocalDateTime(); //Get the last updated
                String lastUpdatedBy = resultSet.getString("Last_Updated_By"); //Get the last updated by
                Appointment appointment = new Appointment(appointmentId, title, description, location, type, startDateTime, endDateTime, customerId, userId, contactId, createDate, createdBy, lastUpdated, lastUpdatedBy); //Create a new appointment
                appointments.add(appointment); //Add the appointment to the list
            }
        } catch (SQLException e) { //If there is an error...
            System.out.println("Error: " + e.getMessage()); //Print the error
            e.printStackTrace(); //Print the stack trace
        } return appointments; //Return the list
    }

    /**
     * Add appointment.
     * Method for adding an appointment
     *
     * @param appointment the appointment
     * @throws SQLException the sql exception
     */
    public static void addAppointment(Appointment appointment) throws SQLException { //Method for adding an appointment
        String addAppointmentStatement = "INSERT INTO Appointments (Title, Description, Location, Type, Start, End, Contact_ID, Customer_ID, User_ID, Create_Date, Created_By, Last_Update, Last_Updated_By) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; //Statement for adding an appointment
        PreparedStatement ps = JDBC.getConnection().prepareStatement(addAppointmentStatement); //Prepare the statement
        ps.setString(1, appointment.getTitle()); //Set the title
        ps.setString(2, appointment.getDescription()); //Set the description
        ps.setString(3, appointment.getLocation()); //Set the location
        ps.setString(4, appointment.getType()); //Set the type
        ps.setTimestamp(5, Timestamp.valueOf(appointment.getStartDateTime())); //Set the start date and time
        ps.setTimestamp(6, Timestamp.valueOf(appointment.getEndDateTime())); //Set the end date and time
        ps.setString(7, String.valueOf(appointment.getContactId())); //Set the contact id
        ps.setString(8, String.valueOf(appointment.getCustomerId())); //Set the customer id
        ps.setString(9, String.valueOf(appointment.getUserId())); //Set the user id
        ps.setTimestamp(10, Timestamp.valueOf(appointment.getCreateDate())); //Set the creation date
        ps.setString(11, appointment.getCreatedBy()); //Set the created by
        ps.setTimestamp(12, Timestamp.valueOf(appointment.getLastUpdated())); //Set the last updated
        ps.setString(13, appointment.getLastUpdatedBy()); //Set the last updated by
        ps.executeUpdate(); //Execute the statement
    }

    /**
     * Update appointment.
     * Method for updating an appointment
     *
     * @param appointment the appointment
     * @throws SQLException the sql exception
     */
    public static void updateAppointment(Appointment appointment) throws SQLException { //Method for updating an appointment
        String updateAppointmentStatement = "UPDATE Appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ? WHERE Appointment_ID = ?"; //Statement for updating an appointment
        PreparedStatement ps = JDBC.getConnection().prepareStatement(updateAppointmentStatement); //Prepare the statement
        ps.setString(1, appointment.getTitle()); //Set the title
        ps.setString(2, appointment.getDescription()); //Set the description
        ps.setString(3, appointment.getLocation()); //Set the location
        ps.setString(4, appointment.getType()); //Set the type
        ps.setTimestamp(5, Timestamp.valueOf(appointment.getStartDateTime())); //Set the start date and time
        ps.setTimestamp(6, Timestamp.valueOf(appointment.getEndDateTime())); //Set the end date and time
        ps.setString(7, String.valueOf(appointment.getCustomerId())); //Set the customer id
        ps.setString(8, String.valueOf(appointment.getUserId())); //Set the user id
        ps.setString(9, String.valueOf(appointment.getContactId())); //Set the contact id
        ps.setTimestamp(10, Timestamp.valueOf(appointment.getCreateDate())); //Set the creation date
        ps.setString(11, appointment.getCreatedBy()); //Set the created by
        ps.setTimestamp(12, Timestamp.valueOf(LocalDateTime.now())); //Set the last updated
        ps.setString(13, LoginController.getActiveIdString()); //Set the last updated by
        ps.setString(14, String.valueOf(appointment.getAppointmentId())); //Set the appointment id
        ps.executeUpdate(); //Execute the statement
    }

    /**
     * Delete appointment.
     * Method for deleting an appointment
     *
     * @param appointmentId the appointment id
     * @throws SQLException the sql exception
     */
    public static void deleteAppointment(int appointmentId) throws SQLException { //Method for deleting an appointment
        String deleteAppointmentStatement = "DELETE FROM Appointments WHERE Appointment_ID = ?"; //Statement for deleting an appointment
        PreparedStatement ps = JDBC.getConnection().prepareStatement(deleteAppointmentStatement); //Prepare the statement
        ps.setString(1, String.valueOf(appointmentId)); //Set the appointment id
        ps.executeUpdate(); //Execute the statement
    }
}
