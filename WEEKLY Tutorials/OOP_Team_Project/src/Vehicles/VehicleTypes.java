package Vehicles;

/**
 * Enum class containing constants to represent different vehicle types
 */
public enum VehicleTypes {

    /**
     * Represents the vehicles belonging to the {@link Motorbike} class
     */
    MOTORBIKE("Motorbike"),
    /**
     * Represents the vehicles belonging to the {@link Car} class
     */
    CAR("Car"),
    /**
     * Represents the vehicles belonging to the {@link Van} class
     */
    VAN("Van"),
    /**
     * Represents the vehicles belonging to the {@link MiniLorry} class
     */
    MINI_LORRY("Mini Lorry"),
    /**
     * Represents the vehicles belonging to the {@link Minibus} class
     */
    MINI_BUS("Mini Bus");


    /**
     * Represents String value of each enum
     */
    private String value;

    /**
     * Constructor for enum class
     * @param value string that represents each enum's string value
     */
    VehicleTypes(String value){
        this.value = value;
    }

    /**
     * @return string that represents each enums string value
     */
    public String getValue(){
        return this.value;
    }

}
