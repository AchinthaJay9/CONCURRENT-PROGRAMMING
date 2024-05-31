package IITCarPark;

import CarPark.CarPark;
import Vehicles.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Class that Implements the {@link Runnable} interface
 * This class is used to create a thread which simulates vehicles leaving a car park in the {@link IITCarParkSimulator} class
 */
public class ExitRunnable implements Runnable {

    /**
     * Represents the name of the thread in the {@link ExitRunnable} class
     */
    private String name;
    /**
     * Represents a queue of this car park  in the {@link ExitRunnable} class
     */
    private ConcurrentLinkedDeque queue;
    /**
     * Represents the time the thread should sleep in the {@link ExitRunnable} class
     */
    private final int SLEEP;
    /**
     * Represents the car park in which the simulation is shown in the {@link ExitRunnable} class
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
    public ExitRunnable(String name, int sleep, ConcurrentLinkedDeque queue, CarPark carPark) {
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


    @Override
    public void run() {
        // Simulate random vehicles leaving this car park periodically
        while (true) {
            List<Vehicle> vehicleList = new ArrayList<>(this.carPark.getVehicleList());
            Collections.shuffle(vehicleList);
            if (vehicleList.size() != 0) {
                // Get a random vehicle that will be removed
                Vehicle vehicleToRemove = vehicleList.get(0);
                try {
                    // Remove the vehicle from teh car park
                    this.carPark.deleteVehicle(vehicleToRemove.getPlateID());
                    System.out.println(vehicleToRemove + " left the car park");
                } catch (Exception e) {
                    System.out.println(vehicleToRemove.getPlateID() + " already left the car park");
                }
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
