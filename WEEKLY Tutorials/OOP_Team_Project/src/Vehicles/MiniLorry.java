package Vehicles;

/**
 * Class represents any Mini Lorry which is a subclass of the {@link Vehicle} class
 */
public class MiniLorry extends Vehicle{

    /**
     * Primary constructor of the {@link MiniLorry} class
     * Initializes plate ID of the mini lorry
     * Set the vehicle type to MINI_LORRY which is {@link VehicleTypes} enum
     * Set the slots needed by the mini lorry to 3.0
     * @param plateID - Represents the plate ID of the mini lorry
     */
    public MiniLorry(String plateID){
        // Call superclass constructor along with plateID
        super(plateID);
        // Set vehicle type to mini lorry
        this.vehicleType = VehicleTypes.MINI_LORRY;
        // Initialize slots needed
        this.slotsNeeded = 3.0;
    }

    /**
     * @return string version of the {@link MiniLorry} class along with its attributes
     */
    @Override
    public String toString() {
        return "Mini Lorry with plate id- "+this.plateID+", entered at: "+this.entryTime.getDateTime();
    }
}
