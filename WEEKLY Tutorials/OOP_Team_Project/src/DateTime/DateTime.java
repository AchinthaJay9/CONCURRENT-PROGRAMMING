package DateTime;

import java.io.Serializable;

/**
 * Class is used to represent a particular Date and Time
 */
public class DateTime implements Serializable{

    /**
     * Represents the {@link Date} component of the {@link DateTime} class
     */
    private Date date;
    /**
     * Represents the {@link Time} component of the {@link DateTime} class
     */
    private Time time;

    /**
     * Default constructor of the {@link DateTime} class
     * Initializes {@link Date} component and {@link Time} component
     */
    public DateTime(){
        // Initialize the time component
        this.time = new Time();
        // Initialize the date component
        this.date = new Date();
    }

    /**
     * Alternative constructor of the {@link DateTime} class
     * Constructor is used when the hour, minutes, seconds, day, month, and year is already provided
     *
     * @param day integer representing day of a particular date
     * @param month integer representing month of a particular date
     * @param year integer representing year of a particular date
     * @param hour integer representing hour of a particular time
     * @param minutes integer representing minutes of a particular time
     * @param seconds integer representing seconds of a particular time
     */
    public DateTime(int day, int month, int year, int hour, int minutes, int seconds){

        // Initialize the time component
        this.time = new Time(hour, minutes, seconds);
        // Initialize the date component
        this.date = new Date(day, month, year);

        // Check if the entered Date&Time is valid
        try {
            boolean valid = this.isValidDateTime();
            if(!valid){
                throw new IllegalArgumentException("Date&Time Entered is invalid. Format used is dd/mm/yyyy hh:mm:ss");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @return date of this dateTime
     */
    public Date getDate(){
        return date;
    }

    /**
     * @return time of this dateTime
     */
    public Time getTime(){
        return time;
    }

    /**
     * @return string version of this dateTime in dd/mm/yyyy HH:MM:SS format
     */
    public String getDateTime(){
        return this.date.getDate() + " " + this.time.getTime();
    }

    /**
     * @return double representing the dateTime in terms of seconds
     */
    public double getDateTimeInSeconds(){
        return this.date.getDateInSeconds() + this.time.getTimeInSeconds();
    }

    /**
     * Checks if the dateTime entered is a proper and valid dateTime in dd/mm/yyyy HH:MM:SS format
     *
     * @return true if this dateTime is valid
     */
    private boolean isValidDateTime() {
        // Check if time component is valid
        boolean validDate  = this.time.isValidTime();
        // Check if date component is valid
        boolean validTime  = this.date.isValidDate();

        if(validTime && validDate){
            return true;
        }

        return false;
    }

    /**
     * @return double representing the difference of two dateTimes in terms of hours
     *
     * @param otherTime time in which entry time difference is calculated on
     */
    public double getDateTimeDiffHours(DateTime otherTime) {
        double diffInSeconds = this.getDateTimeInSeconds() - otherTime.getDateTimeInSeconds();
        return (diffInSeconds / 3600);
    }

    /**
     * @return string version of this dateTime
     */
    @Override
    public String toString() {
        return "DateTime{" +
                "date=" + date +
                ", time=" + time +
                '}';
    }
}