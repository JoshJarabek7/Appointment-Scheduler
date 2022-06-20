package controller;

import helper.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Appointment;
import model.User;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * The class LoginController is used to control the login screen.
 *
 * @author Joshua Jarabek
 */
public class LoginController implements Initializable { //This class is the controller for the login screen.
    /**
     * The Appointment scheduler label.
     */
    @FXML
    private Label appointmentSchedulerLabel; //The title label for the appointment scheduler program.
    /**
     * The Login login button.
     */
    @FXML
    private Button loginLoginButton; //The button to login.
    /**
     * The Login reset button.
     */
    @FXML
    private Button loginResetButton; //The button to reset the login information.
    /**
     * The Password label.
     */
    @FXML
    private Label passwordLabel; //The label for the password field.
    /**
     * The Username label.
     */
    @FXML
    private Label usernameLabel; //The label for the username field.
    /**
     * The Time zone label.
     */
    @FXML
    private Label timeZoneLabel; //The label for the time zone field.
    /**
     * The Username field.
     */
    @FXML
    private TextField usernameField; //The text field for the username input.
    /**
     * The Password field.
     */
    @FXML
    private PasswordField passwordField; //The text field for the password input.
    /**
     * The Login validation.
     */
    public boolean loginValidation = false; //Sets the initial value of the login validation to false.
    /**
     * The Now.
     */
    LocalDateTime now = LocalDateTime.now(); //Gets the current time and stores it in the variable now.
    /**
     * The Appointments.
     */
    ObservableList<Appointment> appointments = AppointmentDB.getAllAppointments(); //Creates an observable list of all appointments.
    /**
     * The Users.
     */
    static ObservableList<User> users = UserDB.getAllUsers(); //Creates an observable list of all users.
    /**
     * The constant activeUserId.
     */
    private static int activeUserId = 0; //Sets the initial value of the active user ID to 0.

    /**
     * On action reset.
     * Resets the login information.
     *
     * @param event The event that triggers the method is the reset button is clicked.
     */
    public void onActionReset(ActionEvent event) { //This method is called when the reset button is clicked.
        usernameField.setText(""); //Sets the username field to an empty string.
        passwordField.setText(""); //Sets the password field to an empty string.
    }

    /**
     * LAMBDA EXPRESSION: On login.
     * Logs the user in.
     * <p>
     * This method is called when the login button is clicked.
     * The method checks the username and password against the database.
     * If the username and password are correct, the method switches to the main menu scene.
     * </p>
     * <p>
     * The lambda expression is used to set the active user ID to the user ID of the user that matches the username and password.
     * This is done by iterating through the users list and comparing the entered userID to IDs in the list.
     * The lambda expression cleans up the code by removing the need for a for loop.
     * The active userID is later used to determine which user is logged in.
     * This allows us to use the active user ID later on in the program for different purposes.
     * </p>
     *
     * @param event the event that triggers the method is the login button is clicked.
     * @throws IOException the io exception
     */
    public void onLogin(ActionEvent event) throws IOException { //This method is called when the login button is clicked.
        String enteredUsername = usernameField.getText(); //Gets the inputted username from the username field and stores it in the variable enteredUsername.
        String enteredPassword = passwordField.getText(); //Gets the inputted password from the password field and stores it in the variable enteredPassword.
        if (validateLogin(enteredUsername, enteredPassword)) { //Checks if the login information is valid.
            loginValidation = true; //If the username and password match, the login validation is set to true.
            //LAMBDA EXPRESSION
            //This lambda expression is used to set the active user ID to the ID of the user that matches the username and password.
            users.forEach(user -> { //For each user in the list of users...
                if (user.getUsername().equals(enteredUsername)) //If the username matches the username entered...
                            activeUserId = user.getId(); //Set the active user ID to the ID of the user.
                    });
            checkUpcomingAppointments(event); //This method checks if there are any upcoming appointments to alert the user.
            SceneSwitcher.switchScreen(event, "MainMenuView.fxml"); //This method switches to the main menu screen.
        } else { //If the username and password do not match...
            loginValidation = false; //...the login validation is set to false.
            Alert alert = new Alert(Alert.AlertType.ERROR); //This alert is used to alert the user that the username and password do not match.
            ResourceBundle bundle = ResourceBundle.getBundle("Nat", Locale.getDefault()); //This resource bundle is used to get the correct language for the alert.
            if (Locale.getDefault().getLanguage().equals("fr")) { //If the language is French...
                alert.setContentText(bundle.getString("Incorrect") + " " + bundle.getString("Username") + " " + bundle.getString("or") + " " + bundle.getString("Password")); //This alert is set to display the correct language.
                alert.show(); //This alert is shown.
            } else if (Locale.getDefault().getLanguage().equals("en")) { //If the user's language is English...
                alert.setContentText(bundle.getString("Incorrect") + " " + bundle.getString("Username") + " " + bundle.getString("or") + " " + bundle.getString("Password")); //This alert is set to display the correct language.
                alert.show(); //This alert is shown.
            }
        } logActivity(); //This method saves the login activity to a text file.
    }

    /**
     * Validate login boolean.
     * Checks if the login information is valid.
     *
     * @param enteredUsername the entered username
     * @param enteredPassword the entered password
     * @return the boolean as true if the login information is valid, false if it is not.
     */
    private boolean validateLogin(String enteredUsername, String enteredPassword) { //This method checks if the username and password match.
        for (User user : users) { //This for loop goes through the list of users.
            if (enteredUsername.equals(user.getUsername()) && enteredPassword.equals(user.getPassword())) { //If the username and password match, the method returns true.
                return true;
            }
        } return false; //If the username and password do not match, the method returns false.
    }

    /**
     * Gets active user id.
     * Gets the active user ID.
     *
     * @return the active user id
     */
    public static int getActiveUserId() { //This method returns the active user ID.
        for (User user : users) { //This for loop goes through the list of users.
            if (user.getId() == activeUserId) { //If the ID of the user is equal to the active user ID, the method returns the ID of the user.
                return user.getId();
            }
        }
        return 0; //If the user's ID does not match the active user ID, the method returns 0.
    }

    /**
     * Gets active id string.
     * Gets the active user ID as a string.
     *
     * @return the active id string
     */
    public static String getActiveIdString() { //This method gets the active user ID to be used in other methods.
        for (User user : users) { //This for loop goes through the list of users.
            if (user.getId() == activeUserId) { //If the user's ID matches the active user ID, the user's ID is returned.
                return Integer.toString(user.getId());
            }
        }
        return ""; //If the user's ID does not match the active user ID, the method returns an empty string.
    }

    /**
     * Gets active username.
     * Gets the active user's username.
     *
     * @return the active username
     */
    public static String getActiveUsername() { //This method gets the active username to be used in other methods.
        for (User user : users) { //This for loop goes through the list of users.
            if (user.getId() == activeUserId) { //If the user's ID matches the active user ID, the user's username is returned.
                return user.getUsername();
            }
        }
        return ""; //If the user's ID does not match the active user ID, the method returns an empty string.
    }

    /**
     * Check upcoming appointments.
     * Checks if there are any upcoming appointments to alert the user.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void checkUpcomingAppointments(ActionEvent event) throws IOException { //This method is called when the login button is clicked and checks if there are any upcoming appointments to alert the user of.
        for (Appointment appointment : appointments) { //For each appointment in the list of appointments...
            if (appointment.getUserId() == getActiveUserId() && (appointment.getStartDateTime().isEqual(now) || appointment.getStartDateTime().isBefore(now.plusMinutes(15)))) {
                AlertHelper alertHelper = new AlertHelper("Information", "Upcoming Appointment", "You have appointment %d with %d at %s".formatted(appointment.getAppointmentId(), appointment.getContactId(), appointment.getStartDateTime()), Alert.AlertType.INFORMATION); //...the user is alerted that there is an appointment.
                alertHelper.showAlert(event, "MainMenuView.fxml"); //Switches to the main menu screen.
            } else {
                AlertHelper alertHelper = new AlertHelper("Information", "No Upcoming Appointments", "There are no upcoming appointments", Alert.AlertType.INFORMATION); //...the user is alerted that there are no appointments.
                alertHelper.showAlert(event, "MainMenuView.fxml"); //Switches to the main menu screen.
            }
        }
    }

    /**
     * LAMBDA EXPRESSION: Log activity.
     * Logs the login activity to a text file.
     *
     * @throws IOException the io exception <p> The method is called when the login button is clicked. The method logs the login activity to a text file. The file writer and print writer are used to write to the login activity text file. The runnable and string are used to get the message to be logged. The print writer prints the message to the login activity text file. The thread is used to log the message. </p>
     */
//LAMBDA EXPRESSION
    public void logActivity() throws IOException { //This method is called when the login button is clicked and logs the login activity to a text file.
        FileWriter writer = new FileWriter("src/login_activity.txt", true); //This file writer is used to write to the login activity text file.
        PrintWriter printWriter = new PrintWriter(writer); //This print writer is used to write to the login activity text file.
        Runnable message= () -> { //This runnable is used to get the message to be logged.
            String a = LocalDateTime.now() + "\n" + (loginValidation ? "Successful" : "Failed") + "\n" + usernameField.getText() + "\n"; //This string is used to get the message to be logged.
            printWriter.print(a); //This print writer prints the message to the login activity text file.
        };
        new Thread(message).start(); //This thread is used to log the message.
    }

    /**
     * Initialize.
     * Initializes the login screen.
     *
     * @param url            the url
     * @param resourceBundle the resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //This method is called when the login screen is loaded.
        ResourceBundle bundle = ResourceBundle.getBundle("Nat", Locale.getDefault()); //This bundle is used to get the language of the user.
        if (Locale.getDefault().getLanguage().equals("fr")) { //If the language is French...
            timeZoneLabel.setText(bundle.getString("Location") + ": " + "\n" + ZoneId.systemDefault()); //This sets the time zone label to the user's time zone.
            appointmentSchedulerLabel.setText(bundle.getString("AppointmentScheduler")); //This sets the appointment scheduler label to French.
            usernameLabel.setText(bundle.getString("Username")); //This sets the username label to French.
            passwordLabel.setText(bundle.getString("Password")); //This sets the password label to French.
            loginLoginButton.setText(bundle.getString("Login")); //This sets the login button to French.
            loginResetButton.setText(bundle.getString("Reset")); //This sets the reset button to French.
            usernameField.setPromptText(bundle.getString("EnterUsername")); //This sets the username field to French.
            passwordField.setPromptText(bundle.getString("EnterPassword")); //This sets the password field to French.
        } else if (Locale.getDefault().getLanguage().equals("en")) { //If the language is English...
            timeZoneLabel.setText(bundle.getString("Location") + ": " + "\n" + ZoneId.systemDefault()); //Sets the time zone label to the user's time zone.
            appointmentSchedulerLabel.setText(bundle.getString("AppointmentScheduler")); //Sets the appointment scheduler label to English.
            usernameLabel.setText(bundle.getString("Username")); //Sets the username label to English.
            passwordLabel.setText(bundle.getString("Password")); //Sets the password label to English.
            loginLoginButton.setText(bundle.getString("Login")); //Sets the login button to English.
            loginResetButton.setText(bundle.getString("Reset")); //Sets the reset button to English.
            usernameField.setPromptText(bundle.getString("EnterUsername")); //Sets the username field to English.
            passwordField.setPromptText(bundle.getString("EnterPassword")); //Sets the password field to English.
        }
    }
}