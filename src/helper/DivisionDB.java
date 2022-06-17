package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for the division database
 * @author Joshua Jarabek
 */
public class DivisionDB { //Class for the division database
    @FXML
    private static ObservableList<Division> divisions = FXCollections.observableArrayList(); //Observable list for the divisions

    /**
     * Gets all divisions.
     * Method for getting all divisions
     * @return all divisions
     */
    public static ObservableList<Division> getAllDivisions() { //Method for getting all the divisions
        try { //Try to get the divisions
            divisions.clear(); //Clear the list
            String getDivisionsStatement = "SELECT * FROM First_Level_Divisions"; //Statement for getting the divisions
            PreparedStatement ps = JDBC.getConnection().prepareStatement(getDivisionsStatement); //Prepare the statement
            ResultSet resultSet = ps.executeQuery(); //Execute the query
            while (resultSet.next()) { //While there are results...
                int divisionId = resultSet.getInt("Division_ID"); //Get the division ID
                String divisionName = resultSet.getString("Division"); //Get the division name
                int countryId = resultSet.getInt("Country_ID"); //Get the country ID
                Division division = new Division(divisionId, divisionName, countryId); //Create a new division
                divisions.add(division); //Add the division to the list
            }
        } catch (SQLException e) { //If there is an error...
            System.out.println("Error: " + e.getMessage()); //Print the error
            e.printStackTrace(); //Print the stack trace
        } return divisions; //Return the list
    }
}
