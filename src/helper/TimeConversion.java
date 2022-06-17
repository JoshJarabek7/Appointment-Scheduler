package helper;

import javafx.scene.control.ComboBox;

import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The TimeConversion class is used to convert times.
 * It's not necessary to create an instance of this class, but it makes it easier to convert times.
 * Especially in the case of this program, where the time zones are often different, it makes it easier to convert times.
 *
 * @author Joshua Jarabek
 */
public class TimeConversion { //Class for converting times
    /**
     * Format date string.
     * This method is used to format a date string.
     *
     * @param localDateTime the local date time to format
     * @return the string of the formatted date
     */
    public static String formatDate(LocalDateTime localDateTime) { //Method for formatting dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy"); //Formatter for the date
        return formatter.format(localDateTime); //Return the formatted date
    }

    /**
     * Format time string.
     * This method is used to format a time string.
     *
     * @param localDateTime the local date time to format
     * @return the string of the formatted time
     */
    public static String formatTime(LocalDateTime localDateTime) { //Method for formatting times
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a"); //Formatter for the time
        return formatter.format(localDateTime); //Return the formatted time
    }

    /**
     * Format time string.
     * This method is used to format a time string.
     *
     * @param localTime the local time to format
     * @return the string of the formatted time
     */
    public static String formatTime(LocalTime localTime) { //Method for formatting times
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a"); //Formatter for the time
        return formatter.format(localTime); //Return the formatted time
    }

    /**
     * Format time string.
     * This method is used to format a time string.
     *
     * @param zonedDateTime the zoned date time to format
     * @return the string of the formatted time
     */
    public static String formatTime(ZonedDateTime zonedDateTime) { //Method for formatting times
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a"); //Formatter for the time
        return formatter.format(zonedDateTime); //Return the formatted time
    }

    /**
     * Populate times combo box.
     * This method is used to populate a combo box with times.
     *
     * @param localTimeComboBox the local time combo box to populate
     * @param startTime         the start time of the combo box
     * @param endTime           the end time of the combo box
     * @return the combo box of times
     */
    public static ComboBox<LocalTime> populateTimes(ComboBox<LocalTime> localTimeComboBox, LocalTime startTime, LocalTime endTime) { //Method for populating times
        while(startTime.isBefore(endTime.plusSeconds(1))) { //While the start time is before the end time...
            localTimeComboBox.getItems().add(startTime); //Add the time to the combo box
            startTime = startTime.plusMinutes(15); //Add 15 minutes to the start time
        } return localTimeComboBox; //Return the combo box
    }

    /**
     * Convert local to eastern local date time.
     * This method is used to convert a local date time to eastern local date time.
     *
     * @param timeToConvert the time to convert to eastern local date time
     * @return the local date time in eastern local date time
     */
    public static LocalDateTime convertLocalToEastern(LocalDateTime timeToConvert) { //Method for converting local times to eastern times
        ZoneId easternTime = ZoneId.of("America/New_York"); //Eastern time
        ZoneId localZone = ZoneId.systemDefault(); //Local time
        ZonedDateTime currentLocalTime = timeToConvert.atZone(localZone); //Current local time
        ZonedDateTime currentEasternTime = currentLocalTime.withZoneSameInstant(easternTime); //Current eastern time
        return currentEasternTime.toLocalDateTime(); //Return the converted time
    }

    /**
     * Compare with business hours boolean.
     * This method is used to compare a time with business hours.
     *
     * @param timeToCompare the time to compare with business hours
     * @return the boolean of whether the time is within business hours
     */
    public static boolean compareWithBusinessHours(LocalDateTime timeToCompare) { //Method for comparing times with business hours
        LocalTime initialStartTime = LocalTime.of(7, 59); //Initial start time
        ZoneId easternZoneId = ZoneId.of("America/New_York"); //Eastern time
        ZonedDateTime easternStartTime = ZonedDateTime.of(timeToCompare.toLocalDate(), initialStartTime, easternZoneId); //Eastern start time
        System.out.println("Eastern Zoned Start Time: " + easternStartTime + " " + easternStartTime.toLocalDateTime()); //Print the eastern start time
        LocalTime initialEndTime = LocalTime.of(22, 0); //Initial end time
        ZonedDateTime easternEndTime = ZonedDateTime.of(timeToCompare.toLocalDate(), initialEndTime, easternZoneId); //Eastern end time
        System.out.println("Eastern Zoned End Time: " + easternEndTime + " " + easternEndTime.toLocalDateTime()); //Print the eastern end time
        LocalDateTime easternTimeToCompare = convertLocalToEastern(timeToCompare); //Convert the local time to eastern time
        return easternTimeToCompare.isAfter(ChronoLocalDateTime.from(easternStartTime)) && easternTimeToCompare.isBefore(ChronoLocalDateTime.from(easternEndTime)); //Return if the time is after the start time and before the end time
    }
}
