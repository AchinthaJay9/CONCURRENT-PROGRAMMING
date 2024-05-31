package lk.iit.level6.concurrent.assignment;

import java.math.BigDecimal;

public class TicketSystemSimulation {

    public static void main(String[] args) {
        // Create thread groups
        ThreadGroup passengers = new ThreadGroup("Passengers");
        ThreadGroup technicians = new ThreadGroup("Technicians");

        final Machine machine = new Machine( TicketMachine.SHEETS_PER_PACK, TicketMachine.FULL_TONER_LEVEL, 3, passengers); // Initialize the machine
        System.out.println("Initial state of the machine: no of tickets printed: " + machine.getNoOfTicketsPrinted() + ", paper level: " + machine.getPaperLevel() + ", toner level: " + machine.getTonerLevel());
        // Create passengerInfo and travelInfo
        PassengerInfo passengerInfo1 = new PassengerInfo("Passenger1", "123-456-7890", "passenger1@example.com");
        PassengerInfo passengerInfo2 = new PassengerInfo("Passenger2", "098-765-4321", "passenger2@example.com");
        PassengerInfo passengerInfo3 = new PassengerInfo("Passenger3", "123-456-7890", "passenger3@example.com");
        PassengerInfo passengerInfo4 = new PassengerInfo("Passenger4", "098-765-4321", "passenger4@example.com");
        
        TravelInfo travelInfo1 = new TravelInfo("Destination1", "DepartureLocation1");
        TravelInfo travelInfo2 = new TravelInfo("Destination2", "DepartureLocation2");
        TravelInfo travelInfo3 = new TravelInfo("Destination3", "DepartureLocation3");
        TravelInfo travelInfo4 = new TravelInfo("Destination4", "DepartureLocation4");
        BigDecimal ticketPrice = new BigDecimal("10.00");

        // Create tickets
        Ticket passenger1Ticket = new Ticket(1, ticketPrice, passengerInfo1, travelInfo1);
        Ticket passenger2Ticket = new Ticket(2, ticketPrice, passengerInfo2, travelInfo2);
        Ticket passenger3Ticket = new Ticket(3, ticketPrice, passengerInfo3, travelInfo3);
        Ticket passenger4Ticket = new Ticket(4, ticketPrice, passengerInfo4, travelInfo4);

        // Create passengers and technicians
        Passenger passenger1 = new Passenger(machine, passenger1Ticket);
        Passenger passenger2 = new Passenger(machine, passenger2Ticket);
        Passenger passenger3 = new Passenger(machine, passenger3Ticket);
        Passenger passenger4 = new Passenger(machine, passenger4Ticket);

        TicketTonerTechnician tonerTechnician = new TicketTonerTechnician("Toner Technician", machine);
        TicketPaperTechnician paperTechnician = new TicketPaperTechnician("Paper Technician", machine);

        // Create threads
        Thread passengerThread1 = new Thread(passengers, passenger1, "Passenger1");
        Thread passengerThread2 = new Thread(passengers, passenger2, "Passenger2");
        Thread passengerThread3 = new Thread(passengers, passenger3, "Passenger3");
        Thread passengerThread4 = new Thread(passengers, passenger4, "Passenger4");

        Thread tonerTechnicianThread = new Thread(technicians, tonerTechnician, "TonerTechnician");
        Thread paperTechnicianThread = new Thread(technicians, paperTechnician, "PaperTechnician");

        // Start threads
        passengerThread1.start();
        passengerThread2.start();
        passengerThread3.start();
        passengerThread4.start();

        tonerTechnicianThread.start();
        paperTechnicianThread.start();

        try {
            // Wait for all passengers to finish
            passengerThread1.join();
            passengerThread2.join();
            passengerThread3.join();
            passengerThread4.join();
            System.out.println("All passengers have been served.");

            // Wait for all technicians to finish
            tonerTechnicianThread.join();
            paperTechnicianThread.join();
            System.out.println("All technicians have finished their work.");

            // Print the final state of the machine
            System.out.println("Final state of the machine: no of tickets printed: " + machine.getNoOfTicketsPrinted() + ", paper level: " + machine.getPaperLevel() + ", toner level: " + machine.getTonerLevel());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Main thread interrupted", e);
        }


    }
}
