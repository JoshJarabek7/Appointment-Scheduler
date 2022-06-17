package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for the contact database
 *
 * @author Joshua Jarabek
 */
public class ContactDB { //Class for the contact database
    /**
     * The constant contacts.
     */
    @FXML
    private static ObservableList<Contact> contacts = FXCollections.observableArrayList(); //Observable list for the contacts

    /**
     * Gets all contacts.
     * Method for getting all contacts
     *
     * @return the all contacts
     */
    public static ObservableList<Contact> getAllContacts() { //Method for getting all contacts
        try { //Try to get all contacts
            contacts.clear(); //Clear the list
            String getContactsStatement = "SELECT * FROM Contacts"; //Statement for getting all contacts
            PreparedStatement ps = JDBC.getConnection().prepareStatement(getContactsStatement); //Prepare the statement
            ResultSet resultSet = ps.executeQuery(); //Execute the statement

            while (resultSet.next()) { //While there are results...
                int id = resultSet.getInt("Contact_ID"); //Get the contact id
                String name = resultSet.getString("Contact_Name"); //Get the contact name
                String email = resultSet.getString("Email"); //Get the email
                Contact contact = new Contact(id, name, email); //Create a new contact
                contacts.add(contact); //Add the contact to the list
            }
        } catch (SQLException e) { //If there is an error...
            System.out.println("Error: " + e.getMessage()); //Print the error
            e.printStackTrace(); //Print the stack trace
        } return contacts; //Return the list
    }
}
