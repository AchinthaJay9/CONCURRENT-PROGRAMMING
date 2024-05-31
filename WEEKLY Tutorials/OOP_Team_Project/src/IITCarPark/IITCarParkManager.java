package IITCarPark;

import CarPark.CarPark;
import CarPark.DatabaseHandler;
import CarPark.CarParkManager;
import CarPark.Floor;
import Vehicles.Vehicle;
import Vehicles.VehicleTypes;

import java.util.*;

/**
 * Class that implements the {@link CarParkManager} interface
 * The main functionality is to simulate the car park manager of the IIT car park.
 */
public class IITCarParkManager implements CarParkManager {


    private DatabaseHandler database;

    /**
     * Represents the car park managed in the {@link IITCarParkManager} class
     */
    private CarPark iitCarPark;
    /**
     * Represents the parking meter used  to calculate the cost of the vehicle parked in the {@link IITCarParkManager} class
     */
    private IITParkingMeter iitParkMeter;

    /**
     * Default constructor of the {@link IITCarParkManager} class
     * Initializes the floors to create the IIT car park.
     * Initializes the IIT parking meter
     */
    public IITCarParkManager(){
        // Initializes all floors of the IIT car park
        List<Floor> floors = this.initializeIITFloors();
        // Initializes the IIT car park
        this.iitCarPark = new CarPark(floors);
        // Initializes the IIT parking meter
        iitParkMeter = new IITParkingMeter();

        this.database = new DatabaseHandler(iitCarPark);
    }

    /**
     * Method is widely used when retrieving the stored data to load the previous state of the car park
     *
     * @param iitCarPark new car park to be managed
     */
    public void setIitCarPark(CarPark iitCarPark) {
        this.iitCarPark = iitCarPark;
    }

    /**
     * @return list of all floors used in this IIT car park
     */
    private List<Floor> initializeIITFloors(){
        // maximum floor capacity for each floor
        double MAX_FLOOR_CAPACITY = 60.0;
        // Arraylist containing all floors
        List<Floor> floors = Collections.synchronizedList(new ArrayList<>());


        // For each floor create two lists containing possible and preferred vehicle types
        // After that initialize from ground to sixth floor.

        // Create Ground Floor
        List<VehicleTypes> groundPreferredVehicleTypes = Collections.synchronizedList(Arrays.asList(
                VehicleTypes.MINI_BUS, VehicleTypes.MINI_LORRY
        ));
        List<VehicleTypes> groundPossibleVehicleTypes = Collections.synchronizedList(Arrays.asList(
                VehicleTypes.CAR, VehicleTypes.MOTORBIKE, VehicleTypes.VAN, VehicleTypes.MINI_BUS, VehicleTypes.MINI_LORRY
        ));
        Floor groundFloor = new Floor(groundPreferredVehicleTypes,groundPossibleVehicleTypes, MAX_FLOOR_CAPACITY);


        // Create First Floor
        List<VehicleTypes> firstPreferredVehicleTypes = Collections.synchronizedList(Arrays.asList(
                VehicleTypes.MOTORBIKE, VehicleTypes.VAN
        ));
        List<VehicleTypes> firstPossibleVehicleTypes = Collections.synchronizedList(Arrays.asList(
                VehicleTypes.CAR, VehicleTypes.MOTORBIKE, VehicleTypes.VAN
        ));
        Floor firstFloor = new Floor(firstPreferredVehicleTypes, firstPossibleVehicleTypes, MAX_FLOOR_CAPACITY);


       // Create Second Floor
        List<VehicleTypes> secondPreferredVehicleTypes = Collections.synchronizedList(Arrays.asList(
                VehicleTypes.MOTORBIKE, VehicleTypes.VAN
        ));
        List<VehicleTypes> secondPossibleVehicleTypes = Collections.synchronizedList(Arrays.asList(
                VehicleTypes.CAR, VehicleTypes.MOTORBIKE, VehicleTypes.VAN
        ));
        Floor secondFloor = new Floor(secondPreferredVehicleTypes, secondPossibleVehicleTypes, MAX_FLOOR_CAPACITY);

       // Create Third Floor
        List<VehicleTypes> thirdFPreferredVehicleTypes = Collections.synchronizedList(Collections.singletonList(
                VehicleTypes.CAR
        ));
        List<VehicleTypes> thirdFPossibleVehicleTypes = Collections.synchronizedList(Collections.singletonList(
                VehicleTypes.CAR
        ));
        Floor thirdFloor = new Floor(thirdFPreferredVehicleTypes,thirdFPossibleVehicleTypes, MAX_FLOOR_CAPACITY);

       // Create Fourth Floor
        List<VehicleTypes> fourthPreferredVehicleTypes = Collections.synchronizedList(Collections.singletonList(
                VehicleTypes.CAR
        ));
        List<VehicleTypes> fourthPossibleVehicleTypes = Collections.synchronizedList(Collections.singletonList(
                VehicleTypes.CAR
        ));
        Floor fourthFloor = new Floor(fourthPreferredVehicleTypes, fourthPossibleVehicleTypes, MAX_FLOOR_CAPACITY);

       // Create Fifth Floor
        List<VehicleTypes> fifthPreferredVehicleTypes = Collections.synchronizedList(Collections.singletonList(
                VehicleTypes.CAR
        ));
        List<VehicleTypes> fifthPossibleVehicleTypes = Collections.synchronizedList(Collections.singletonList(
                VehicleTypes.CAR
        ));
        Floor fifthFloor = new Floor(fifthPreferredVehicleTypes,fifthPossibleVehicleTypes, MAX_FLOOR_CAPACITY);

       // Create sixth Floor
        List<VehicleTypes> sixthPreferredVehicleTypes = Collections.synchronizedList(Collections.singletonList(
                VehicleTypes.CAR
        ));
        List<VehicleTypes> sixthPossibleVehicleTypes = Collections.synchronizedList(Collections.singletonList(
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

    public CarPark getIitCarPark() {
        return iitCarPark;
    }

    /**
     * Add vehicle to car park and print number of free slots in the car park
     *
     * @param vehicle instance of the vehicle to be added to the car park
     */
    @Override
    public void addVehicle(Vehicle vehicle) {
        try{
            // Add vehicle to car park
            this.iitCarPark.addVehicle(vehicle);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            // print number of free slots in the car park
            double slotsAvailable = this.iitCarPark.getFreeSlots();
            System.out.println("Vehicle added successfully! There are " + String.format("%.2f", slotsAvailable) + " slots left in the car park");
        }
    }

    /**
     * Delete vehicle of a given plateID and print details of the deleted vehicle
     *
     * @param plateID plateID of the vehicle to be deleted
     */
    @Override
    public void deleteVehicle(String plateID) {
        try{
            // Delete vehicle with given plate ID
            Vehicle deletedVehicle = this.iitCarPark.deleteVehicle(plateID);
            // Print details of the deleted vehicle
            String vehicleType = deletedVehicle.getVehicleType().getValue();

            System.out.println(vehicleType+" with plate id - "+plateID+" was deleted.");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Print details of vehicles parked in the car park in chronological order
     */
    @Override
    public void printVehiclesParked() {
        // Get the list of vehicles in the car park in chronological order
        List<Vehicle> vehicleList = this.iitCarPark.getVehicleList();
        // Iterate over the vehicle list and print details of each vehicle
        for(Vehicle vehicle: vehicleList){
            String vehicleType = vehicle.getVehicleType().getValue();
            String plateID = vehicle.getPlateID();
            String entryTime = vehicle.getEntryTime().getDateTime();

            System.out.println(vehicleType +" with plate id - "+plateID+" entered at: "+entryTime);
        }
    }

    /**
     * Print the percentages of each vehicle type parked within the car park
     */
    @Override
    public void printVehiclesPercentages() {
        // Get the vehicle statistics in a hashmap
        HashMap<String, Double> percentagesMap = this.iitCarPark.getVehiclePercentages();

        System.out.println("The Percentages of Vehicles parked");
        System.out.println("==================================");

        // Print percentage for each vehicle type
        for(String vehicleType: percentagesMap.keySet()){
            String percentage = String.format("%.2f", percentagesMap.get(vehicleType));
            System.out.println(vehicleType+" - "+ percentage  + "%");
        }
    }

    /**
     * Print details of the oldest vehicle that has entered the car park
     */
    @Override
    public void printOldestVehicle() {
        try{
            // Get the oldest vehicle
            Vehicle oldestVehicle = this.iitCarPark.getOldestVehicle();
            // Print details of the oldest vehicle
            String vehicleType = oldestVehicle.getVehicleType().getValue();
            String plateID = oldestVehicle.getPlateID();
            String entryTime = oldestVehicle.getEntryTime().getDateTime();

            System.out.println("The oldest vehicle is a "+vehicleType+" with plate ID - "+plateID+ " entered at: "+ entryTime);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Print details of the latest vehicle that has entered the car park
     */
    @Override
    public void printLatestVehicle() {
        try {
            // Get the latest vehicle
            Vehicle latestVehicle = this.iitCarPark.getLatestVehicle();
            // Print details of the latest vehicle
            String vehicleType = latestVehicle.getVehicleType().getValue();
            String plateID = latestVehicle.getPlateID();
            String entryTime = latestVehicle.getEntryTime().getDateTime();

            System.out.println("The latest vehicle is a " + vehicleType + " with plate ID - " + plateID + " entered at: " + entryTime);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Print receipt of the vehicle with the given PlateID
     *
     * @param plateID plateID of the vehicle
     */
    @Override
    public void printReceipt(String plateID) {
        try{
            Vehicle vehicle = this.iitCarPark.getVehicleById(plateID);
            String vehicleType = vehicle.getVehicleType().getValue();
            String amount = String.format("%.2f", this.iitParkMeter.getParkingCharge(vehicle));

            System.out.println(vehicleType+ " with plate id - " + plateID + " has a parking charge of Rs."+ amount );
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Print details of all vehicles entered at a particular day and year
     *
     * @param day day the vehicle has entered the car park
     * @param year year the vehicle has entered the car park
     */
    @Override
    public void printVehiclesByDate(String day,String year) {
        try{
            List<Vehicle> vehicleList = this.iitCarPark.getVehicleByDayYear(day,year);
            for(Vehicle vehicle: vehicleList){
                String vehicleType = vehicle.getVehicleType().getValue();
                String plateID = vehicle.getPlateID();
                String entryTime = vehicle.getEntryTime().getDateTime();

                System.out.println(vehicleType +" with plate id - "+plateID+" entered on: "+ entryTime);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Display parking charges of all vehicles in the car park
     */
    @Override
    public void displayParkingCharges() {
        List<Vehicle> vehicleList = this.iitCarPark.getVehicleList();
        for(Vehicle vehicle: vehicleList){
            String plateID = vehicle.getPlateID();
            this.printReceipt(plateID);
        }
    }

    /**
     * Display the entire car park in a floor by floor hierarchy
     */
    public void displayEntireCarPark(){
        // Get all floors in a car park
        List<Floor> floorList = this.iitCarPark.getFloorList();
        // initialize the floor count
        int floorCount = 0;
        System.out.println("=========================================================");
        System.out.println("====================IIT Car Park=========================");
        for(Floor floor: floorList){
            // Print floor level
            System.out.println("=========================================================");
            System.out.println("======================== Floor "+floorCount+" ========================");
            System.out.println("=========================================================");
            floorCount+=1;
            // Print Vehicles in current Floor
            for(Vehicle vehicle: floor.getVehicleList()){
                System.out.println(vehicle.getVehicleType().getValue() +" with plate id - "+vehicle.getPlateID());
            }
            if(floor.getVehicleList().size() == 0){
                System.out.println("====================    Empty    ====================");
            }
        }
    }


    /**
     * Method to save the car park data to database
     */
   @Override
    public void saveData(){
       database.writeData("CarPark.ser");
   }

    /**
     * Method to retrieve the car park data from the database and update the current car park
     */
   @Override
    public void retrieveData(){
      CarPark carPark = database.readData("CarPark.ser");
      this.setIitCarPark(carPark);
   }

}
