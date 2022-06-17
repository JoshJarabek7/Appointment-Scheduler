package helper;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;

/**
 * The AlertHelper class is used to create alerts.
 * It's not necessary to create an instance of this class, but it makes it easier to create alerts.
 * Object-Oriented Programming is only helpful if you use it.
 * OOP makes your code more readable and easier to maintain.
 * Java can become spaghetti code very quickly.
 * @author Joshua Jarabek
 */
public class AlertHelper { //AlertHelper is a helper class for the Alerts throughout the program.
    private String title; //The title of the alert.
    private String header; //The header of the alert.
    private String content; //The content of the alert.
    private Alert alert; //The alert itself.

    /**
     * Instantiates a new Alert helper.
     * This constructor is used to create an alert.
     * @param title     the title of the alert.
     * @param header    the header of the alert.
     * @param content   the content of the alert.
     * @param alertType the alert type of the alert.
     */
    public AlertHelper(String title, String header, String content, Alert.AlertType alertType) { //Constructor for the AlertHelper.
        this.title = title; //Sets the title of the alert.
        this.header = header; //Sets the header of the alert.
        this.content = content; //Sets the content of the alert.
        this.alert = new Alert(alertType); //Creates the alert.
    }

    /**
     * Show alert.
     * This method is used to show the alert, as well as switch to the next scene.
     * @param event            the event
     * @param screenToSwitchTo the screen to switch to
     * @throws IOException the io exception
     */
    public void showAlert(ActionEvent event, String screenToSwitchTo) throws IOException { //Shows the alert and switches to the specified screen.
        alert.setTitle(title); //Sets the title of the alert.
        alert.setHeaderText(header); //Sets the header of the alert.
        alert.setContentText(content); //Sets the content of the alert.
        Optional<ButtonType> result = alert.showAndWait(); //Shows the alert.
        if (result.isPresent() && result.get() == ButtonType.OK) { //If the user clicks OK...
            SceneSwitcher.switchScreen(event, screenToSwitchTo); //Switch screens.
        }
    }

    /**
     * Show alert but stay.
     * This method is used to show the alert, but stay on the current scene.
     */
    public void showAlertButStay() { //Shows the alert, but stays on the current screen.
        alert.setTitle(title); //Sets the title of the alert.
        alert.setHeaderText(header); //Sets the header of the alert.
        alert.setContentText(content); //Sets the content of the alert.
        alert.showAndWait(); //Shows the alert.
    }

    /**
     * Gets result.
     * This method is used to get the result of the alert.
     * Some alerts have a button that the user can click, but also require to stay on the current screen.
     * This alleviates having 3 different methods for the same thing.
     * @return the result
     */
    public Optional<ButtonType> getResult() {
        return alert.showAndWait();
    } //Gets the result of the alert.
}
