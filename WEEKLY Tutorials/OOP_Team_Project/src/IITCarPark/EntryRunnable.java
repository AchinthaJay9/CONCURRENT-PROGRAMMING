package IITCarPark;

import CarPark.CarPark;
import Vehicles.Vehicle;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Class that Implements the {@link Runnable} interface
 * This class is used to create a thread which simulates vehicles entering a car park from a particular entrance queue in the {@link IITCarParkSimulator} class
 */
public class EntryRunnable implements Runnable{

    /**
     * Represents the name of the thread in the {@link EntryRunnable} class
     */
    private String name;
    /**
     * Represents a queue of this car park  in the {@link EntryRunnable} class
     */
    private ConcurrentLinkedDeque queue;
    /**
     * Represents the time the thread should sleep in the {@link EntryRunnable} class
     */
    private final int SLEEP;
    /**
     * Represents the car park in which the simulation is shown in the {@link EntryRunnable} class
     */
    private CarPark carPark;

    /**
     * Primary Constructor of the {@link EntryRunnable} class
     *
     * @param name String which is the name given to the thread
     * @param sleep Integer which represents the time the thread should sleep each iteration
     * @param queue Tread safe Concurrent Deque which represents the queue in which the simulation is done
     * @param carPark Car park in which the simulation is done
     */
    public EntryRunnable(String name, int sleep, ConcurrentLinkedDeque queue, CarPark carPark){
        this.queue = queue;
        this.SLEEP = sleep;
        this.name = name;
        this.carPark = carPark;
    }

    /**
     * @return name of the current thread
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method which executes the runnable
     */
    @Override
    public void run() {
        // Continuously get the first vehicle in the queue and add to the car park
        while(true){
            Vehicle vehicleToEnter = (Vehicle) this.queue.poll();
            try {
                // Check if a vehicle is currently in front of the queue
                if(vehicleToEnter != null){
                    this.carPark.addVehicle(vehicleToEnter);
                    System.out.println(vehicleToEnter + " entered the car park");
                }
            } catch (Exception e) {
                // This is when car park is full, i.e. all possible floors to accommodate the vehicle is full
                System.out.println("Cannot accommodate "+vehicleToEnter+" at the moment");
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
