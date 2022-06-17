package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import model.Countries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for the country database
 *
 * @author Joshua Jarabek
 */
public class CountriesDB { //Class for the countries database
    /**
     * The constant countries.
     */
    @FXML
    private static ObservableList<Countries> countries = FXCollections.observableArrayList(); //Observable list for the countries

    /**
     * Gets all countries.
     * Method for getting all countries
     *
     * @return the all countries
     */
    public static ObservableList<Countries> getAllCountries() { //Method for getting all the countries
        try { //Try to get all the countries
            countries.clear(); //Clear the observable list
            String getCountriesStatement = "SELECT * FROM Countries"; //Statement for getting all the countries
            PreparedStatement ps = JDBC.getConnection().prepareStatement(getCountriesStatement); //Prepare the statement
            ResultSet resultSet = ps.executeQuery(); //Execute the statement
            while (resultSet.next()) { //While there are results...
                int countryId = resultSet.getInt("Country_ID"); //Get the country id
                String countryName = resultSet.getString("Country"); //Get the country name
                Countries country = new Countries(countryId, countryName); //Create a new country
                countries.add(country); //Add the country to the observable list
            }
        } catch (SQLException e) { //If there is an error...
            System.out.println("Error: " + e.getMessage()); //Print the error
            e.printStackTrace(); //Print the stack trace
        } return countries; //Return the observable list
    }
}
