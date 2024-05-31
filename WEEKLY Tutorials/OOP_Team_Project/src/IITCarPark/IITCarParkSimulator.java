package IITCarPark;

import CarPark.CarPark;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Class that is used to run a Simulation of the IIT car park
 * The Class simulates vehicles forming queues, vehicles entering and leaving from each entrance
 */
public class IITCarParkSimulator {

    /**
     * Represents the car park that the simulation will run on, in the {@link IITCarParkSimulator} class
     */
    private final CarPark carPark;

   /**
     * A thread safe concurrent queue that represents entrance 1 in this car park in the {@link IITCarParkSimulator} class
     */
   private ConcurrentLinkedDeque entry1Queue;
   /**
     * A thread safe concurrent queue that represents entrance 2 in this car park in the {@link IITCarParkSimulator} class
     */
   private ConcurrentLinkedDeque entry2Queue;
   /**
     * A thread safe concurrent queue that represents entrance 3 in this car park in the {@link IITCarParkSimulator} class
     */
   private ConcurrentLinkedDeque entry3Queue;
   /**
     * A thread safe concurrent queue that represents entrance 4 in this car park in the {@link IITCarParkSimulator} class
     */
   private ConcurrentLinkedDeque entry4Queue;
   /**
     * A thread safe concurrent queue that represents entrance 5 in this car park in the {@link IITCarParkSimulator} class
     */
   private ConcurrentLinkedDeque entry5Queue;
   /**
     * A thread safe concurrent queue that represents entrance 6 in this car park in the {@link IITCarParkSimulator} class
     */
   private ConcurrentLinkedDeque entry6Queue;

    /**
     * Default constructor of the {@link IITCarParkSimulator} class
     */
    public IITCarParkSimulator(){
        // Create an IIT car park manager
        IITCarParkManager manager = new IITCarParkManager();
        // Set the current car park managed to an IIT car park
        this.carPark = manager.getIitCarPark();
        // Initialize all the queues for each entrance of this car park
        entry1Queue = new ConcurrentLinkedDeque();
        entry2Queue = new ConcurrentLinkedDeque();
        entry3Queue = new ConcurrentLinkedDeque();
        entry4Queue = new ConcurrentLinkedDeque();
        entry5Queue = new ConcurrentLinkedDeque();
        entry6Queue = new ConcurrentLinkedDeque();
    }


    /**
     * Method which simulates the IIT car park with synthetic traffic
     */
    public void startSimulation(){
        System.out.println("Simulation started!");
        // Create 6 Threads which will keep on randomly adding vehicles to each entrance queue of this car park
        Thread entry1Queue = new Thread(new EntryQueueRunnable("Entry 1 Queue", 500, this.entry1Queue));
        Thread entry2Queue = new Thread(new EntryQueueRunnable("Entry 2 Queue", 500, this.entry2Queue));
        Thread entry3Queue = new Thread(new EntryQueueRunnable("Entry 3 Queue", 500, this.entry3Queue));
        Thread entry4Queue = new Thread(new EntryQueueRunnable("Entry 4 Queue", 500, this.entry4Queue));
        Thread entry5Queue = new Thread(new EntryQueueRunnable("Entry 5 Queue", 500, this.entry5Queue));
        Thread entry6Queue = new Thread(new EntryQueueRunnable("Entry 6 Queue", 500, this.entry6Queue));

        // Create 6 Threads which will continuously add the first vehicle in each entrance queue to this car park
        Thread entrance1 = new Thread(new EntryRunnable("Entrance 1", 500, this.entry1Queue, this.carPark));
        Thread entrance2 = new Thread(new EntryRunnable("Entrance 2", 500, this.entry2Queue, this.carPark));
        Thread entrance3 = new Thread(new EntryRunnable("Entrance 3", 500, this.entry3Queue, this.carPark));
        Thread entrance4 = new Thread(new EntryRunnable("Entrance 4", 500, this.entry4Queue, this.carPark));
        Thread entrance5 = new Thread(new EntryRunnable("Entrance 5", 500, this.entry5Queue, this.carPark));
        Thread entrance6 = new Thread(new EntryRunnable("Entrance 6", 500, this.entry6Queue, this.carPark));

        // Create 6 Threads which will simulate vehicles leaving from each exit of this car park
        Thread exit1 = new Thread(new ExitRunnable("Exit 1", 2000, this.entry1Queue, this.carPark));
        Thread exit2 = new Thread(new ExitRunnable("Exit 2", 2000, this.entry2Queue, this.carPark));
        Thread exit3 = new Thread(new ExitRunnable("Exit 3", 2000, this.entry3Queue, this.carPark));
        Thread exit4 = new Thread(new ExitRunnable("Exit 4", 2000, this.entry4Queue, this.carPark));
        Thread exit5 = new Thread(new ExitRunnable("Exit 5", 2000, this.entry5Queue, this.carPark));
        Thread exit6 = new Thread(new ExitRunnable("Exit 6", 2000, this.entry6Queue, this.carPark));

        // Start randomly adding vehicles to entrance 1 queue
        // Start entering vehicles to this car park from the entrance 1 queue
        // Simulate vehicles leaving from exit 1
        entrance1.start();
        entry1Queue.start();
        exit1.start();

        // Start randomly adding vehicles to entrance 2 queue
        // Start entering vehicles to this car park from the entrance 2 queue
        // Simulate vehicles leaving from exit 2
        entrance2.start();
        entry2Queue.start();
        exit2.start();

        // Start randomly adding vehicles to entrance 3 queue
        // Start entering vehicles to this car park from the entrance 3 queue
        // Simulate vehicles leaving from exit 3
        entrance3.start();
        entry3Queue.start();
        exit3.start();

        // Start randomly adding vehicles to entrance 4 queue
        // Start entering vehicles to this car park from the entrance 4 queue
        // Simulate vehicles leaving from exit 4
        entrance4.start();
        entry4Queue.start();
        exit4.start();


        // Start randomly adding vehicles to entrance 5 queue
        // Start entering vehicles to this car park from the entrance 5 queue
        // Simulate vehicles leaving from exit 5
        entrance5.start();
        entry5Queue.start();
        exit5.start();

        // Start randomly adding vehicles to entrance 6 queue
        // Start entering vehicles to this car park from the entrance 6 queue
        // Simulate vehicles leaving from exit 6
        entrance6.start();
        entry6Queue.start();
        exit6.start();

    }

    public static void main(String[] args) {
        IITCarParkSimulator simulator = new IITCarParkSimulator();
        simulator.startSimulation();
    }


}
