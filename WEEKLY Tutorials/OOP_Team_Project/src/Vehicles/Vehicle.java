package Vehicles;

import DateTime.DateTime;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class represents the superclass for each vehicle class supported by the system
 */
public abstract class Vehicle implements Comparable<Vehicle>, Serializable {

    /**
     * Represents the plate ID of each vehicle in the {@link Vehicle} class
     */
    protected String plateID;
    /**
     * Represents the entry time of each vehicle as a {@link DateTime} in the {@link Vehicle} class
     */
    protected DateTime entryTime;
    /**
     * Represents the number of slots needed to park each vehicle in the {@link Vehicle} class
     */
    protected double slotsNeeded;
    /**
     * Represents the type of vehicle as {@link VehicleTypes } enum in the {@link Vehicle} class
     */
    protected VehicleTypes vehicleType;

    /**
     * Primary constructor of the {@link Vehicle} class
     * Initializes plate ID of the vehicle and initializes the entry time
     *
     * @param plateID represents the plate Id of the vehicle being constructed
     */
    public Vehicle(String plateID) {
        // set plateID
        this.plateID = plateID;
        // initialize entry time
        this.entryTime = new DateTime();
    }

    /**
     * Sets a Date time as this vehicle's entry time.
     *
     * @param entryTime DateTime to be set as entry time
     */
    public void setEntryTime(DateTime entryTime) {
        this.entryTime = entryTime;
    }

    /**
     * @return dateTime in which this vehicle entered
     */
    public DateTime getEntryTime() {
        return entryTime;
    }

    /**
     * @return plate ID of this vehicle
     */
    public String getPlateID() {
        return plateID;
    }

    /**
     * @return the enum representing the type of this vehicle
     */
    public VehicleTypes getVehicleType(){
        return this.vehicleType;
    }

    /**
     * @return the number of slots this vehicle needs
     */
    public double getSlotsNeeded(){
        return this.slotsNeeded;
    }

    /**
     * @return double representing the total time parked of this vehicle in hours
     */
    public double getTimeParked(){
        DateTime currentTime = new DateTime();
        return currentTime.getDateTimeDiffHours(this.entryTime);
    }

    /**
     * @param vehicle represents the other vehicle to be compared
     * @return int according to which vehicle has parked the longest
     */
    @Override
    public int compareTo(Vehicle vehicle){
        // Get time parked of this vehicle
        Double veh1_timeParked = this.getTimeParked();
        // Get time parked of other vehicle
        Double veh2_timeParked = vehicle.getTimeParked();

        // Return 1 or 0 or -1 : corresponding to time parked by each vehicle
        return veh1_timeParked.compareTo(veh2_timeParked);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Vehicle) {
            Vehicle other = (Vehicle) o;
            return Objects.equals(getPlateID(), other.getPlateID()) && Objects.equals(getVehicleType(), other.getVehicleType());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlateID(), getVehicleType());
    }

}
