package DateTime;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

/**
 * The Class represents the Date component of the {@link DateTime} class
 */
public class Date implements Serializable{

    /**
     * Represents the day in the {@link Date} class
     */
    private int day;
    /**
     * Represents the month in the {@link Date} class
     */
    private int month;
    /**
     * Represents the year in the {@link Date} class
     */
    private int year;

    /**
     * Hashmap used to store the number of days in each month
     * Later the days for each month are modified according to leap years
     */
    private Map<Integer, Integer> maxDaysInMonth = new HashMap<Integer, Integer>(){{
        put(1, 31); put(2, 28); put(3, 31); put(4, 30); put(5, 31); put(6, 30);
        put(7, 31); put(8, 31); put(9, 30); put(10, 31); put(11, 30); put(12, 31);
    }};

    /**
     * Default constructor of the {@link Date} class
     * Initializes day, month, and year with help of {@link LocalDateTime}
     */
    public Date() {
        // Get current LocalDateTime
        LocalDateTime currentTime = LocalDateTime.now();
        // Initialize day
        this.day = currentTime.getDayOfMonth();
        // Initialize month
        this.month = currentTime.getMonthValue();
        // Initialize year
        this.year = currentTime.getYear();
    }

    /**
     * Alternative constructor of the {@link Date} class
     * Constructor is used when the day, month, and year is already provided
     *
     * @param day integer representing day of a particular date
     * @param month integer representing month of a particular date
     * @param year integer representing year of a particular date
     */
    public Date(int day, int month, int year) {
        // set day
        this.day = day;
        // set month
        this.month = month;
        // set year
        this.year = year;

        // Check if the entered Date is valid
        try {
            boolean valid = this.isValidDate();
            if(!valid){
                throw new IllegalArgumentException("Date Entered is invalid. Format used is dd/mm/yyyy");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @return day of this date
     */
    public int getDay() {
        return day;
    }

    /**
     * @return month of this date
     */
    public int getMonth() {
        return month;
    }

    /**
     * @return year of this date
     */
    public int getYear() {
        return year;
    }

    /**
     * @return string version of this date in dd/mm/yyyy format
     */
    public String getDate(){
        String formattedDay = String.format("%02d", this.day);
        String formattedMonth = String.format("%02d", this.month);

        return formattedDay + "/" + formattedMonth + "/" + this.year;
    }


    /**
     * Checks if this date is the dd/mm/yyyy format and performs a preliminary range check on day, month, and year
     *
     * @return true if this date follows the basic date validation constraints
     */
    private boolean validateDate(){
        // Define the validation pattern in regex
        String pattern = "(0?[1-9]|[12][0-9]|3[01])\\/(0?[1-9]|1[0-2])\\/([0-9]{4})";
        boolean flag = false;
        // Check if the date matches the pattern
        if (this.getDate().matches(pattern)) {
            flag = true;
        }
        return flag;
    }

    /**
     * @param year integer representing year of a date
     * @return true of the year is a leap year.
     */
    private boolean isLeapYear(int year){
        if (((year % 4 == 0) && (year % 100!= 0)) || (year%400 == 0))
            return true;
        else
            return false;
    }

    /**
     * Checks if the date entered is a proper and valid date in dd/mm/yyyy format
     *
     * @return true if this date is valid
     */
    public boolean isValidDate() {
        // Check whether the basic date validation constraints are followed
        boolean valid  = validateDate();
        if(!valid){
            return false;
        }

        // Check if the Date is in a leap year and adjust the days per each month accordingly.
        boolean isLeap = this.isLeapYear(this.year);
        if(isLeap){
            this.maxDaysInMonth.put(2, 29);
        }else{
            this.maxDaysInMonth.put(2, 28);
        }


        // Check if the value given for the month is valid
        if(this.month <=0 || this.month > 12){
            return false;
        }

        // Check if the value given for the day is valid
        int maxDay = this.maxDaysInMonth.get(this.month);
        if(this.day<= 0 || this.day > maxDay){
            return false;
        }

        return true;

    }

    /**
     * @return double representing the date in terms of seconds
     */
    public double getDateInSeconds(){
        double seconds;
        seconds = (this.year * 31556952) + (this.month * 2628000) + (this.day * 86400);
        return seconds;
    }

    /**
     * @return string version of this date
     */
    @Override
    public String toString() {
        return "Date{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", maxDaysInMonth=" + maxDaysInMonth +
                '}';
    }
}
