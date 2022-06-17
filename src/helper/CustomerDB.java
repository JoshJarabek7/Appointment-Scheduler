package helper;

import controller.LoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Class for the customer database
 *
 * @author Joshua Jarabek
 */
public class CustomerDB { //Class for the customer database
    /**
     * The constant customers.
     */
    @FXML
    private static ObservableList<Customer> customers = FXCollections.observableArrayList(); //Observable list for the customers

    /**
     * Gets all customers.
     * Method for getting all customers
     *
     * @return all customers
     */
    public static ObservableList<Customer> getAllCustomers() { //Method for getting all customers
        try { //Try to get all customers
            customers.clear(); //Clear the observable list
            String getCustomersStatement = "SELECT * FROM customers"; //Statement for getting all customers
            PreparedStatement ps = JDBC.getConnection().prepareStatement(getCustomersStatement); //Prepare statement for getting all customers
            ResultSet resultSet = ps.executeQuery(); //Execute query for getting all customers
            while (resultSet.next()) { //While there are customers
                int id = resultSet.getInt("Customer_ID"); //Get the customer ID
                String name = resultSet.getString("Customer_Name"); //Get the customer name
                String address = resultSet.getString("Address"); //Get the customer address
                String postalCode = resultSet.getString("Postal_Code"); //Get the customer postal code
                String phone = resultSet.getString("Phone"); //Get the customer phone
                int divisionId = resultSet.getInt("Division_ID"); //Get the customer division ID
                LocalDateTime createDate = resultSet.getTimestamp("Create_Date").toLocalDateTime(); //Get the customer create date
                String createdBy = resultSet.getString("Created_By"); //Get the customer created by
                LocalDateTime lastUpdate = resultSet.getTimestamp("Last_Update").toLocalDateTime(); //Get the customer last update
                String updatedBy = resultSet.getString("Last_Updated_By"); //Get the customer last updated by
                Customer customer = new Customer(id, name, address, postalCode, phone, divisionId, createDate, createdBy, updatedBy, lastUpdate); //Create a new customer
                customers.add(customer); //Add the customer to the observable list
            }
        } catch (SQLException e) { //Catch any SQL exceptions
            System.out.println("Error: " + e.getMessage()); //Print the error message
            e.printStackTrace(); //Print the stack trace
        } return customers; //Return the observable list
    }

    /**
     * Add customer.
     * Method for adding a customer
     *
     * @param customer the customer
     * @throws SQLException the sql exception
     */
    public static void addCustomer(Customer customer) throws SQLException { //Method for adding a customer
        String addCustomerStatement = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID, Create_Date, Created_By, Last_Updated_By, Last_Update) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; //Statement for adding a customer
        PreparedStatement ps = JDBC.getConnection().prepareStatement(addCustomerStatement); //Prepare statement for adding a customer
        ps.setString(1, customer.getName()); //Set the customer name
        ps.setString(2, customer.getAddress()); //Set the customer address
        ps.setString(3, customer.getPostalCode()); //Set the customer postal code
        ps.setString(4, customer.getPhone()); //Set the customer phone
        ps.setInt(5, customer.getDivisionId()); //Set the customer division ID
        ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now())); //Set the customer create date
        ps.setString(7, LoginController.getActiveUsername()); //Set the customer created by
        ps.setString(8, LoginController.getActiveUsername()); //Set the customer last updated by
        ps.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now())); //Set the customer last update
        ps.executeUpdate(); //Execute the update
    }

    /**
     * Delete customer.
     * Method for deleting a customer
     *
     * @param customer the customer
     * @throws SQLException the sql exception
     */
    public static void deleteCustomer(Customer customer) throws SQLException { //Method for deleting a customer
        String deleteCustomerStatement = "DELETE FROM customers WHERE Customer_ID = ?"; //Statement for deleting a customer
        PreparedStatement ps = JDBC.getConnection().prepareStatement(deleteCustomerStatement); //Prepare statement for deleting a customer
        ps.setString(1, String.valueOf(customer.getId())); //Set the customer ID
        ps.executeUpdate(); //Execute the update
    }

    /**
     * Update customer.
     * Method for updating a customer
     *
     * @param customer the customer
     * @throws SQLException the sql exception
     */
    public static void updateCustomer(Customer customer) throws SQLException { //Method for updating a customer
        String updateCustomerStatement = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?"; //Statement for updating a customer
        PreparedStatement ps = JDBC.getConnection().prepareStatement(updateCustomerStatement); //Prepare statement for updating a customer
        ps.setString(1, customer.getName()); //Set the customer name
        ps.setString(2, customer.getAddress()); //Set the customer address
        ps.setString(3, customer.getPostalCode()); //Set the customer postal code
        ps.setString(4, customer.getPhone()); //Set the customer phone
        ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now())); //Set the customer last update
        ps.setString(6, customer.getUpdatedBy()); //Set the customer last updated by
        ps.setString(7, String.valueOf(customer.getDivisionId())); //Set the customer division ID
        ps.setString(8, String.valueOf(customer.getId())); //Set the customer ID
        ps.executeUpdate();
    }
}