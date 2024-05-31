package DateTime;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * The Class represents the Time component of the {@link DateTime} class
 */
public class Time implements Serializable{

    /**
     * Represents the hour in the {@link Time} class
     */
    private int hour;
    /**
     * Represents the minutes in the {@link Time} class
     */
    private int minutes;
   /**
     * Represents the seconds in the {@link Time} class
     */
    private int seconds;


    /**
     * Default constructor of the {@link Time} class
     * Initializes hour, minutes, and seconds with help of {@link LocalDateTime}
     */
    public Time(){
        // Get current LocalDateTime
        LocalDateTime currentTime = LocalDateTime.now();
        // Initialize hour
        this.hour = currentTime.getHour();
        // Initialize minutes
        this.minutes = currentTime.getMinute();
        // Initialize seconds
        this.seconds = currentTime.getSecond();
    }

    /**
     * Alternative constructor of the {@link Time} class
     * Constructor is used when the hour, minutes, and seconds is already provided
     *
     * @param hour integer representing hour of a particular time
     * @param minutes integer representing minutes of a particular time
     * @param seconds integer representing seconds of a particular time
     */
    public Time(int hour, int minutes, int seconds){
        // set hour
        this.hour = hour;
        // set minutes
        this.minutes = minutes;
        // set seconds
        this.seconds = seconds;

        // Check if the entered Time is valid
        try {
            boolean valid = this.isValidTime();
            if(!valid){
                throw new IllegalArgumentException("Time Entered is invalid. Format used is HH:MM:SS");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @return hour of this time
     */
    public int getHour(){
        return hour;
    }

    /**
     * @return minutes of this time
     */
    public int getMinutes(){
        return minutes;
    }

    /**
     * @return seconds of this time
     */
    public int getSeconds(){
        return seconds;
    }

    /**
     * @return string version of this time in HH:MM:SS format
     */
    public String getTime(){
        String formattedHour = String.format("%02d", this.hour);
        String formattedMinutes = String.format("%02d", this.minutes);
        String formattedSeconds = String.format("%02d", this.seconds);

        return formattedHour + ":" + formattedMinutes + ":" + formattedSeconds;
    }

    /**
     * Checks if this time is the HH:MM:SS format and performs a preliminary range check on hour, minutes, and seconds
     *
     * @return true if this time follows the basic time validation constraints
     */
    private boolean validateTime() {
        // Define the validation pattern in regex
        String pattern = "([01]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])";
        boolean flag = false;
        // Check if the time matches the pattern
        if (this.getTime().matches(pattern)) {
            flag = true;
        }
        return flag;
    }

    /**
     * Checks if the time entered is a proper and valid time in HH:MM:SS format
     *
     * @return true if this time is valid
     */
    public boolean isValidTime() {
        boolean valid  = this.validateTime();
        if(!valid){
            return false;
        }

        return true;
    }

    /**
     * @return double representing the time in terms of seconds
     */
    public double getTimeInSeconds(){
        double seconds;
        seconds = (this.hour * 3600) + (this.minutes * 60) + this.seconds;
        return seconds;
    }

    /**
     * @return string version of this time
     */
    @Override
    public String toString() {
        return "Time{" +
                "hour=" + hour +
                ", minutes=" + minutes +
                ", seconds=" + seconds +
                '}';
    }
}
