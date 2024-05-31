package Vehicles;

/**
 * Class represents any Minibus which is a subclass of the {@link Vehicle} class
 */
public class Minibus extends Vehicle{

    /**
     * Primary constructor of the {@link Minibus} class
     * Initializes plate ID of the minibus
     * Set the vehicle type to MINI_BUS which is {@link VehicleTypes} enum
     * Set the slots needed by the van to 3.0
     * @param plateID - Represents the plate ID of the van
     */
    public Minibus(String plateID){
        super(plateID);
        this.vehicleType = VehicleTypes.MINI_BUS;
        this.slotsNeeded = 3.0;
    }

    /**
     * @return string version of the {@link Minibus} class along with its attributes
     */
    @Override
    public String toString() {
        return "Minibus with plate id- "+this.plateID+", entered at: "+this.entryTime.getDateTime();
    }

}
