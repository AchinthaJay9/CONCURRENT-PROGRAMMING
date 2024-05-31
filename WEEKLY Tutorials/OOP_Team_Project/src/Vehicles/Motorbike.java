package Vehicles;

/**
 * Class represents any Motorbike which is a subclass of the {@link Vehicle} class
 */
public class Motorbike extends Vehicle{

    /**
     * Primary constructor of the {@link Motorbike} class
     * Initializes plate ID of the motorbike
     * Set the vehicle type to MOTORBIKE which is {@link VehicleTypes} enum
     * Set the slots needed by the motorbike to 0.33333 ...
     * @param plateID - Represents the plate ID of the motorbike
     */
    public Motorbike(String plateID){
        // Call superclass constructor along with plateID
        super(plateID);
        // Set vehicle type to motorbike
        this.vehicleType = VehicleTypes.MOTORBIKE;
        // Initialize slots needed
        this.slotsNeeded = (1.0/3.0);
    }

    /**
     * @return string version of the {@link Motorbike} class along with its attributes
     */
    @Override
    public String toString() {
        return "Motorbike with plate id- "+this.plateID+", entered at: "+this.entryTime.getDateTime();
    }
}
