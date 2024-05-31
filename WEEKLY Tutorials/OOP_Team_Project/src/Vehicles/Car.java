package Vehicles;

/**
 * Class represents any Car which is a subclass of the {@link Vehicle} class
 */
public class Car extends Vehicle{

    /**
     * Primary constructor of the {@link Car} class
     * Initializes plate ID of the car
     * Set the vehicle type to CAR which is {@link VehicleTypes} enum
     * Set the slots needed by the van to 1.0
     * @param plateID - Represents the plate ID of the car
     */
    public Car(String plateID){
        // Call superclass constructor along with plateID
        super(plateID);
        // Set vehicle type to car
        this.vehicleType = VehicleTypes.CAR;
        // Initialize slots needed
        this.slotsNeeded = 1.0;
    }

    /**
     * @return string version of the {@link Car} class along with its attributes
     */
    @Override
    public String toString() {
        return "Car with plate id- "+this.plateID+", entered at: "+this.entryTime.getDateTime();
    }
}
