package CarPark;

import Vehicles.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;


/**
 * Junit4 Test class that tests the main functionalities of the {@link Floor} class
 */
public class FloorTest{

    /**
     * Represents a single floor instance of the {@link FloorTest} class
     */
    private Floor currentFloor;

    /**
     * Method run before each test case to initialize this floor
     */
    @Before
    public void init(){

        // maximum floor capacity for this floor
        double MAX_FLOOR_CAPACITY = 60.0;

        ArrayList<VehicleTypes> groundPreferredVehicleTypes = new ArrayList<>(Arrays.asList(
                VehicleTypes.MINI_BUS, VehicleTypes.MINI_LORRY
        ));
        ArrayList<VehicleTypes> groundPossibleVehicleTypes = new ArrayList<>(Arrays.asList(
                VehicleTypes.CAR, VehicleTypes.MOTORBIKE, VehicleTypes.VAN, VehicleTypes.MINI_BUS, VehicleTypes.MINI_LORRY
        ));
        this.currentFloor = new Floor(groundPreferredVehicleTypes, groundPossibleVehicleTypes, MAX_FLOOR_CAPACITY);
    }

    /**
     * Test class that checks whether this floor updates the vehicle count correctly
     */
    @Test
    public void getCurrentNumberOfVehicles(){
        // initial number of vehicles
        assertEquals(0, currentFloor.getCurrentNumberOfVehicles(), 0.1);

        // check number of vehicles after a car is added
        Vehicle vehicle1 = new Car("yp0101");
        currentFloor.addVehicle(vehicle1);
        assertEquals(1, currentFloor.getCurrentNumberOfVehicles(), 0.1);

        // check number of vehicles after a van is added
        Vehicle vehicle2 = new Van("kf2002");
        currentFloor.addVehicle(vehicle2);
        assertEquals(2, currentFloor.getCurrentNumberOfVehicles(), 0.1);

        // check number of vehicles after a minibus is added
        Vehicle vehicle3 = new Minibus("LMO001");
        currentFloor.addVehicle(vehicle3);
        assertEquals(3, currentFloor.getCurrentNumberOfVehicles(), 0.1);

        // check number of vehicles after a mini lorry is added
        Vehicle vehicle4 = new MiniLorry("ML1002");
        currentFloor.addVehicle(vehicle4);
        assertEquals(4, currentFloor.getCurrentNumberOfVehicles(), 0.1);

        // check number of vehicles after a motorbike is added
        Vehicle vehicle5 = new Motorbike("MB9999");
        currentFloor.addVehicle(vehicle5);
        assertEquals(5, currentFloor.getCurrentNumberOfVehicles(), 0.1);

    }

    /**
     * Test class that checks whether this floor updates the current capacity correctly
     */
    @Test
    public void getCurrentCapacity(){
        // initial capacity
        assertEquals(0, currentFloor.getCurrentCapacity(), 0.1);

        // check capacity after a car is added
        Vehicle vehicle1 = new Car("yp0101");
        currentFloor.addVehicle(vehicle1);
        assertEquals(1.0, currentFloor.getCurrentCapacity(), 0.1);

        // check capacity after a van is added
        Vehicle vehicle2 = new Van("kf2002");
        currentFloor.addVehicle(vehicle2);
        assertEquals(3.0, currentFloor.getCurrentCapacity(), 0.1);

        // check capacity after a minibus is added
        Vehicle vehicle3 = new Minibus("LMO001");
        currentFloor.addVehicle(vehicle3);
        assertEquals(6.0, currentFloor.getCurrentCapacity(), 0.1);

        // check capacity after a mini lorry is added
        Vehicle vehicle4 = new MiniLorry("ML1002");
        currentFloor.addVehicle(vehicle4);
        assertEquals(9.0, currentFloor.getCurrentCapacity(), 0.1);

        // check capacity after a motorbike is added
        Vehicle vehicle5 = new Motorbike("MB9999");
        currentFloor.addVehicle(vehicle5);
        assertEquals(9.0 + (1.0 / 3.0), currentFloor.getCurrentCapacity(), 0.1);
    }

    /**
     * Test class that checks whether this floor returns the correct number of free slots when vehicles are added
     */
    @Test
    public void getAvailableNumberOfSlots(){
        // initial capacity
        assertEquals(60.0, currentFloor.getAvailableNumberOfSlots(), 0.1);

        // check available number of slots after a car is added
        Vehicle vehicle1 = new Car("yp0101");
        currentFloor.addVehicle(vehicle1);
        assertEquals(60.0 - 1.0, currentFloor.getAvailableNumberOfSlots(), 0.1);

        // check available number of slots after a van is added
        Vehicle vehicle2 = new Van("kf2002");
        currentFloor.addVehicle(vehicle2);
        assertEquals(60.0 - 3.0, currentFloor.getAvailableNumberOfSlots(), 0.1);

        // check available number of slots after a minibus is added
        Vehicle vehicle3 = new Minibus("LMO001");
        currentFloor.addVehicle(vehicle3);
        assertEquals(60.0 - 6.0, currentFloor.getAvailableNumberOfSlots(), 0.1);

        // check available number of slots after a mini lorry is added
        Vehicle vehicle4 = new MiniLorry("ML1002");
        currentFloor.addVehicle(vehicle4);
        assertEquals(60.0 - 9.0, currentFloor.getAvailableNumberOfSlots(), 0.1);

        // check available number of slots after a motorbike is added
        Vehicle vehicle5 = new Motorbike("MB9999");
        currentFloor.addVehicle(vehicle5);
        assertEquals(60.0 - (9.0 + (1.0 / 3.0)), currentFloor.getAvailableNumberOfSlots(), 0.1);
    }

    /**
     * Test class that checks whether a particular vehicle can be accommodated by this floor
     */
    @Test
    public void isParkingSlotsSufficient(){
        // Check when Capacity cannot be accommodated
        double MAX_FLOOR_CAPACITY = 2.0;

        ArrayList<VehicleTypes> groundPreferredVehicleTypes = new ArrayList<>(Arrays.asList(
                VehicleTypes.MINI_BUS, VehicleTypes.MINI_LORRY
        ));
        ArrayList<VehicleTypes> groundPossibleVehicleTypes = new ArrayList<>(Arrays.asList(
                VehicleTypes.CAR, VehicleTypes.MOTORBIKE, VehicleTypes.VAN, VehicleTypes.MINI_BUS, VehicleTypes.MINI_LORRY
        ));
        Floor floor = new Floor(groundPreferredVehicleTypes, groundPossibleVehicleTypes, MAX_FLOOR_CAPACITY);

        Vehicle vehicle = new Minibus("LMO001");
        assertFalse(floor.isParkingSlotsSufficient(vehicle));

        // This is a border case when it can be accommodated
        MAX_FLOOR_CAPACITY = 3.0;

        Floor floor2 = new Floor(groundPreferredVehicleTypes, groundPossibleVehicleTypes, MAX_FLOOR_CAPACITY);

        Vehicle vehicle1 = new Minibus("LMO001");
        assertTrue(floor2.isParkingSlotsSufficient(vehicle1));
    }


    /**
     * Test class that checks whether this floor correctly returns the vehicle by its plate ID
     */
    @Test
    public void getVehicleById(){

        // Get a vehicle by plate ID
        Vehicle vehicle1 = new Car("yp0101");
        currentFloor.addVehicle(vehicle1);

        Vehicle vehicle2 = new Van("kf2002");
        currentFloor.addVehicle(vehicle2);

        Vehicle vehicle3 = new Minibus("LMO001");
        currentFloor.addVehicle(vehicle3);

        Vehicle vehicle4 = new MiniLorry("ML1002");
        currentFloor.addVehicle(vehicle4);

        Vehicle vehicle5 = new Motorbike("MB9999");
        currentFloor.addVehicle(vehicle5);

        assertEquals(vehicle1, currentFloor.getVehicleById("yp0101"));
        assertEquals(vehicle2, currentFloor.getVehicleById("kf2002"));
        assertEquals(vehicle3, currentFloor.getVehicleById("LMO001"));
        assertEquals(vehicle4, currentFloor.getVehicleById("ML1002"));
        assertEquals(vehicle5, currentFloor.getVehicleById("MB9999"));

        // plateID's that don't exist
        assertNull(currentFloor.getVehicleById("lp0103"));
        assertNull(currentFloor.getVehicleById("rr0103"));

    }

    /**
     * Test class that checks whether this floor correctly deletes a particular vehicle by its instance
     *
     * @throws Exception when a vehicle cannot be added to the car park
     */
    @Test
    public void deleteVehicleByInstance() throws Exception{
        // Delete vehicle by object
        Vehicle vehicle1 = new Car("yp0101");
        currentFloor.addVehicle(vehicle1);

        Vehicle vehicle2 = new Van("kf2002");
        currentFloor.addVehicle(vehicle2);

        Vehicle vehicle3 = new Minibus("LMO001");
        currentFloor.addVehicle(vehicle3);

        Vehicle vehicle4 = new MiniLorry("ML1002");
        Vehicle vehicle5 = new Motorbike("MB9999");

        assertEquals(vehicle1, currentFloor.deleteVehicleByInstance(vehicle1));
        assertEquals(vehicle2, currentFloor.deleteVehicleByInstance(vehicle2));
        assertEquals(vehicle3, currentFloor.deleteVehicleByInstance(vehicle3));

        try{
            currentFloor.deleteVehicleByInstance(vehicle4);
            fail("Vehicle instance was returned, although it is not in this floor");
        } catch (Exception ignored){

        }

        try{
            currentFloor.deleteVehicleByInstance(vehicle5);
            fail("Vehicle instance was returned, although it is not in this floor");
        } catch (Exception ignored){

        }
    }

    /**
     * Test class that checks whether this floor correctly deletes a particular vehicle by its plate ID
     *
     * @throws Exception when a vehicle cannot be added to the car park
     */
    @Test
    public void deleteVehicleByPlateId() throws Exception{
        // Delete vehicle by plate ID
        Vehicle vehicle1 = new Car("yp0101");
        currentFloor.addVehicle(vehicle1);

        Vehicle vehicle2 = new Van("kf2002");
        currentFloor.addVehicle(vehicle2);

        Vehicle vehicle3 = new Minibus("LMO001");
        currentFloor.addVehicle(vehicle3);

        assertEquals(vehicle1, currentFloor.deleteVehicleByPlateId("yp0101"));
        assertEquals(vehicle2, currentFloor.deleteVehicleByPlateId("kf2002"));
        assertEquals(vehicle3, currentFloor.deleteVehicleByPlateId("LMO001"));

        try{
            currentFloor.deleteVehicleByPlateId("ML1002");
            fail("Vehicle instance was returned, although it is not in this floor");
        } catch (Exception ignored){

        }

        try{
            currentFloor.deleteVehicleByPlateId("MB9999");
            fail("Vehicle instance was returned, although it is not in this floor");
        } catch (Exception ignored){

        }
    }
}