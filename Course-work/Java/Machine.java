package lk.iit.level6.concurrent.assignment;

public class Machine implements ServiceTicketMachine {

    private int paperLevel;
    private int tonerLevel;
    private final ThreadGroup passengers;
    private int noOfTicketsPrinted;

    public Machine(int paperLevel, int tonerLevel, int noOfTicketsPrinted, ThreadGroup passengers) {
        this.paperLevel = paperLevel;
        this.tonerLevel = tonerLevel;
        this.noOfTicketsPrinted = noOfTicketsPrinted;
        this.passengers = passengers;
    }

    private boolean isPaperAvailable() {
        return paperLevel > 0;
    }

    private boolean isTonerAvailable() {
        return tonerLevel >= TicketMachine.MINIMUM_TONER_LEVEL;
    }

    private void processPrinting() {
        tonerLevel -= 1;
        paperLevel -= 1;
    }

    @Override
    public synchronized void replaceTonerCartridge() {
        try {
            while (tonerLevel > MINIMUM_TONER_LEVEL && tonerLevel <= FULL_TONER_LEVEL) {
                // If there are no passengers, stop replacing
                if (passengers.activeCount() == 0){
                    System.out.println("No passengers, stopping toner replacement.");
                    return;
                }

                System.out.println("Toner level sufficient, not replacing yet.");

                wait(1000); // Wait for a while before checking again
            }
            System.out.println("Current Toner level: " + tonerLevel);
            tonerLevel = TicketMachine.FULL_TONER_LEVEL;
            System.out.println(Thread.currentThread().getName() + " Filled the Toner");
            System.out.println("New Toner level: " + tonerLevel);

            notifyAll(); // Notify all waiting threads

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrupted", e);
        }
    }

    @Override
    public synchronized void refillTicketPaper() {
        try {
            while (paperLevel > LOW_PAPER_THRESHOLD && paperLevel <= FULL_PAPER_TRAY) {
                // If there are no passengers, stop refilling
                if (passengers.activeCount() == 0){
                    System.out.println("No passengers, stopping paper refill.");
                    return;
                }
                System.out.println("Paper level sufficient, not refilling yet.");

                wait(1000); // Wait for a while before checking again
            }
            System.out.println("Current Paper level: " + paperLevel);
            paperLevel += TicketMachine.SHEETS_PER_PACK;
            System.out.println(Thread.currentThread().getName() + " Filled the Paper");
            System.out.println("New Paper level: " + paperLevel);

            notifyAll(); // Notify all waiting threads

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrupted", e);
        }
    }

    @Override
    public synchronized void printTicket(Ticket ticket) {
        try {
            System.out.println("Printing ticket by " + Thread.currentThread().getName() + " Ticket: " + ticket.toString());
            while (!(isPaperAvailable() && isTonerAvailable())) {
                if (!isPaperAvailable()) {
                    System.out.println("Paper level is low, waiting for refill.");
                }
                if (!isTonerAvailable()) {
                    System.out.println("Toner level is low, waiting for refill.");
                }

                wait(); // Wait until resources are available
            }
            processPrinting();
            System.out.println("Ticket printed by " + Thread.currentThread().getName() + " Ticket: " + ticket);
            noOfTicketsPrinted += 1;
            notifyAll(); // Notify all waiting threads

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrupted", e);
        }
    }

    @Override
    public synchronized int getPaperLevel() {
        return paperLevel;
    }

    @Override
    public synchronized int getTonerLevel() {
        return tonerLevel;
    }

    @Override
    public synchronized int getNoOfTicketsPrinted() {
        return noOfTicketsPrinted;
    }
}
