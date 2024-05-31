package CarPark;

import Vehicles.Vehicle;

/**
 * Interface that incorporates the main functionality that has to be implemented by a car park manager
 */
public interface CarParkManager {

    /**
     * Method is used to add a vehicle to the car park
     *
     * @param vehicle instance of the vehicle to be added to the car park
     */
    void addVehicle(Vehicle vehicle);

    /**
     * method is used to delete a vehicle parked using its plateID
     *
     * @param plateID plateID of the vehicle to be deleted
     */
    void deleteVehicle(String plateID);

    /**
     * method is used to print all the vehicles currently in the car park
     */
    void printVehiclesParked();

    /**
     * method is used to print the oldest vehicle that has entered the car park
     */
    void printOldestVehicle();

    /**
     * method is used to print the latest vehicle that has entered the car park
     */
    void printLatestVehicle();

    /**
     * method is used to print the receipt of the vehicle when its plateID is provided
     *
     * @param plateID plateID of the vehicle
     */
    void printReceipt(String plateID);

    /**
     * method is used to print the list of vehicles that has entered the car park on a particular day and year
     *
     * @param Day day the vehicle has entered the car park
     * @param year year the vehicle has entered the car park
     */
    void printVehiclesByDate(String Day,String year);

    /**
     * method is used to print the percentages of each vehicle type within the car park
     */
    void printVehiclesPercentages();

    /**
     * method is used to display the parking charges for each vehicle in the car park
     */
    void displayParkingCharges();

    /**
     * method is used to save the data
     */
    void saveData();

    /**
     * method is used to retrieve the data
     */
    void retrieveData();


}
