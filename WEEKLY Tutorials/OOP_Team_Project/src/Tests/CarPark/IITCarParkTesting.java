package CarPark;

import Vehicles.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Junit4 Testing class that test the vehicles are added to the correct floor of the {@link CarPark} class.
 */
public class IITCarParkTesting {

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
        this.floors = this.initializeIITFloors();
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
     * @return list of all floors used in this IIT car park
     */
    private ArrayList<Floor> initializeIITFloors(){
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
        ArrayList<VehicleTypes> secondPreferredVehicleTypes = new ArrayList<>(Arrays.asList(
                VehicleTypes.MOTORBIKE, VehicleTypes.VAN
        ));
        ArrayList<VehicleTypes> secondPossibleVehicleTypes = new ArrayList<>(Arrays.asList(
                VehicleTypes.CAR, VehicleTypes.MOTORBIKE, VehicleTypes.VAN
        ));
        Floor secondFloor = new Floor(secondPreferredVehicleTypes, secondPossibleVehicleTypes, MAX_FLOOR_CAPACITY);

        // Create Third Floor
        ArrayList<VehicleTypes> thirdFPreferredVehicleTypes = new ArrayList<>(Collections.singletonList(
                VehicleTypes.CAR
        ));
        ArrayList<VehicleTypes> thirdFPossibleVehicleTypes = new ArrayList<>(Collections.singletonList(
                VehicleTypes.CAR
        ));
        Floor thirdFloor = new Floor(thirdFPreferredVehicleTypes,thirdFPossibleVehicleTypes, MAX_FLOOR_CAPACITY);

        // Create Fourth Floor
        ArrayList<VehicleTypes> fourthPreferredVehicleTypes = new ArrayList<>(Collections.singletonList(
                VehicleTypes.CAR
        ));
        ArrayList<VehicleTypes> fourthPossibleVehicleTypes = new ArrayList<>(Collections.singletonList(
                VehicleTypes.CAR
        ));
        Floor fourthFloor = new Floor(fourthPreferredVehicleTypes, fourthPossibleVehicleTypes, MAX_FLOOR_CAPACITY);

        // Create Fifth Floor
        ArrayList<VehicleTypes> fifthPreferredVehicleTypes = new ArrayList<>(Collections.singletonList(
                VehicleTypes.CAR
        ));
        ArrayList<VehicleTypes> fifthPossibleVehicleTypes = new ArrayList<>(Collections.singletonList(
                VehicleTypes.CAR
        ));
        Floor fifthFloor = new Floor(fifthPreferredVehicleTypes,fifthPossibleVehicleTypes, MAX_FLOOR_CAPACITY);

        // Create sixth Floor
        ArrayList<VehicleTypes> sixthPreferredVehicleTypes = new ArrayList<>(Collections.singletonList(
                VehicleTypes.CAR
        ));
        ArrayList<VehicleTypes> sixthPossibleVehicleTypes = new ArrayList<>(Collections.singletonList(
                VehicleTypes.CAR
        ));
        Floor sixthFloor = new Floor(sixthPreferredVehicleTypes,sixthPossibleVehicleTypes, MAX_FLOOR_CAPACITY);

        // Add each individual floor to Arraylist containing all floors.
        floors.add(groundFloor);
        floors.add(firstFloor);
        floors.add(secondFloor);
        floors.add(thirdFloor);
        floors.add(fourthFloor);
        floors.add(fifthFloor);
        floors.add(sixthFloor);

        return floors;
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
        Floor thirdFloor = carPark.getFloorList().get(3);




        // check if car was added to the 3rd floor

        carPark.addVehicle(vehicle1);
        assertEquals(vehicle1, thirdFloor.getVehicleById("yp0101"));

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


        // Add cars until third, fourth, fifth and sixth and see if added to the second floor
        
        Vehicle tempVehicle;
        String randomPlateID;
        for(int i=0; i<= 238; i++){
            randomPlateID = getRandomPlateID(6);
            tempVehicle = new Car(randomPlateID);
            carPark.addVehicle(tempVehicle);
        }

        randomPlateID = getRandomPlateID(6);
        tempVehicle = new Car(randomPlateID);
        carPark.addVehicle(tempVehicle);
        assertEquals(tempVehicle, secondFloor.getVehicleById(randomPlateID));


    }
}
