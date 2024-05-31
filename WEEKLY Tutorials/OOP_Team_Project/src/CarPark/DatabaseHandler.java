package CarPark;

import java.io.*;

/**
 * Class that incorporates the Database handling functionalities
 */
public class DatabaseHandler {

    /**
     * Represents the car park that the database operations will be performed in the {@link DatabaseHandler} class
     */
    private CarPark carpark;

    /**
     * Primary Constructor of the {@link DatabaseHandler} class
     *
     * @param carPark car park that will be saved and retrieved
     */
    public DatabaseHandler(CarPark carPark){
        this.carpark = carPark;
    }

    /**
     * Save the car park data to a specific file location
     *
     * @param file_url String where the location of database is stored
     */
    public void writeData(String file_url) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(file_url);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject( this.carpark);//write object
            out.close();
            fileOut.close();
            System.out.println("All the data are saved in CarPark");

        } catch (
                IOException i) {
            i.printStackTrace();
        }
    }


    /**
     * Retrieve the car park data from a specific file location
     *
     * @param file_url String where the location of database is stored
     * @return updated car park
     */
    public CarPark readData(String file_url) {
        try {

            FileInputStream fileIn = new FileInputStream(file_url);
            ObjectInputStream in = new ObjectInputStream(fileIn);
             this.carpark = (CarPark) in.readObject();//read object
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Carpark class not found");
            c.printStackTrace();
            return null;
        }
        System.out.println("***************************************************************************************************************");
        System.out.println("Carpark");
        System.out.println("FloorList: " + carpark.getFloorList());
        System.out.println("VehicleList: " + carpark.getVehicleList());
        System.out.println("***************************************************************************************************************");
        return this.carpark;
    }

}
