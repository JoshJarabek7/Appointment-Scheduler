package helper;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * The class for the database
 * @author Joshua Jarabek
 */
public abstract class JDBC { //Class for the JDBC
    private static final String protocol = "jdbc";  //The protocol
    private static final String vendor = ":mysql:"; //The vendor
    private static final String location = "//localhost/"; //The location
    private static final String databaseName = "client_schedule"; //The database name
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; //LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; //Driver reference
    private static final String userName = "sqlUser"; //Username
    public static Connection conn;  //Connection Interface

    /**
     * Open connection.
     * Method for opening a connection
     */
    public static void openConnection() { //Method for opening the connection
        try { //Try to open the connection
            Class.forName(driver); //Locate Driver
            String password = "Passw0rd!"; //Password
            conn = DriverManager.getConnection(jdbcUrl, userName, password); //Reference Connection object
            System.out.println("Connection successful!"); //Print successful connection
        } catch(Exception e) { //If there is an error...
            System.out.println("Error: " + e.getMessage()); //Print the error
            e.printStackTrace(); //Print the stack trace
        }
    }

    /**
     * Gets connection.
     * Method for getting the connection
     * @return the connection
     */
    public static Connection getConnection() { //Method for getting the connection
        return conn; //Return the connection
    }

    /**
     * Close connection.
     * Method for closing the connection
     */
    public static void closeConnection() { //Method for closing the connection
        try { //Try to close the connection
            conn.close(); //Close the connection
            System.out.println("Connection closed!"); //Print successful connection
        } catch(Exception e) { //If there is an error...
            System.out.println("Error: " + e.getMessage()); //Print the error
            e.printStackTrace(); //Print the stack trace
        }
    }
}
