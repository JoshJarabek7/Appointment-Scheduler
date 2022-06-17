package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import model.User;

import java.sql.*;

/**
 * The class for the user database
 * @author Joshua Jarabek
 */
public class UserDB { //Class for the user database
    @FXML
    private static ObservableList<User> users = FXCollections.observableArrayList(); //Observable list for the users

    /**
     * Gets all users.
     * Method for getting all users
     * @return all users
     */
    public static ObservableList<User> getAllUsers() { //Method for getting all the users
        try { //Try to get the users
            users.clear(); //Clear the list
            String getUsersStatement = "SELECT * FROM Users"; //Statement for getting the users
            PreparedStatement ps = JDBC.getConnection().prepareStatement(getUsersStatement); //Prepare the statement
            ResultSet resultSet = ps.executeQuery(); //Execute the query
            while (resultSet.next()) { //While there are results...
                int id= resultSet.getInt("User_ID"); //Get the user ID
                String username = resultSet.getString("User_Name"); //Get the user name
                String password = resultSet.getString("Password"); //Get the user password
                User user = new User(id, username, password); //Create a new user
                users.add(user); //Add the user to the list
            }
        } catch (SQLException e) { //If there is an error...
            System.out.println("Error: " + e.getMessage()); //Print the error
            e.printStackTrace(); //Print the stack trace
        } return users; //Return the list
    }
}