package model;

import helper.UserDB;

/**
 * The class for the user.
 *
 * @author Joshua Jarabek
 */
public class User { //Class for the user
    /**
     * The Id.
     */
    private int id; //The user ID
    /**
     * The Username.
     */
    private String username; //The user username
    /**
     * The Password.
     */
    private String password; //The user password

    /**
     * Instantiates a new User.
     *
     * @param id       the id
     * @param username the username
     * @param password the password
     */
    public User(int id, String username, String password) { //Constructor for the user
        this.id = id; //Set the user ID
        this.username = username; //Set the user username
        this.password = password; //Set the user password
    }

    /**
     * Gets id.
     * Method for getting the user ID
     *
     * @return the id
     */
    public int getId() { //Method for getting the user ID
        return id; //Return the user ID
    }

    /**
     * Gets username.
     * Method for getting the user username
     *
     * @return the username
     */
    public String getUsername() { //Method for getting the user username
        return username; //Return the user username
    }

    /**
     * Gets password.
     * Method for getting the user password
     *
     * @return the password
     */
    public String getPassword() { //Method for getting the user password
        return password; //Return the user password
    }

    /**
     * Id to name string.
     * Method for getting the username from the user ID
     *
     * @param id the id
     * @return the string
     */
    public static String idToName(int id) { //Method for getting the username from the ID
        for (User user : UserDB.getAllUsers()) { //For each user in the database
            if (user.getId() == id) { //If the user ID is the same as the ID passed in
                return user.getUsername(); //Return the username
            }
        }
        return ""; //Return an empty string
    }

    /**
     * To string.
     * Method for getting the user as a string
     *
     * @return the string
     */
    @Override
    public String toString() { //Method for getting the user as a string
        return Integer.toString(getId()); //Return the user ID
    }
}