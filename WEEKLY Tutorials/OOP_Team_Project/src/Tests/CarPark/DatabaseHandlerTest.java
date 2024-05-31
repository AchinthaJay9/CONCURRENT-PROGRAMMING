package CarPark;

import DateTime.*;
import Vehicles.*;
import org.junit.Before;
import org.junit.Test;


import java.util.*;

import static org.junit.Assert.*;

/**
 * Junit4 Testing class that test the main functionality of the {@link DatabaseHandler} class.
 */
public class DatabaseHandlerTest {

    /**
     * Car park component of the {@link CarParkTest} class
     */
    private CarPark carPark;
    private CarPark carPark1;
    /**
     * List of the floors in the car park of the {@link CarParkTest} class
     */
    private ArrayList<Floor> floors;
    private ArrayList<Floor> floors1;

    /**
     *  Individual vehicle object used for testing purposes in the {@link CarParkTest} class
     */
    private Vehicle vehicle1;
    /**
     * Individual vehicle object used for testing purposes in the {@link CarParkTest} class
     */
    private Vehicle vehicle2;
    /**
     * Individual vehicle object used for testing purposes in the {@link CarParkTest} class
     */
    private Vehicle vehicle3;
    /**
     * Individual vehicle object used for testing purposes in the {@link CarParkTest} class
     */
    private Vehicle vehicle4;
    /**
     * Individual vehicle object used for testing purposes in the {@link CarParkTest} class
     */
    private Vehicle vehicle5;
    /**
     *  Individual vehicle object used for testing purposes in the {@link CarParkTest} class
     *
     * Database Handler component of the {@link DatabaseHandlerTest} class
     */
    private DatabaseHandler testDB;
    /**
     /**
     * Method run before each test case to initialize this car park, floors and each vehicle object
     */

    /**
     * Method run before each test case to initialize this car park, floors and each vehicle object
     */
    @Before
    public void init(){
        // Initializes all floors of the car park
        this.floors = this.initializeFloors();
        this.floors1 = this.initializeFloors();
        // Initializes the car park
        this.carPark = new CarPark(floors);
        this.carPark1 = new CarPark(floors1);

        // initialize vehicles
        this.vehicle1 = new Car("yp0101");
        this.vehicle2 = new Van("kf2002");
        this.vehicle3 = new Minibus("LMO001");
        this.vehicle4 = new MiniLorry("ML1002");
        this.vehicle5 = new Motorbike("MB9999");


        //Initialize Database
        this.testDB = new DatabaseHandler(this.carPark);
    }

    /**
     * Method initializes each floor level in this car park object that will be created
     *
     * @return list of floors that are part of this car park
     */
    private ArrayList<Floor> initializeFloors(){
        // maximum floor capacity for each floor
        double MAX_FLOOR_CAPACITY = 60.0;
        // Arraylist containing all floors
        ArrayList<Floor> floors = new ArrayList<>();


        // For each floor create two lists containing possible and preferred vehicle types
        // After that initialize from ground to sixth floor.

        // Create Ground Floor
        ArrayList<VehicleTypes> groundPreferredVehicleTypes = new ArrayList<>(Arrays.asList(
                VehicleTypes.MINI_BUS, VehicleTypes.MINI_LORRY
        ));
        ArrayList<VehicleTypes> groundPossibleVehicleTypes = new ArrayList<>(Arrays.asList(
                VehicleTypes.CAR, VehicleTypes.MOTORBIKE, VehicleTypes.VAN, VehicleTypes.MINI_BUS, VehicleTypes.MINI_LORRY
        ));
        Floor groundFloor = new Floor(groundPreferredVehicleTypes,groundPossibleVehicleTypes, MAX_FLOOR_CAPACITY);


        // Create First Floor
        ArrayList<VehicleTypes> firstPreferredVehicleTypes = new ArrayList<>(Arrays.asList(
                VehicleTypes.MOTORBIKE, VehicleTypes.VAN
        ));
        ArrayList<VehicleTypes> firstPossibleVehicleTypes = new ArrayList<>(Arrays.asList(
                VehicleTypes.CAR, VehicleTypes.MOTORBIKE, VehicleTypes.VAN
        ));
        Floor firstFloor = new Floor(firstPreferredVehicleTypes, firstPossibleVehicleTypes, MAX_FLOOR_CAPACITY);


        // Create Second Floor
        ArrayList<VehicleTypes> secondPreferredVehicleTypes = new ArrayList<>(Collections.singletonList(
                VehicleTypes.CAR
        ));
        ArrayList<VehicleTypes> secondPossibleVehicleTypes = new ArrayList<>(Arrays.asList(
                VehicleTypes.CAR, VehicleTypes.MOTORBIKE, VehicleTypes.VAN
        ));
        Floor secondFloor = new Floor(secondPreferredVehicleTypes, secondPossibleVehicleTypes, MAX_FLOOR_CAPACITY);


        // Add each individual floor to Arraylist containing all floors.
        floors.add(groundFloor);
        floors.add(firstFloor);
        floors.add(secondFloor);

        return floors;
    }
    /**
     * Test method that checks whether the Deserialization is correct
     *
     * @throws Exception if Object before serialization and object after deserialization equal
     */
    @Test
    public void TestNullDeSer() throws Exception{
        //Here we will be sending and empty car park object to check whether serialized and deserialized objects are same

        testDB.writeData("CarParkTest.Ser");

        CarPark carParkTest = new CarPark();

        carParkTest = testDB.readData("CarParkTest.Ser");

        assertEquals(this.carPark.getVehicleList(), carParkTest.getVehicleList());

    }

    /**
     * Test method that checks whether the Serialization is consistent for Car Parks with vehicles
     *
     * @throws Exception if serialization is inconsistent
     */
    @Test
    public void TestFullDeSer() throws Exception{

        //Second DB Instance
        this.carPark.addVehicle(vehicle1);
        this.carPark.addVehicle(vehicle2);
        this.carPark.addVehicle(vehicle3);
        this.carPark.addVehicle(vehicle4);;
        this.carPark.addVehicle(vehicle5);

        testDB.writeData("CarParkTest.Ser");

        CarPark carParkTest = testDB.readData("CarParkTest.Ser");

        assertEquals(this.carPark.getVehicleList(), carParkTest.getVehicleList());

    }


    /**
     * Test method that checks whether the correct vehicle is returned when a plate ID is provided after deserialization
     *
     * @throws Exception when a vehicle cannot be added to the car park
     */
    @Test
    public void getVehicleByIdDeSer() throws Exception{
        // Check if the car park returns vehicle according to the plate ID
        carPark.addVehicle(vehicle1);
        carPark.addVehicle(vehicle2);
        carPark.addVehicle(vehicle3);
        carPark.addVehicle(vehicle4);
        carPark.addVehicle(vehicle5);

        testDB.writeData("CarParkTest.Ser");

        CarPark carParkTest = testDB.readData("CarParkTest.Ser");

        assertEquals(vehicle1, carParkTest.getVehicleById("yp0101"));
        assertEquals(vehicle2, carParkTest.getVehicleById("kf2002"));
        assertEquals(vehicle3, carParkTest.getVehicleById("LMO001"));
        assertEquals(vehicle4, carParkTest.getVehicleById("ML1002"));
        assertEquals(vehicle5, carParkTest.getVehicleById("MB9999"));

        // plateID's that don't exist
        try{
            carPark.getVehicleById("lp0103");
            fail("Error must be thrown because vehicle with that specific plate ID doesnt exist");
        }catch (Exception ignored){

        }
        try{
            carPark.getVehicleById("rr0103");
            fail("Error must be thrown because vehicle with that specific plate ID doesnt exist");
        }catch (Exception ignored){

        }
    }


    /**
     * Test method that checks whether the vehicle is in appropriate floor in the car park after deserialization
     *
     *
     * @throws Exception when a vehicle not in correct floor
     */
    @Test
    public void checkFloorVehicleDeSer() throws Exception{

        Floor groundFloor = carPark.getFloorList().get(0);
        Floor firstFloor = carPark.getFloorList().get(1);
        Floor secondFloor = carPark.getFloorList().get(2);

        carPark.addVehicle(vehicle1);
        carPark.addVehicle(vehicle2);
        carPark.addVehicle(vehicle3);
        carPark.addVehicle(vehicle4);
        carPark.addVehicle(vehicle5);

        testDB.writeData("CarParkTest.Ser");

        CarPark carParkTest = testDB.readData("CarParkTest.Ser");


        // check if car was added to the 2nd floor
        assertEquals(vehicle1, secondFloor.getVehicleById("yp0101"));

        // check if van was added to the 1st floor
        assertEquals(vehicle2, firstFloor.getVehicleById("kf2002"));

        // check if minibus was added to the ground floor
        assertEquals(vehicle3, groundFloor.getVehicleById("LMO001"));

        // check if mini lorry was added to the ground floor
        assertEquals(vehicle4, groundFloor.getVehicleById("ML1002"));

        // check if motorbike was added to the 1st floor
        assertEquals(vehicle5, firstFloor.getVehicleById("MB9999"));


    }
}
