package model;

/**
 * The class for the division.
 * @author Joshua Jarabek
 */
public class Division { //Class for the division
    private int id; //The division ID
    private String name; //The division name
    private int countryId; //The division country ID

    /**
     * Instantiates a new Division.
     *
     * @param id        the id
     * @param name      the name
     * @param countryId the country id
     */
    public Division(int id, String name, int countryId) { //Constructor for the division
        this.id = id; //Set the division ID
        this.name = name; //Set the division name
        this.countryId = countryId; //Set the division country ID
    }

    /**
     * Gets id.
     * Method for getting the division ID
     * @return the id
     */
    public int getId() { //Method for getting the division ID
        return id; //Return the division ID
    }

    /**
     * Gets name.
     * Method for getting the division name
     * @return the name
     */
    public String getName() { //Method for getting the division name
        return name; //Return the division name
    }

    /**
     * Gets country id.
     * Method for getting the division country ID
     * @return the country id
     */
    public int getCountryId() { //Method for getting the division country ID
        return countryId; //Return the division country ID
    }

    /**
     * Sets name.
     * Method for setting the division name
     * @param name the name
     */
    public void setName(String name) { //Method for setting the division name
        this.name = name; //Set the division name
    }

    /**
     * To string.
     * Method for getting the division as a string
     * @return the string
     */
    @Override
    public String toString() { //Method for getting the division as a string
        return this.getName(); //Return the division name
    }
}