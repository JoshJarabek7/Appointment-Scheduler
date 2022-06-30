package main;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * The class for Main.
 *
 * @author Joshua Jarabek
 */
public class Main extends Application { //Main class
    /**
     * Start.
     * This method is used to start the program.
     *
     * @param primaryStage the primary stage to start the program
     * @throws Exception the exception if the program cannot be started
     */
    @Override
    public void start(Stage primaryStage) throws Exception { //Method for starting the program
        ResourceBundle bundle = ResourceBundle.getBundle("Nat", Locale.getDefault()); //Get the resource bundle for the current locale
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(("/view/LoginView.fxml")))); //Load the login view
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/main/theme.css")).toExternalForm()); //Add the theme CSS
        primaryStage.setScene(new Scene(root, 900, 600)); //Set the scene
        primaryStage.show(); //Show the scene
    }

    /**
     * Main.
     * This method is used to start the program.
     *
     * @param args the args to start the program
     */
    public static void main(String[] args){ //Main method
        JDBC.openConnection(); //Open the connection
        launch(args); //Launch the program
        JDBC.closeConnection(); //Close the connection
    }
}