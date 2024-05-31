package CarPark;

import Vehicles.Vehicle;
import Vehicles.VehicleTypes;
import com.google.common.util.concurrent.AtomicDouble;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class that is used to represent each floor level used in the {@link CarPark} class
 */
public class Floor implements Comparable<Floor> , Serializable {
    /**
     * Represents the maximum capacity of the {@link Floor} class
     * Used an atomic double from the guava library
     */
    private AtomicDouble maxCapacity;
    /**
     * Represents the current capacity of the {@link Floor} class
     * Used an atomic double from the guava library
     */
    private AtomicDouble currentCapacity;
    /**
     * Represents the current number of vehicles parked of the {@link Floor} class
     * Used atomic integer for thread safety (liveness of threads)
     */
    private AtomicInteger currentNumberOfVehicles;
    /**
     * Represents the list of vehicles parked of the {@link Floor} class
     * Used atomic integer for thread safety (liveness of threads)
     */
    private List<Vehicle> vehicleList;
    /**
     * A concurrent thread safe that represents the list of vehicle types preferred to be parked of the {@link Floor} class
     */
    private List<VehicleTypes> preferredVehicleType;
    /**
     * Represents the list of vehicle types possible to be parked of the {@link Floor} class
     */
    private List<VehicleTypes> possibleVehicleType;


    /**
     * Primary constructor of the {@link Floor} class
     *
     * @param preferredVehicleType list of preferred vehicle types to be parked at this floor
     * @param possibleVehicleType list of possible vehicle types to be parked at this floor
     * @param maxCapacity maximum parking capacity of this floor
     */
    public Floor(List<VehicleTypes> preferredVehicleType, List<VehicleTypes> possibleVehicleType, double maxCapacity){
        // set current capacity to 0
        this.currentCapacity = new AtomicDouble(0.0);
        // set number of vehicles to 0
        this.currentNumberOfVehicles = new AtomicInteger(0);
        // initialize vehicle list using a thread safe list
        this.vehicleList = Collections.synchronizedList(new ArrayList<>());
        // set the list of preferred vehicle types
        this.preferredVehicleType = preferredVehicleType;
        // set the list of possible vehicle types
        this.possibleVehicleType = possibleVehicleType;
        // set maximum parking capacity
        this.maxCapacity = new AtomicDouble(maxCapacity);
    }

    /**
     * @return number of vehicles parked at this floor
     */
    public int getCurrentNumberOfVehicles(){
        return currentNumberOfVehicles.get();
    }

    /**
     * @return get the current capacity of this floor
     */
    public double getCurrentCapacity() {
        return currentCapacity.get();
    }

    /**
     * @return get the list of vehicles parked at this floor
     */
    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    /**
     * @return list of preferred vehicle types of this floor
     */
    public List<VehicleTypes> getPreferredVehicleType(){
        return preferredVehicleType;
    }

    /**
     * @return list of possible vehicle types of this floor
     */
    public List<VehicleTypes> getPossibleVehicleType(){
        return possibleVehicleType;
    }

    /**
     * @return number of available slots in this floor
     */
    public double getAvailableNumberOfSlots(){
        return maxCapacity.get()- this.currentCapacity.get();
    }

    /**
     * @param vehicle instance of the vehicle to be parked
     * @return true if the vehicle can be parked on this floor
     */
    public boolean isParkingSlotsSufficient(Vehicle vehicle){
        double neededCapacity = vehicle.getSlotsNeeded();
        return ((this.currentCapacity.get() + neededCapacity) <= maxCapacity.get());
    }

    /**
     * Add a vehicle to be parked in this floor
     *
     * @param vehicle instance of the vehicle to be parked
     */
    public void addVehicle(Vehicle vehicle){

        // check if the vehicle can be parked
        if(!this.isParkingSlotsSufficient(vehicle)){
            System.out.println("Capacity is: "+this.currentCapacity);
            throw new IllegalStateException("No parking slots available");
        }
        // update current vehicle capacity by slots taken
        this.currentCapacity.getAndAdd(vehicle.getSlotsNeeded());
        // update current number of vehicles by +1
        this.currentNumberOfVehicles.incrementAndGet();
        // add vehicle to list of vehicles
        this.vehicleList.add(vehicle);
    }

    /**
     * @param id plateId of vehicle to be found
     * @return instance of vehicles found with the given plate ID
     */
    public Vehicle getVehicleById(String id){
        Vehicle found = null;
        // Iterate over the vehicle list and see if the vehicle has the plateId of vehicle to be found
        for(Vehicle v: this.vehicleList){
            if(v.getPlateID().equals(id)){
                // break loop once the vehicle is found
                found = v;
                break;
            }
        }
        return found;
    }

    /**
     * Delete vehicle from this floor from the given instance
     *
     * @param vehicle instance of vehicle to be removed
     * @return the vehicle instance which was removed
     * @throws Exception when no vehicle is found in this floor
     */
    public Vehicle deleteVehicleByInstance(Vehicle vehicle) throws Exception{
        // Delete vehicle from this floor
        boolean removed = this.vehicleList.remove(vehicle);
        if (removed){
            // Get the space occupied by the vehicle
            double freed_space = vehicle.getSlotsNeeded();
            // Free the space previously occupied by the vehicle
            this.currentCapacity.getAndAdd(-freed_space);
            // Decrement vehicle count by 1
            this.currentNumberOfVehicles.decrementAndGet();
            return vehicle;
        }else {
            // No vehicle was found in the floor by the given instance
            throw new Exception("Vehicle not found");
        }
    }

    /**
     * Delete vehicle from this floor from the given plate ID
     *
     * @param plateID plate ID of the vehicle to be deleted
     * @return vehicle instance of deleted vehicle
     * @throws Exception when no vehicle is found in this floor
     */
    public Vehicle deleteVehicleByPlateId(String plateID) throws Exception{
        Vehicle vehicleToRemove = null;
        // Iterate through the vehicle list and check if the given vehicle is found by its ID
        for(Vehicle vehicle: this.vehicleList){
            if(vehicle.getPlateID().equals(plateID)){
                vehicleToRemove = vehicle;
                break;
            }
        }
        // Remove the vehicle which is found
        if (vehicleToRemove != null){
            this.vehicleList.remove(vehicleToRemove);
            double freed_space = vehicleToRemove.getSlotsNeeded();
            // Free the space previously occupied by the vehicle
            this.currentCapacity.getAndAdd(-freed_space);
            // Decrement vehicle count by 1
            this.currentNumberOfVehicles.decrementAndGet();
            return vehicleToRemove;
        }else {
            // If this point is reached, the vehicle is not found.
            throw new Exception("Vehicle not found");
        }
    }

    /**
     * @param other other floor to be compared with this floor
     * @return integer which is 1, 0,-1 depending on the other floor's capacity
     */
    @Override
    public int compareTo(Floor other) {
        // Get this floor's current capacity
        double currCapacity = this.getCurrentCapacity();
        // Get the other floor's current capacity
        double otherCapacity = other.getCurrentCapacity();
        // Compare and return an integer depending on the two floor's capacities
        return Double.compare(currCapacity, otherCapacity);
    }

    /**
     * @return string version of this floor
     */
    @Override
    public String toString() {
        return "Floor{" +
                "currentCapacity=" + currentCapacity +
                ", vehicleList=" + vehicleList +
                '}';
    }
}
