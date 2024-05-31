package CarPark;

import DateTime.DateTime;
import Vehicles.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Junit4 Testing class that test the main functionality of the {@link CarPark} class.
 */
public class CarParkTest{

    /**
     * Car park component of the {@link CarParkTest} class
     */
    private CarPark carPark;
    /**
     * List of the floors in the car park of the {@link CarParkTest} class
     */
    private ArrayList<Floor> floors;

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
     * Method run before each test case to initialize this car park, floors and each vehicle object
     */
    @Before
    public void init(){
        // Initializes all floors of the car park
        this.floors = this.initializeFloors();
        // Initializes the car park
        this.carPark = new CarPark(floors);

        // initialize vehicles
        this.vehicle1 = new Car("yp0101");
        this.vehicle2 = new Van("kf2002");
        this.vehicle3 = new Minibus("LMO001");
        this.vehicle4 = new MiniLorry("ML1002");
        this.vehicle5 = new Motorbike("MB9999");
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
     * Method checks if two lists have the same set of elements irrespective of the element order
     *
     * @param list1 First list object to compare
     * @param list2 Second list object to compare
     * @param <T> Type of object stored in each list
     * @return True if the list have the same element irrespective of the order
     */
    public static <T> boolean isListEqual(List<T> list1, List<T> list2){
        // check if array lists are equal irrespective of the order
        return new HashSet<>(list1).equals(new HashSet<>(list2));
    }

    /**
     * Randomly generate a vehicle plate ID of a specific length
     *
     * @param len length of plate ID
     * @return string of generated plate ID
     */
    public static String getRandomPlateID(int len){
        String VOCAB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder plateID = new StringBuilder();
        Random rnd = new Random();
        while (plateID.length() < len){ // length of the random string.
            int index = (int) (rnd.nextFloat() * VOCAB.length());
            plateID.append(VOCAB.charAt(index));
        }
        return plateID.toString();
    }

    /**
     * Test method that checks whether the car park returns the correct floor list
     */
    @Test
    public void getFloorList(){
        // check if floor list is returned properly
        assertTrue(isListEqual(floors, carPark.getFloorList()));
    }

    /**
     * Test method that checks the list of vehicles is properly maintained when vehicles are added
     *
     * @throws Exception when a vehicle cannot be added to the car park
     */
    @Test
    public void getVehicleList() throws Exception{
        ArrayList<Vehicle> vehicleList = new ArrayList<>();

        // check whether the vehicle list is maintained properly

        carPark.addVehicle(vehicle1);
        vehicleList.add(vehicle1);

        carPark.addVehicle(vehicle2);
        vehicleList.add(vehicle2);

        carPark.addVehicle(vehicle3);
        vehicleList.add(vehicle3);

        carPark.addVehicle(vehicle4);
        vehicleList.add(vehicle4);

        carPark.addVehicle(vehicle5);
        vehicleList.add(vehicle5);

        // check if vehicle list is properly maintained
        assertTrue(isListEqual(vehicleList, carPark.getVehicleList()));
    }


    /**
     * Test method that checks whether the vehicle is correct added to the appropriate floor in this car park
     * It also checks whether the system correctly rejects new vehicles when the possible floors to park is full.
     *
     * @throws Exception when a vehicle cannot be added to the car park
     */
    @Test
    public void addVehicle() throws Exception{

        Floor groundFloor = carPark.getFloorList().get(0);
        Floor firstFloor = carPark.getFloorList().get(1);
        Floor secondFloor = carPark.getFloorList().get(2);

        // check if car was added to the 2nd floor

        carPark.addVehicle(vehicle1);
        assertEquals(vehicle1, secondFloor.getVehicleById("yp0101"));

        // check if van was added to the 1st floor

        carPark.addVehicle(vehicle2);
        assertEquals(vehicle2, firstFloor.getVehicleById("kf2002"));

        // check if minibus was added to the ground floor

        carPark.addVehicle(vehicle3);
        assertEquals(vehicle3, groundFloor.getVehicleById("LMO001"));

        // check if mini lorry was added to the ground floor

        carPark.addVehicle(vehicle4);
        assertEquals(vehicle4, groundFloor.getVehicleById("ML1002"));

        // check if motorbike was added to the 1st floor

        carPark.addVehicle(vehicle5);
        assertEquals(vehicle5, firstFloor.getVehicleById("MB9999"));


        // Add cars until third floor is full and see if it is added to the first floor
        Vehicle tempVehicle;
        String randomPlateID;
        for(int i=0; i<= 58; i++){
            randomPlateID = getRandomPlateID(6);
            tempVehicle = new Car(randomPlateID);
            carPark.addVehicle(tempVehicle);
            assertEquals(tempVehicle, secondFloor.getVehicleById(randomPlateID));
        }

        randomPlateID = getRandomPlateID(6);
        tempVehicle = new Car(randomPlateID);
        carPark.addVehicle(tempVehicle);
        assertEquals(tempVehicle, firstFloor.getVehicleById(randomPlateID));

        // fill the ground floor and check whether the mini lorry/minibus is rejected from the car park when added
        for(int i=0; i<= 17; i++){
            randomPlateID = getRandomPlateID(6);
            tempVehicle = new Minibus(randomPlateID);
            carPark.addVehicle(tempVehicle);
            assertEquals(tempVehicle, groundFloor.getVehicleById(randomPlateID));
        }

        try{
            randomPlateID = getRandomPlateID(6);
            tempVehicle = new Minibus(randomPlateID);
            carPark.addVehicle(tempVehicle);
            fail("Minibus cannot be added, since possible floors are full.");
        }catch (Exception ignored){

        }

        try{
            randomPlateID = getRandomPlateID(6);
            tempVehicle = new MiniLorry(randomPlateID);
            carPark.addVehicle(tempVehicle);
            fail("MiniLorry cannot be added, since possible floors are full.");
        }catch (Exception ignored){

        }

    }

    /**
     * Test method that checks whether the number of free slots are correctly updated
     *
     * @throws Exception when a vehicle cannot be added to the car park
     */
    @Test
    public void getFreeSlots() throws Exception{

        // check whether the number of free slots are returned correctly

        carPark.addVehicle(vehicle1);

        carPark.addVehicle(vehicle2);

        carPark.addVehicle(vehicle3);

        carPark.addVehicle(vehicle4);

        carPark.addVehicle(vehicle5);

        assertEquals(170.666, carPark.getFreeSlots(), 0.1);
    }

    /**
     * Test method to check whether the vehicle is correctly deleted from the car park
     *
     * @throws Exception when a vehicle cannot be added to the car park
     */
    @Test
    public void deleteVehicle() throws Exception{
        // check whether the vehicles are deleted and returned properly

        carPark.addVehicle(vehicle1);

        carPark.addVehicle(vehicle2);

        carPark.addVehicle(vehicle3);

        carPark.addVehicle(vehicle4);

        carPark.addVehicle(vehicle5);

        assertEquals(vehicle1, carPark.deleteVehicle("yp0101"));
        assertEquals(vehicle2, carPark.deleteVehicle("kf2002"));
        assertEquals(vehicle3, carPark.deleteVehicle("LMO001"));
        assertEquals(vehicle4, carPark.deleteVehicle("ML1002"));
        assertEquals(vehicle5, carPark.deleteVehicle("MB9999"));
        assertEquals(180, carPark.getFreeSlots(), 0.1);
    }

    /**
     * Test method that checks whether the correct vehicle percentages are given by this car park
     *
     * @throws Exception when a vehicle cannot be added to the car park
     */
    @Test
    public void getVehiclePercentages() throws Exception{
       // check whether the percentages of each vehicle type is returned correctly

        carPark.addVehicle(vehicle1);

        carPark.addVehicle(vehicle2);

        carPark.addVehicle(vehicle3);

        carPark.addVehicle(vehicle4);

        carPark.addVehicle(vehicle5);

        HashMap<String, Double> vehiclePercentages = new HashMap<String, Double>(){{
                put("Motorbike", 20.0);
                put("Car", 20.0);
                put("Van", 20.0);
                put("Mini Lorry", 20.0);
                put("Mini Bus", 20.0);
            }};

        assertEquals(vehiclePercentages, carPark.getVehiclePercentages());

        // add 9 cars
        Vehicle tempVehicle;
        String randomPlateID;
        for(int i=0; i<= 8; i++){
            randomPlateID = getRandomPlateID(6);
            tempVehicle = new Car(randomPlateID);
            carPark.addVehicle(tempVehicle);
        }

        // add 9 lorries
        for(int i=0; i<= 8; i++){
            randomPlateID = getRandomPlateID(6);
            tempVehicle = new MiniLorry(randomPlateID);
            carPark.addVehicle(tempVehicle);
        }

            vehiclePercentages = new HashMap<String, Double>(){{
                put("Motorbike", (1.0/23.0)*100);
                put("Car", (10.0/23.0)*100);
                put("Van", (1.0/23.0)*100);
                put("Mini Lorry", (10.0/23.0)*100);
                put("Mini Bus", (1/23.0)*100);
            }};

        assertEquals(vehiclePercentages, carPark.getVehiclePercentages());

    }

    /**
     * Test method that checks whether this car park returns the correct vehicle as the oldest
     *
     * @throws Exception when a vehicle cannot be added to the car park
     */
    @Test
    public void getOldestVehicle() throws Exception{
        // Check whether the oldest vehicle is returned by car park
        DateTime date1 = new DateTime(12, 7, 2021, 17, 50, 20);
        DateTime date2 = new DateTime(12, 7, 2021, 8, 50, 20);
        DateTime date3 = new DateTime(29, 2, 2020, 1, 50, 20);
        DateTime date4 = new DateTime(12, 2, 2020, 17, 50, 20);
        DateTime date5 = new DateTime(31, 12, 2020, 0, 0, 1);

        vehicle1.setEntryTime(date1);
        vehicle2.setEntryTime(date2);
        vehicle3.setEntryTime(date3);
        vehicle4.setEntryTime(date4);
        vehicle5.setEntryTime(date5);

        carPark.addVehicle(vehicle1);
        carPark.addVehicle(vehicle2);
        carPark.addVehicle(vehicle3);
        carPark.addVehicle(vehicle4);
        carPark.addVehicle(vehicle5);

        assertEquals(vehicle4, carPark.getOldestVehicle());
        carPark.deleteVehicle(vehicle4.getPlateID());
        assertEquals(vehicle3, carPark.getOldestVehicle());
    }

    /**
     * Test method that checks whether this car park returns the correct vehicle as the oldest
     *
     * @throws Exception when a vehicle cannot be added to the car park
     */
    @Test
    public void getLatestVehicle() throws Exception{
        // Check whether the latest vehicle is returned by car park
        DateTime date1 = new DateTime(12, 7, 2021, 17, 50, 20);
        DateTime date2 = new DateTime(12, 7, 2021, 8, 50, 20);
        DateTime date3 = new DateTime(29, 2, 2020, 1, 50, 20);
        DateTime date4 = new DateTime(12, 2, 2020, 17, 50, 20);
        DateTime date5 = new DateTime(31, 12, 2020, 0, 0, 1);


        vehicle1.setEntryTime(date1);
        vehicle2.setEntryTime(date2);
        vehicle3.setEntryTime(date3);
        vehicle4.setEntryTime(date4);
        vehicle5.setEntryTime(date5);

        carPark.addVehicle(vehicle1);
        carPark.addVehicle(vehicle2);
        carPark.addVehicle(vehicle3);
        carPark.addVehicle(vehicle4);
        carPark.addVehicle(vehicle5);

        assertEquals(vehicle1, carPark.getLatestVehicle());
        carPark.deleteVehicle(vehicle1.getPlateID());
        assertEquals(vehicle2, carPark.getLatestVehicle());
    }

    /**
     * Test method that checks whether the correct vehicle is returned when a plate ID is provided
     *
     * @throws Exception when a vehicle cannot be added to the car park
     */
    @Test
    public void getVehicleById() throws Exception{
        // Check if the car park returns vehicle according to the plate ID
        carPark.addVehicle(vehicle1);
        carPark.addVehicle(vehicle2);
        carPark.addVehicle(vehicle3);
        carPark.addVehicle(vehicle4);
        carPark.addVehicle(vehicle5);

        assertEquals(vehicle1, carPark.getVehicleById("yp0101"));
        assertEquals(vehicle2, carPark.getVehicleById("kf2002"));
        assertEquals(vehicle3, carPark.getVehicleById("LMO001"));
        assertEquals(vehicle4, carPark.getVehicleById("ML1002"));
        assertEquals(vehicle5, carPark.getVehicleById("MB9999"));

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
     * Test method that checks whether the car park returns the correct list of vehicles according to the date of entry
     *
     * @throws Exception when a vehicle cannot be added to the car park
     */
    @Test
    public void getVehicleByDayYear() throws Exception{
        // Check if the car park returns vehicles according to entered date

        DateTime date1 = new DateTime(12, 7, 2021, 17, 50, 20);
        DateTime date2 = new DateTime(12, 7, 2021, 8, 50, 20);
        DateTime date3 = new DateTime(29, 2, 2020, 1, 50, 20);
        DateTime date4 = new DateTime(12, 2, 2020, 17, 50, 20);
        DateTime date5 = new DateTime(31, 12, 2020, 0, 0, 1);


        vehicle1.setEntryTime(date1);
        vehicle2.setEntryTime(date2);
        vehicle3.setEntryTime(date3);
        vehicle4.setEntryTime(date4);
        vehicle5.setEntryTime(date5);

        carPark.addVehicle(vehicle1);
        carPark.addVehicle(vehicle2);
        carPark.addVehicle(vehicle3);
        carPark.addVehicle(vehicle4);
        carPark.addVehicle(vehicle5);

        ArrayList<Vehicle> vehiclesOnDayYear = new ArrayList<>(Arrays.asList(
                vehicle1, vehicle2
        ));
        assertEquals(vehiclesOnDayYear, carPark.getVehicleByDayYear("12", "2021"));

        vehiclesOnDayYear = new ArrayList<>(Collections.singletonList(
                vehicle5
        ));

        assertEquals(vehiclesOnDayYear, carPark.getVehicleByDayYear("31", "2020"));

        try{
            carPark.getVehicleByDayYear("31", "1999");
            fail("Error must be thrown because vehicle on that entry conditions is not found");
        }catch (Exception ignored){

        }
    }
}