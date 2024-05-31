package Vehicles;

import DateTime.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

/**
 * Junit4 Testing class that test the main functionality of each subclass of the {@link Vehicle} class.
 */
public class VehicleTest{

    /**
     *  Vehicle object used for testing purposes in the {@link VehicleTest} class
     */
    public Vehicle vehicle1;
    /**
     *  Vehicle object used for testing purposes in the {@link VehicleTest} class
     */
    public Vehicle vehicle2;
    /**
     *  Vehicle object used for testing purposes in the {@link VehicleTest} class
     */
    public Vehicle vehicle3;
    /**
     *  Vehicle object used for testing purposes in the {@link VehicleTest} class
     */
    public Vehicle vehicle4;
    /**
     *  Vehicle object used for testing purposes in the {@link VehicleTest} class
     */
    public Vehicle vehicle5;


    /**
     * Common entry time used in all vehicle objects in the {@link VehicleTest} class
     */
    public LocalDateTime entryDateTime;

   /**
     * Method run before each test case to initialize each Vehicle object and entry time for vehicles
     */
   @Before
    public void init(){
        // Initialize entry time, and the different types of vehicles.

        DateTime date = new DateTime(7, 12, 2021, 0, 0, 0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.entryDateTime = LocalDateTime.parse(date.getDateTime(), formatter);

        this.vehicle1 = new Car("yp0101");
        this.vehicle2 = new Van("kf2002");
        this.vehicle3 = new Minibus("LMO001");
        this.vehicle4 = new MiniLorry("ML1002");
        this.vehicle5 = new Motorbike("MB9999");

        this.vehicle1.setEntryTime(date);
        this.vehicle2.setEntryTime(date);
        this.vehicle3.setEntryTime(date);
        this.vehicle4.setEntryTime(date);
        this.vehicle5.setEntryTime(date);
    }

    /**
     * Test method that checks whether each vehicle object returns its plate ID
     */
    @Test
    public void getPlateID(){
        // Test whether each vehicle's plateId is returned correctly

        assertEquals("yp0101", vehicle1.getPlateID());
        assertEquals("kf2002", vehicle2.getPlateID());
        assertEquals("LMO001", vehicle3.getPlateID());
        assertEquals("ML1002", vehicle4.getPlateID());
        assertEquals("MB9999", vehicle5.getPlateID());
    }

    /**
     * Test method that checks whether each vehicle object returns its correct vehicle type
     */
    @Test
    public void getVehicleType(){
        // Test whether each vehicle's type is returned correctly

        assertEquals(VehicleTypes.CAR, vehicle1.getVehicleType());
        assertEquals(VehicleTypes.VAN, vehicle2.getVehicleType());
        assertEquals(VehicleTypes.MINI_BUS, vehicle3.getVehicleType());
        assertEquals(VehicleTypes.MINI_LORRY, vehicle4.getVehicleType());
        assertEquals(VehicleTypes.MOTORBIKE, vehicle5.getVehicleType());
    }

    /**
     * Test method that checks whether each vehicle object returns its correct number of slots needed to park
     */
    @Test
    public void getSlotsNeeded(){
       // Test whether each vehicle's slots needed to park is returned correctly

        assertEquals(1.0, vehicle1.getSlotsNeeded(), 0.01);
        assertEquals(2.0, vehicle2.getSlotsNeeded(), 0.01);
        assertEquals(3.0, vehicle3.getSlotsNeeded(), 0.01);
        assertEquals(3.0, vehicle4.getSlotsNeeded(), 0.01);
        assertEquals(1.0/3.0, vehicle5.getSlotsNeeded(), 0.01);
    }

    /**
     * Test method that checks whether each vehicle object returns correct time taken to park from entry time to current time
     */
    @Test
    public void getTimeParked(){
        // Test whether each vehicle's time parked is returned correctly

        // Get current time
        LocalDateTime currentTime = LocalDateTime.now();

        // Find the difference in hours of the current time and entry time
        double diffInHours = Duration.between(this.entryDateTime, currentTime).toMillis() / 3.6e+6;

        assertEquals(diffInHours, vehicle1.getTimeParked(), 0.1);
        assertEquals(diffInHours, vehicle2.getTimeParked(), 0.1);
        assertEquals(diffInHours, vehicle3.getTimeParked(), 0.1);
        assertEquals(diffInHours, vehicle4.getTimeParked(), 0.1);
        assertEquals(diffInHours, vehicle5.getTimeParked(), 0.1);
    }
}