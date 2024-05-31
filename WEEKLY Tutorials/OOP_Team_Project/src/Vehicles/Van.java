package Vehicles;

/**
 * Class represents any Van which is a subclass of the {@link Vehicle} class
 */
public class Van extends Vehicle{

    /**
     * Primary constructor of the {@link Van} class
     * Initializes plate ID of the van
     * Set the vehicle type to VAN which is {@link VehicleTypes} enum
     * Set the slots needed by the van to 2.0
     * @param plateID - Represents the plate ID of the van
     */
    public Van(String plateID){
        // Call superclass constructor along with plateID
        super(plateID);
        // Set vehicle type to van
        this.vehicleType = VehicleTypes.VAN;
        // Initialize slots needed
        this.slotsNeeded = 2.0;
    }

    /**
     * @return string version of the {@link Van} class along with its attributes
     */
    @Override
    public String toString() {
        return "Van with plate id- "+this.plateID+", entered at: "+this.entryTime.getDateTime();
    }
}
