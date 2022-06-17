package model;

/**
 * The class for the countries.
 *
 * @author Joshua Jarabek
 */
public class Countries { //Class for the countries
    /**
     * The Id.
     */
    private int id; //The country ID
    /**
     * The Name.
     */
    private String name; //The country name

    /**
     * Instantiates a new Countries.
     *
     * @param id   the id
     * @param name the name
     */
    public Countries(int id, String name) { //Constructor for the country
        this.id = id; //Set the country ID
        this.name = name; //Set the country name
    }

    /**
     * Gets id.
     * Method for getting the country ID
     *
     * @return the id
     */
    public int getId() { //Method for getting the country ID
        return id; //Return the country ID
    }

    /**
     * Gets name.
     * Method for getting the country name
     *
     * @return the name
     */
    public String getName() { //Method for getting the country name
        return name; //Return the country name
    }

    /**
     * To string string.
     * Method for getting the country as a string
     *
     * @return the string
     */
    @Override
    public String toString() { //Method for getting the country as a string
        return this.getName(); //Return the country name
    }
}