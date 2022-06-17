package model;

import java.time.LocalDateTime;

/**
 * The class for the customer.
 *
 * @author Joshua Jarabek
 */
public class Customer { //Class for the customer
    /**
     * The Id.
     */
    private int id; //The customer ID
    /**
     * The Name.
     */
    private String name; //The customer name
    /**
     * The Address.
     */
    private String address; //The customer address
    /**
     * The Postal code.
     */
    private String postalCode; //The customer postal code
    /**
     * The Phone.
     */
    private String phone; //The customer phone
    /**
     * The Division id.
     */
    private int divisionId; //The customer division ID
    /**
     * The Create date.
     */
    private LocalDateTime createDate; //The customer creation date
    /**
     * The Created by.
     */
    private String createdBy; //The customer created by
    /**
     * The Updated by.
     */
    private String updatedBy; //The customer updated by
    /**
     * The Last update.
     */
    private LocalDateTime lastUpdate; //The customer last update

    /**
     * Instantiates a new Customer.
     *
     * @param id         the id
     * @param name       the name
     * @param address    the address
     * @param postalCode the postal code
     * @param phone      the phone
     * @param divisionId the division id
     * @param createDate the create date
     * @param createdBy  the created by
     * @param updatedBy  the updated by
     * @param lastUpdate the last update
     */
    public Customer(int id, String name, String address, String postalCode, String phone, int divisionId, LocalDateTime createDate, String createdBy, String updatedBy, LocalDateTime lastUpdate) { //Constructor for the customer
        this.id = id; //Set the customer ID
        this.name = name; //Set the customer name
        this.address = address; //Set the customer address
        this.postalCode = postalCode; //Set the customer postal code
        this.phone = phone; //Set the customer phone
        this.divisionId = divisionId; //Set the customer division ID
        this.createDate = createDate; //Set the customer creation date
        this.createdBy = createdBy; //Set the customer created by
        this.lastUpdate = lastUpdate; //Set the customer last update
        this.updatedBy = updatedBy; //Set the customer updated by
    }

    /**
     * Gets id.
     * Method for getting the customer ID
     *
     * @return the id
     */
    public int getId() { //Method for getting the customer ID
        return id; //Return the customer ID
    }

    /**
     * Gets name.
     * Method for getting the customer name
     *
     * @return the name
     */
    public String getName() { //Method for getting the customer name
        return name; //Return the customer name
    }

    /**
     * Gets address.
     * Method for getting the customer address
     *
     * @return the address
     */
    public String getAddress() { //Method for getting the customer address
        return address; //Return the customer address
    }

    /**
     * Gets postal code.
     * Method for getting the customer postal code
     *
     * @return the postal code
     */
    public String getPostalCode() { //Method for getting the customer postal code
        return postalCode; //Return the customer postal code
    }

    /**
     * Gets phone.
     * Method for getting the customer phone
     *
     * @return the phone
     */
    public String getPhone() { //Method for getting the customer phone
        return phone; //Return the customer phone
    }

    /**
     * Gets division id.
     * Method for getting the customer division ID
     *
     * @return the division id
     */
    public int getDivisionId() { //Method for getting the customer division ID
        return divisionId; //Return the customer division ID
    }

    /**
     * Gets create date.
     * Method for getting the customer creation date
     *
     * @return the create date
     */
    public LocalDateTime getCreateDate() { //Method for getting the customer creation date
        return createDate; //Return the customer creation date
    }

    /**
     * Gets created by.
     * Method for getting the customer created by
     *
     * @return the created by
     */
    public String getCreatedBy() { //Method for getting the customer created by
        return createdBy; //Return the customer created by
    }

    /**
     * Gets updated by.
     * Method for getting the customer updated by
     *
     * @return the updated by
     */
    public String getUpdatedBy() { //Method for getting the customer updated by
        return updatedBy; //Return the customer updated by
    }

    /**
     * Gets last update.
     * Method for getting the customer last update
     *
     * @return the last update
     */
    public LocalDateTime getLastUpdate() { //Method for getting the customer last update
        return lastUpdate; //Return the customer last update
    }

    /**
     * Sets id.
     * Method for setting the customer ID
     *
     * @param id the id
     */
    public void setId(int id) { //Method for setting the customer ID
        this.id = id; //Set the customer ID
    }

    /**
     * Sets name.
     * Method for setting the customer name
     *
     * @param name the name
     */
    public void setName(String name) { //Method for setting the customer name
        this.name = name; //Set the customer name
    }

    /**
     * Sets address.
     * Method for setting the customer address
     *
     * @param address the address
     */
    public void setAddress(String address) { //Method for setting the customer address
        this.address = address; //Set the customer address
    }

    /**
     * Sets postal code.
     * Method for setting the customer postal code
     *
     * @param postalCode the postal code
     */
    public void setPostalCode(String postalCode) { //Method for setting the customer postal code
        this.postalCode = postalCode; //Set the customer postal code
    }

    /**
     * Sets phone.
     * Method for setting the customer phone
     *
     * @param phone the phone
     */
    public void setPhone(String phone) { //Method for setting the customer phone
        this.phone = phone; //Set the customer phone
    }

    /**
     * Sets division id.
     * Method for setting the customer division ID
     *
     * @param divisionId the division id
     */
    public void setDivisionId(int divisionId) { //Method for setting the customer division ID
        this.divisionId = divisionId; //Set the customer division ID
    }

    /**
     * Sets create date.
     * Method for setting the customer creation date
     *
     * @param createDate the create date
     */
    public void setCreateDate(LocalDateTime createDate) { //Method for setting the customer creation date
        this.createDate = createDate; //Set the customer creation date
    }

    /**
     * Sets created by.
     * Method for setting the customer created by
     *
     * @param createdBy the created by
     */
    public void setCreatedBy(String createdBy) { //Method for setting the customer created by
        this.createdBy = createdBy; //Set the customer created by
    }

    /**
     * Sets updated by.
     * Method for setting the customer updated by
     *
     * @param updatedBy the updated by
     */
    public void setUpdatedBy(String updatedBy) { //Method for setting the customer updated by
        this.updatedBy = updatedBy; //Set the customer updated by
    }

    /**
     * Sets last update.
     * Method for setting the customer last update
     *
     * @param lastUpdate the last update
     */
    public void setLastUpdate(LocalDateTime lastUpdate) { //Method for setting the customer last update
        this.lastUpdate = lastUpdate; //Set the customer last update
    }

    /**
     * To string string.
     * Method for converting the customer to a string
     *
     * @return the string
     */
    @Override
    public String toString() { //Method for getting the customer as a string
        return Integer.toString(getId()); //Return the customer ID
    }
}