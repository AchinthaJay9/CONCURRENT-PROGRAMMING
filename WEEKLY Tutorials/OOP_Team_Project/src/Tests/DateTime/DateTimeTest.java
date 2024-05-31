package DateTime;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Junit4 Testing class that test the main functionality of the {@link DateTime} class.
 */
public class DateTimeTest{

    /**
     *  Date time object used for testing purposes in the {@link DateTimeTest} class
     */
    public DateTime date1;
    /**
     *  Date time object used for testing purposes in the {@link DateTimeTest} class
     */
    public DateTime date2;
    /**
     *  Date time object used for testing purposes in the {@link DateTimeTest} class
     */
    public DateTime date3;
    /**
     *  Date time object used for testing purposes in the {@link DateTimeTest} class
     */
    public DateTime date4;
    /**
     *  Date time object used for testing purposes in the {@link DateTimeTest} class
     */
    public DateTime date5;
    /**
     *  Date time object used for testing purposes in the {@link DateTimeTest} class
     */
    public DateTime date6;

   /**
     * Method run before each test case to initialize each Date time object
     */
   @Before
    public void init(){
        this.date1 = new DateTime(12, 7, 2021, 17, 50, 20);
        this.date2 = new DateTime(12, 7, 2021, 8, 50, 20);
        this.date3 = new DateTime(29, 2, 2020, 1, 50, 20);
        this.date4 = new DateTime(12, 2, 2020, 17, 50, 20);
        this.date5 = new DateTime(31, 12, 2020, 0, 0, 1);
        this.date6 = new DateTime(2, 10, 2021, 1, 0, 9);
    }

    /**
     * Test method that checks whether the date component is correctly represented by the date time object
     */
    @Test
    public void getDate(){
        String date1String = this.date1.getDate().getDate();
        String date2String = this.date2.getDate().getDate();
        String date3String = this.date3.getDate().getDate();
        String date4String = this.date4.getDate().getDate();
        String date5String = this.date5.getDate().getDate();
        String date6String = this.date6.getDate().getDate();

        assertEquals("12/07/2021", date1String);
        assertEquals("12/07/2021", date2String);
        assertEquals("29/02/2020", date3String);
        assertEquals("12/02/2020", date4String);
        assertEquals("31/12/2020", date5String);
        assertEquals("02/10/2021", date6String);
    }

    /**
     * Test method that checks whether the time component is correctly represented by the date time object
     */
    @Test
    public void getTime(){
        String date1String = this.date1.getTime().getTime();
        String date2String = this.date2.getTime().getTime();
        String date3String = this.date3.getTime().getTime();
        String date4String = this.date4.getTime().getTime();
        String date5String = this.date5.getTime().getTime();
        String date6String = this.date6.getTime().getTime();

        assertEquals("17:50:20", date1String);
        assertEquals("08:50:20", date2String);
        assertEquals("01:50:20", date3String);
        assertEquals("17:50:20", date4String);
        assertEquals("00:00:01", date5String);
        assertEquals("01:00:09", date6String);

    }

    /**
     * Test method that checks whether both the date and time component is correctly represented by the date time object
     */
    @Test
    public void getDateTime(){
        String date1String = this.date1.getDateTime();
        String date2String = this.date2.getDateTime();
        String date3String = this.date3.getDateTime();
        String date4String = this.date4.getDateTime();
        String date5String = this.date5.getDateTime();
        String date6String = this.date6.getDateTime();

        assertEquals("12/07/2021 17:50:20", date1String);
        assertEquals("12/07/2021 08:50:20", date2String);
        assertEquals("29/02/2020 01:50:20", date3String);
        assertEquals("12/02/2020 17:50:20", date4String);
        assertEquals("31/12/2020 00:00:01", date5String);
        assertEquals("02/10/2021 01:00:09", date6String);
    }

    /**
     * Test method that checks whether the date time object correctly gets the time difference in hours of two date time objects
     */
    @Test
    public void getDateTimeDiffHours(){
        assertEquals( 9.0, date1.getDateTimeDiffHours(date2),0.1);
        assertEquals(12014.8, date2.getDateTimeDiffHours(date3), 0.1);
        assertEquals(6610.8, date6.getDateTimeDiffHours(date5), 0.1);
    }
}