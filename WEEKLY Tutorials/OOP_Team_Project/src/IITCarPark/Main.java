package IITCarPark;

import Vehicles.*;

import java.util.Scanner;

/**
 * Class that runs the car park application
 */
public class Main {

    /**
     * Method that prints the main options for the car park manager to choose from
     */
    public static void printMainOptions() {
        System.out.println("Enter number to choose option");
        System.out.println("===============================================");
        System.out.println("Enter '1'  - To add a new vehicle ");
        System.out.println("Enter '2'  - To delete a vehicle vehicle ");
        System.out.println("Enter '3'  - To print current list of vehicles ");
        System.out.println("Enter '4'  - To print statistics ");
        System.out.println("Enter '5'  - Save to Database");
        System.out.println("Enter '6'  - Read From Database");
        System.out.println("Enter '-1' - To exit application");
        System.out.println("===============================================");
    }

    /**
     * Prints a personalized goodbye message
     *
     * @param name name of the operator
     */
    public static void exitMessage(String name) {
        System.out.println("===============================================");
        System.out.println("Thank you for using the Application Mr. " + name);
        System.out.println("===============================================");
    }

    /**
     * Prints the vehicle type options that can be added to the car park
     */
    private static void printVehicleTypes() {
        System.out.println("Enter '1'  - Motorbike");
        System.out.println("Enter '2'  - Car");
        System.out.println("Enter '3'  - Van");
        System.out.println("Enter '4'  - Minibus");
        System.out.println("Enter '5' - Mini lorry");
        System.out.println("Enter '-1' - To return to main menu");
    }

    /**
     * Method handles the logic of adding different types of vehicles ti the car park
     *
     * @param manager IIT car park manager object
     * @param sc      Scanner to get input of the operator
     */
    public static void addVehicle(IITCarParkManager manager, Scanner sc) {
        while (true) {
            System.out.println("Choose the type of vehicle you want to enter");
            printVehicleTypes();
            int choice = sc.nextInt();
            System.out.println("Enter the plateID of the vehicle");
            String plateId = sc.next();
            Vehicle vehicleToAdd;
            switch (choice) {
                case 1:
                    vehicleToAdd = new Motorbike(plateId);
                    break;
                case 2:
                    vehicleToAdd = new Car(plateId);
                    break;
                case 3:
                    vehicleToAdd = new Van(plateId);
                    break;
                case 4:
                    vehicleToAdd = new Minibus(plateId);
                    break;
                case 5:
                    vehicleToAdd = new MiniLorry(plateId);
                    break;
                case -1:
                    return;
                default:
                    System.out.println("Invalid Choice of vehicle type! Choose between 1, 2, 3, 4 or 5");
                    vehicleToAdd = null;
                    break;
            }

            if (vehicleToAdd != null) {
                manager.addVehicle(vehicleToAdd);
            }

            System.out.println("Do you want to add more vehicles? (Y/N)");
            String wantToContinue = sc.next();
            if (wantToContinue.toLowerCase().charAt(0) == 'n') {
                return;
            }
        }
    }

    /**
     * Method handles the logic of deleting a vehicle from the car park
     *
     * @param manager IIT car park manager object
     * @param sc      Scanner to get input of the operator
     */
    public static void deleteVehicle(IITCarParkManager manager, Scanner sc) {
        while (true) {
            System.out.println("Enter the plateID of the vehicle to be deleted");
            String plateID = sc.next();
            manager.deleteVehicle(plateID);

            System.out.println("Do you want to delete another vehicle? (Y/N)");
            String wantToContinue = sc.next();
            if (wantToContinue.toLowerCase().charAt(0) == 'n') {
                return;
            }
        }
    }

    /**
     * Method prints the vehicle list of the car park in chronological order
     *
     * @param manager IIT car park manager object
     */
    public static void printAllVehicles(IITCarParkManager manager) {
        System.out.println("Vehicle list in Chronological Order");
        System.out.println("===============================================");
        manager.printVehiclesParked();
        System.out.println("===============================================");
    }

    /**
     * Methods prints the types of statistics that can be analyzed by the operator
     */
    private static void printStatisticTypes() {
        System.out.println("Enter '1'  - Percentage of Vehicles parked");
        System.out.println("Enter '2'  - Latest and the Oldest Vehicle in the Car Park");
        System.out.println("Enter '3'  - List of Vehicles on a specific Date");
        System.out.println("Enter '4'  - Parking charge for vehicles in the car park");
        System.out.println("Enter '5'  - Visualize the car park");
        System.out.println("Enter '-1' - Return to main menu");
    }


    /**
     * Method handles the logic of displaying different statistics of the car park
     *
     * @param manager IIT car park manager object
     * @param sc      Scanner to get input of the operator
     */
    public static void printStatistics(IITCarParkManager manager, Scanner sc) {
        while (true) {
            System.out.println("Choose the type of statistic, you want to be displayed ");
            printStatisticTypes();
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    manager.printVehiclesPercentages();
                    break;
                case 2:
                    manager.printLatestVehicle();
                    manager.printOldestVehicle();
                    break;
                case 3:
                    System.out.println("Enter the entry day for the list of vehicles to ve found.");
                    String day = sc.next();
                    System.out.println("Enter the entry year for the list of vehicles to ve found.");
                    String year = sc.next();
                    manager.printVehiclesByDate(day, year);
                    break;
                case 4:
                    System.out.println("Vehicle Parking Charges are");
                    System.out.println("===============================================");
                    manager.displayParkingCharges();
                    System.out.println("===============================================");
                    break;
                case 5:
                    manager.displayEntireCarPark();
                    break;

                case -1:
                    return;
                default:
                    System.out.println("Invalid Choice of vehicle type! Choose between 1, 2, 3, 4 or 5");
                    break;
            }

            System.out.println("Do you want to analyze another statistic? (Y/N)");
            String wantToContinue = sc.next();
            if (wantToContinue.toLowerCase().charAt(0) == 'n') {
                return;
            }
        }
    }

    public static void main(String[] args) {
        IITCarParkManager manager = new IITCarParkManager();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name");
        String name = sc.next();
        System.out.println("Welcome Mr. " + name + " to the IIT car park system.");
        int choice;
        mainLoop:
        while (true) {
            printMainOptions();
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addVehicle(manager, sc);

                    break;
                case 2:
                    deleteVehicle(manager, sc);
                    break;
                case 3:
                    printAllVehicles(manager);
                    break;
                case 4:
                    printStatistics(manager, sc);
                    break;
                case 5:
                    manager.saveData();;
                    break;
                case 6:
                    manager.retrieveData();;
                    break;
                case -1:
                    manager.saveData();
                    exitMessage(name);
                    // Break the while loop and exit application
                    break mainLoop;
                default:
                    System.out.println("Invalid Choice! Choose between 1, 2, 3, 4, 5, 6 or -1");
            }
        }

    }
}

