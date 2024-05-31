package lk.iit.level6.concurrent.assignment;

import java.util.Random;

public class Passenger implements Runnable{

    private final TicketMachine ticketMachine;
    private final Ticket ticket;


    public Passenger(TicketMachine ticketMachine, Ticket ticket) {
        this.ticketMachine = ticketMachine;
        this.ticket = ticket;

    }

    @Override
    public void run() {
        Random random = new Random();

        ticketMachine.printTicket( this.ticket);

        System.out.println("Passenger " + this.ticket.getPassengerInfo().getPassengerName() + " got " + ticket);

        try {
            Thread.sleep(random.nextInt(100)+1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
