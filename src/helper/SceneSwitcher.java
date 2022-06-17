package helper;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * The class for SceneSwitcher.
 *
 * @author Joshua Jarabek Created in order to make it easier to switch scenes. The code block for switching scenes tends to be very long. This class makes it short and easy to switch scenes.
 */
public class SceneSwitcher { //Class for making scene switching easier
    /**
     * Switch screen.
     * This method is used to switch screens.
     *
     * @param event     the event that triggers the switch
     * @param sceneName the scene name to switch to
     * @throws IOException the io exception if the scene cannot be loaded
     */
    public static void switchScreen(ActionEvent event, String sceneName) throws IOException { //Method for switching screens
        String baseName = "/view/"; //Base name for the scene
        String fullName = baseName + sceneName; //Full name for the scene
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow(); //Get the stage
        Parent scene = FXMLLoader.load(Objects.requireNonNull(SceneSwitcher.class.getResource(fullName))); //Load the scene
        stage.setScene(new Scene(scene)); //Set the scene
        stage.show(); //Show the scene
    }
}
