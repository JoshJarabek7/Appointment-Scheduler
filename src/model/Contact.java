package model;

/**
 * The class for the contact
 *
 * @author Joshua Jarabek
 */
public class Contact { //Class for the contact
    /**
     * The Id.
     */
    private int id; //The contact ID
    /**
     * The Name.
     */
    private String name; //The contact name
    /**
     * The Email.
     */
    private String email; //The contact email

    /**
     * Instantiates a new Contact.
     *
     * @param id    the id
     * @param name  the name
     * @param email the email
     */
    public Contact(int id, String name, String email) { //Constructor for the contact
        this.id = id; //Set the contact ID
        this.name = name; //Set the contact name
        this.email = email; //Set the contact email
    }

    /**
     * Gets id.
     * Method for getting the contact ID
     *
     * @return the id
     */
    public int getId() { //Method for getting the contact ID
        return id; //Return the contact ID
    }

    /**
     * Gets name.
     * Method for getting the contact name
     *
     * @return the name
     */
    public String getName() { //Method for getting the contact name
        return name; //Return the contact name
    }

    /**
     * Gets email.
     * Method for getting the contact email
     *
     * @return the email
     */
    public String getEmail() { //Method for getting the contact email
        return email; //Return the contact email
    }

    /**
     * To string string.
     * Method for getting the contact as a string
     *
     * @return the string
     */
    @Override
    public String toString() { //Method for getting the contact as a string
        return this.getName(); //Return the contact name
    }
}
