package CarPark;

import DateTime.DateTime;
import Vehicles.Vehicle;
import Vehicles.VehicleTypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.HashMap;

/**
 * Class that represents an entire car park ecosystem
 */
public class CarPark implements Serializable {
    /**
     * Represents each floor level in the {@link CarPark} class
     */
    private List<Floor> floorList;
    /**
     * A Thread safe concurrent List containing all the vehicles that are currently in the {@link CarPark} class
     */
    private List<Vehicle> vehicleList;

    /**
     * Primary constructor of the {@link CarPark} class
     *
     * @param floorList list of floor levels in the {@link CarPark} class
     */
    public CarPark(List<Floor> floorList ) {
        // set the floor levels
        this.floorList = floorList;
        // initialize the vehicle list
        this.vehicleList = Collections.synchronizedList(new ArrayList<>());
    }

    /**
     * Alternative constructor of the {@link CarPark} class
     *
     * @param floorList list of floor levels in the {@link CarPark} class
     * @param vehicleList existing list of vehicles in the {@link CarPark} class
     */
    public CarPark(List<Floor> floorList, List<Vehicle> vehicleList ) {
        // set the floor levels
        this.floorList = floorList;
        // set the vehicle list
        this.vehicleList = vehicleList;
    }

    public CarPark(){

    }

    /**
     * @return list of floors in the car park
     */
    public List<Floor> getFloorList() {
        return this.floorList;
    }

    /**
     * @return list of current vehicles ordered chronologically on the entered time
     */
    public List<Vehicle> getVehicleList() {
        // Recreate a new vehicle list and return the sorted version of it
        List<Vehicle> orderedVehicleList = Collections.synchronizedList(this.vehicleList);;
        Collections.sort(orderedVehicleList);
        return orderedVehicleList;
    }

    /**
     * Method helps select the most suitable floor for each vehicle depending on possibility and preference for each floor
     *
     * @param vehicle is the vehicle that is going to park
     * @return floor which is most suitable for the vehicle to park
     * @throws Exception when no suitable floor is found
     */
    private Floor getMostSuitableFloorForVehicle(Vehicle vehicle) throws Exception {
        // Initialize array list to store the preferred for the vehicle
        List<Floor> preferredFloors = Collections.synchronizedList(new ArrayList<>());
        // Initialize array list to store other possible for the vehicle
        List<Floor> possibleFloors = Collections.synchronizedList(new ArrayList<>());

        // For each floor get preferred and possible vehicle type
        // Add floor to array list depending on vehicle type and availability of the floor
        for(Floor currFloor : this.floorList){
            List<VehicleTypes> currFloorPreferredVehicleTypes = currFloor.getPreferredVehicleType();
            List<VehicleTypes> currFloorPossibleVehicleTypes = currFloor.getPossibleVehicleType();

            VehicleTypes currVehicleType = vehicle.getVehicleType();

            // Check if the floor can accommodate the current vehicle and whether it is preferred or possible
            if(currFloorPreferredVehicleTypes.contains(currVehicleType) && currFloor.isParkingSlotsSufficient(vehicle)){
                preferredFloors.add(currFloor);
            }else if(currFloorPossibleVehicleTypes.contains(currVehicleType) && currFloor.isParkingSlotsSufficient(vehicle)){
                possibleFloors.add(currFloor);
            }
        }

        // Check if there are any preferred floors available
        // If so return floor with most parking slots
        if(preferredFloors.size() > 0){
            Collections.sort(preferredFloors, Collections.reverseOrder());
            return preferredFloors.get(0);
        }else if (possibleFloors.size() > 0){
            // Check if there are any other possible floors available
            // If so return floor with the least parking slots
            Collections.sort(possibleFloors);
            return possibleFloors.get(0);
        }else {
            // No spot is available to park the vehicle
            throw new Exception("No suitable Floor Found to park - "+ vehicle.getVehicleType().getValue());
        }

    }

    /**
     * Adds the vehicle to most suitable floor if possible
     *
     * @param vehicle represents the vehicle to be added to this car park
     * @throws Exception when no suitable spot is found to park the vehicle
     */
    public void addVehicle(Vehicle vehicle) throws Exception{
        try {
            // Get most suitable floor for the vehicle to park
            Floor mostSuitable = this.getMostSuitableFloorForVehicle(vehicle);
            // Add the vehicle to the floor
            mostSuitable.addVehicle(vehicle);
            // Add the vehicle to current list of vehicles
            this.vehicleList.add(vehicle);
        }catch (Exception e){
            throw new Exception("Car park cannot accommodate vehicle at the moment.");
        }
    }

    /**
     * @return double representing the number of free slots in this car park
     */
    public double getFreeSlots(){
        // Variable to keep track of all free slots
        double freeSlots = 0.0;
        // Iterate over available floors and add the free slots
        for(Floor currFloor : this.floorList){
            freeSlots += currFloor.getAvailableNumberOfSlots();
        }
        return freeSlots;
    }

    /**
     * @param plateId represents the plateID of the vehicle to be deleted
     * @return Vehicle instance of vehicle to be deleted
     * @throws Exception when the specified vehicle is not found in the car park
     */
    public Vehicle deleteVehicle(String plateId) throws Exception{
        // Iterate over all the floors
        // Delete the vehicle from the floor if the vehicle is parked there
        // Remove vehicle from this car park's vehicle list if the vehicle was deleted from the floor
        for(Floor currFloor : this.floorList){
            Vehicle vehicle = currFloor.getVehicleById(plateId);
            if (vehicle != null){
                currFloor.deleteVehicleByInstance(vehicle);
                this.vehicleList.remove(vehicle);
                return vehicle;
            }
        }
        // To arrive here, the vehicle is not found wth the plateID given
        throw new Exception("Vehicle not found with specified ID");
    }

    /**
     * Calculate percentages of the number of different types of vehicles parked in this car park
     *
     * @return hashmap containing percentages for each vehicle type
     */
    public HashMap<String, Double> getVehiclePercentages(){
        // Initialize variable to keep track of vehicle count
        double vehicleCount = 0;
        // Create a hashmap to store the percentage for each vehicle
        HashMap<String, Double> vehiclePercentage = new HashMap<>();


        for(Vehicle vehicle: this.vehicleList){
            // Count the total number of vehicle for each vehicle type
            // Count the total number of vehicle for all types
            vehicleCount += 1;
            String vehicleType = vehicle.getVehicleType().getValue();
            if(vehiclePercentage.containsKey(vehicleType)){
                vehiclePercentage.put(vehicleType, vehiclePercentage.get(vehicleType)+1.0);
            }else{
                vehiclePercentage.put(vehicleType, 1.0);
            }
        }

        // Divide the hashmap values by total vehicle count and multiply by 100 to get the percentage of vehicles for each type
        for(String vehicleType: vehiclePercentage.keySet()){
            double percentageFactor = 100/vehicleCount;
            vehiclePercentage.put(vehicleType, vehiclePercentage.get(vehicleType)*percentageFactor);
        }

        return vehiclePercentage;
    }

    /**
     * @return oldest vehicle that has entered this car park
     * @throws Exception is thrown when there are no vehicles in this car park
     */
    public Vehicle getOldestVehicle() throws Exception{
        // Sort the vehicle list in Chronological order (Descending)
        Collections.sort(this.vehicleList, Collections.reverseOrder());
        if(this.vehicleList.size() > 0){
            return this.vehicleList.get(0);
        }
        // To arrive here, no vehicles are there in this car park
        throw new Exception("No vehicles in the car park");
    }

    /**
     * @return latest vehicle that has entered this car park
     * @throws Exception is thrown when there are no vehicles in this car park
     */
    public Vehicle getLatestVehicle() throws Exception{
        // Sort the vehicle list in Chronological order (Ascending)
        Collections.sort(this.vehicleList);
        if(this.vehicleList.size() > 0){
            return this.vehicleList.get(0);
        }
        // To arrive here, no vehicles are there in this car park
        throw new Exception("No vehicles in the car park");
    }

    /**
     * @param plateId represents the plateID of the vehicle to be found
     * @return vehicle instance of the vehicle that is found
     * @throws Exception if no vehicle is found with the particular plateID
     */
    public Vehicle getVehicleById(String plateId) throws Exception{
        // Iterate over all floors and check if the vehicle is parked there
        // If so return the instance
        for(Floor currFloor : this.floorList){
            Vehicle vehicle = currFloor.getVehicleById(plateId);
            if(vehicle != null){
                return vehicle;
            }
        }
        // To arrive here, the vehicle is not found wth the plateID given
        throw new Exception("Vehicle not found");
    }

    /**
     * @param day String representing the day the vehicle has entered
     * @param year String representing the year the vehicle has entered
     * @return list of vehicles that has entered on a particular day and year
     * @throws Exception is thrown when no vehicles have entered on that particular day and year
     */
    public List<Vehicle> getVehicleByDayYear(String day, String year) throws Exception{
        // Initialize a list to store the vehicles that have entered on that day and year
        List<Vehicle> availableVehicles = new ArrayList<>();

        // Iterate over the vehicle list and see if the vehicle's entered day and year match
        // If so, add it to the availableVehicles list
        for(Vehicle vehicle: this.vehicleList){
            DateTime enteredTime = vehicle.getEntryTime();
            String enteredDay = String.valueOf(enteredTime.getDate().getDay());
            String enteredYear = String.valueOf(enteredTime.getDate().getYear());
            if(enteredDay.equalsIgnoreCase(day) && enteredYear.equalsIgnoreCase(year)){
                availableVehicles.add(vehicle);
            }
        }

        if(availableVehicles.size() == 0){
            // Throw exception if no vehicles are found on that day and year
            throw new Exception("No vehicles found on that date.");
        }

        return availableVehicles;
    }


}
