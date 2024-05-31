package IITCarPark;

import Vehicles.*;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Class that Implements the {@link Runnable} interface
 * This class is used to create a thread which simulates vehicles forming a queue in front of a particular entrance in the car park in the {@link IITCarParkSimulator} class
 */
public class EntryQueueRunnable implements Runnable{

    /**
     * Represents the name of the thread in the {@link EntryQueueRunnable} class
     */
    private String name;
    /**
     * Represents a queue of this car park in the {@link EntryQueueRunnable} class
     */
    private ConcurrentLinkedDeque queue;
    /**
     * Represents the time the thread should sleep in the {@link EntryQueueRunnable} class
     */
    private final int SLEEP;

    /**
     * Primary Constructor of the {@link EntryQueueRunnable} class
     *
     * @param name String which is the name given to the thread
     * @param sleep Integer which represents the time the thread should sleep each iteration
     * @param queue Tread safe Concurrent Deque which represents the queue in which the simulation is done
     */
    public EntryQueueRunnable(String name, int sleep, ConcurrentLinkedDeque queue){
        this.queue = queue;
        this.SLEEP = sleep;
        this.name = name;
    }

    /**
     * @return name of the current thread
     */
    public String getName() {
        return name;
    }

    /**
     * Method generates a random ID number of a required length
     *
     * @param len Integer which represents the length of the needed ID
     * @return a random plate ID for a vehicle
     */
    private String getRandomPlateID(int len){
        // Vocabulary to generate the ID from
        String VOCAB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder plateID = new StringBuilder();
        Random rnd = new Random();
        while (plateID.length() < len){
            int index = (int) (rnd.nextFloat() * VOCAB.length());
            plateID.append(VOCAB.charAt(index));
        }
        // return the generated string
        return plateID.toString();
    }

    /**
     * Method generates a random vehicle of a particular type with a random plateID
     *
     * @return Vehicle of random type: Car, Motorbike, MiniLorry, Minibus or Van
     */
    private Vehicle generateRandomVehicle(){
        // Generate a random plate ID
        String plateID = getRandomPlateID(6);
        Random rnd = new Random();
        double randomDouble = rnd.nextDouble()*10.0;
        Vehicle newVehicle;

        // Generate a random vehicle
        // Cars were given a higher chance due to higher availability of slots in the car park
        if(randomDouble <= 4.0){
            newVehicle = new Car(plateID);
        } else if(randomDouble <= 6.0){
            newVehicle = new Motorbike(plateID);
        }else if(randomDouble <= 7.0){
            newVehicle = new MiniLorry(plateID);
        }else if(randomDouble <= 8.0){
            newVehicle = new Minibus(plateID);
        }else {
            newVehicle = new Van(plateID);
        }

        return newVehicle;
    }

    /**
     * Method which executes the runnable
     */
    @Override
    public void run() {
        // Continuously generate a random vehicle and add it to the back of the entrance queue
        while(true){
            // Generate a random vehicle
            Vehicle vehicleToEnter = this.generateRandomVehicle();
            try {
                // Add vehicle to queue
                queue.add(vehicleToEnter);
            } catch (Exception e) {
                System.out.println("Error when adding vehicle to queue.");
            }
            try {
                // Sleep the thread giving other threads a chance to run
                Thread.sleep(SLEEP);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
