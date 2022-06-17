package model;


import java.time.LocalDateTime;

/**
 * The Appointment class is used to create appointments.
 * @author Joshua Jarabek
 */
public class Appointment { //Class for the appointment
    private int appointmentId; //The appointment ID
    private String title; //The appointment title
    private String description; //The appointment description
    private String location; //The appointment location
    private String type; //The appointment type
    private LocalDateTime startDateTime; //The appointment start date and time
    private LocalDateTime endDateTime; //The appointment end date and time
    private int customerId; //The appointment customer ID
    private int userId; //The appointment user ID
    private int contactId; //The appointment contact ID
    private LocalDateTime createDate; //The appointment creation date
    private LocalDateTime lastUpdated; //The appointment last updated date
    private String lastUpdatedBy; //The appointment last updated by
    private String createdBy; //The appointment created by

    /**
     * Instantiates a new Appointment.
     *
     * @param appointmentId the appointment id
     * @param title         the title
     * @param description   the description
     * @param location      the location
     * @param type          the type
     * @param startDateTime the start date time
     * @param endDateTime   the end date time
     * @param customerId    the customer id
     * @param userId        the user id
     * @param contactId     the contact id
     * @param createDate    the create date
     * @param createdBy     the created by
     * @param lastUpdated   the last updated
     * @param lastUpdatedBy the last updated by
     */
    public Appointment(int appointmentId, String title, String description, String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime, int customerId, int userId, int contactId, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdated, String lastUpdatedBy) { //Constructor for the appointment
        this.appointmentId = appointmentId; //Set the appointment ID
        this.title = title; //Set the appointment title
        this.description = description; //Set the appointment description
        this.location = location; //Set the appointment location
        this.type = type; //Set the appointment type
        this.startDateTime = startDateTime; //Set the appointment start date and time
        this.endDateTime = endDateTime; //Set the appointment end date and time
        this.customerId = customerId; //Set the appointment customer ID
        this.userId = userId; //Set the appointment user ID
        this.contactId = contactId; //Set the appointment contact ID
        this.createDate = createDate; //Set the appointment creation date
        this.lastUpdated = lastUpdated; //Set the appointment last updated date
        this.lastUpdatedBy = lastUpdatedBy; //Set the appointment last updated by
        this.createdBy = createdBy; //Set the appointment created by
    }

    /**
     * Gets appointment id.
     * Method for getting the appointment ID.
     * @return the appointment id
     */
    public int getAppointmentId() { //Method for getting the appointment ID
        return appointmentId; //Return the appointment ID
    }

    /**
     * Gets title.
     * Method for getting the appointment title.
     * @return the title
     */
    public String getTitle() { //Method for getting the appointment title
        return title; //Return the appointment title
    }

    /**
     * Gets description.
     * Method for getting the appointment description.
     * @return the description
     */
    public String getDescription() { //Method for getting the appointment description
        return description; //Return the appointment description
    }

    /**
     * Gets location.
     * Method for getting the appointment location.
     * @return the location
     */
    public String getLocation() { //Method for getting the appointment location
        return location; //Return the appointment location
    }

    /**
     * Gets type.
     * Method for getting the appointment type.
     * @return the type
     */
    public String getType() { //Method for getting the appointment type
        return type; //Return the appointment type
    }

    /**
     * Gets start date time.
     * Method for getting the appointment start date and time.
     * @return the start date time
     */
    public LocalDateTime getStartDateTime() { //Method for getting the appointment start date and time
        return startDateTime; //Return the appointment start date and time
    }

    /**
     * Gets end date time.
     * Method for getting the appointment end date and time.
     * @return the end date time
     */
    public LocalDateTime getEndDateTime() { //Method for getting the appointment end date and time
        return endDateTime; //Return the appointment end date and time
    }

    /**
     * Gets customer id.
     * Method for getting the appointment customer ID.
     * @return the customer id
     */
    public int getCustomerId() { //Method for getting the appointment customer ID
        return customerId; //Return the appointment customer ID
    }

    /**
     * Gets user id.
     * Method for getting the appointment user ID.
     * @return the user id
     */
    public int getUserId() { //Method for getting the appointment user ID
        return userId; //Return the appointment user ID
    }

    /**
     * Gets contact id.
     * Method for getting the appointment contact ID.
     * @return the contact id
     */
    public int getContactId() { //Method for getting the appointment contact ID
        return contactId; //Return the appointment contact ID
    }

    /**
     * Gets create date.
     * Method for getting the appointment creation date.
     * @return the create date
     */
    public LocalDateTime getCreateDate() { //Method for getting the appointment creation date
        return createDate; //Return the appointment creation date
    }

    /**
     * Gets created by.
     * Method for getting the appointment created by.
     * @return the created by
     */
    public String getCreatedBy() { //Method for getting the appointment created by
        return createdBy; //Return the appointment created by
    }

    /**
     * Gets last updated.
     * Method for getting the appointment last updated date.
     * @return the last updated
     */
    public LocalDateTime getLastUpdated() { //Method for getting the appointment last updated date
        return lastUpdated; //Return the appointment last updated date
    }

    /**
     * Gets last updated by.
     * Method for getting the appointment last updated by.
     * @return the last updated by
     */
    public String getLastUpdatedBy() { //Method for getting the appointment last updated by
        return lastUpdatedBy; //Return the appointment last updated by
    }

    /**
     * Sets title.
     * Method for setting the appointment title.
     * @param title the title
     */
    public void setTitle(String title) { //Method for setting the appointment title
        this.title = title; //Set the appointment title
    }

    /**
     * Sets description.
     * Method for setting the appointment description.
     * @param description the description
     */
    public void setDescription(String description) { //Method for setting the appointment description
        this.description = description; //Set the appointment description
    }

    /**
     * Sets location.
     * Method for setting the appointment location.
     * @param location the location
     */
    public void setLocation(String location) { //Method for setting the appointment location
        this.location = location; //Set the appointment location
    }

    /**
     * Sets type.
     * Method for setting the appointment type.
     * @param type the type
     */
    public void setType(String type) { //Method for setting the appointment type
        this.type = type; //Set the appointment type
    }

    /**
     * Sets start date time.
     * Method for setting the appointment start date and time.
     * @param startDateTime the start date time
     */
    public void setStartDateTime(LocalDateTime startDateTime) { //Method for setting the appointment start date and time
        this.startDateTime = startDateTime; //Set the appointment start date and time
    }

    /**
     * Sets end date time.
     * Method for setting the appointment end date and time.
     * @param endDateTime the end date time
     */
    public void setEndDateTime(LocalDateTime endDateTime) { //Method for setting the appointment end date and time
        this.endDateTime = endDateTime; //Set the appointment end date and time
    }

    /**
     * Sets customer id.
     * Method for setting the appointment customer ID.
     * @param customerId the customer id
     */
    public void setCustomerId(int customerId) { //Method for setting the appointment customer ID
        this.customerId = customerId; //Set the appointment customer ID
    }

    /**
     * Sets user id.
     * Method for setting the appointment user ID.
     * @param userId the user id
     */
    public void setUserId(int userId) { //Method for setting the appointment user ID
        this.userId = userId; //Set the appointment user ID
    }

    /**
     * Sets contact id.
     * Method for setting the appointment contact ID.
     * @param contactId the contact id
     */
    public void setContactId(int contactId) { //Method for setting the appointment contact ID
        this.contactId = contactId; //Set the appointment contact ID
    }

    /**
     * Sets create date.
     * Method for setting the appointment creation date.
     * @param createDate the create date
     */
    public void setCreateDate(LocalDateTime createDate) { //Method for setting the appointment creation date
        this.createDate = createDate; //Set the appointment creation date
    }

    /**
     * Sets last updated.
     * Method for setting the appointment last updated date.
     * @param lastUpdated the last updated
     */
    public void setLastUpdated(LocalDateTime lastUpdated) { //Method for setting the appointment last updated date
        this.lastUpdated = lastUpdated; //Set the appointment last updated date
    }

    /**
     * Sets last updated by.
     * Method for setting the appointment last updated by.
     * @param lastUpdatedBy the last updated by
     */
    public void setLastUpdatedBy(String lastUpdatedBy) { //Method for setting the appointment last updated by
        this.lastUpdatedBy = lastUpdatedBy; //Set the appointment last updated by
    }

    /**
     * Sets created by.
     * Method for setting the appointment created by.
     * @param createdBy the created by
     */
    public void setCreatedBy(String createdBy) { //Method for setting the appointment created by
        this.createdBy = createdBy; //Set the appointment created by
    }
}