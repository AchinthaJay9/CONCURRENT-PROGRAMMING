package lk.iit.level6.concurrent.assignment;

import java.util.Random;

public class TicketPaperTechnician implements Runnable {

    public static final int NUMBER_OF_RETRIES = 3;

    private final ServiceTicketMachine machine;
    private final String name;

    public TicketPaperTechnician(String name, ServiceTicketMachine machine) {
        this.name = name;
        this.machine = machine;
    }

    @Override
    public void run() {

        Random random = new Random();

        for (int i = 0; i < NUMBER_OF_RETRIES; i++) {

            if(machine.getPaperLevel() < TicketMachine.SHEETS_PER_PACK){
                machine.refillTicketPaper();
            }

            try {
                Thread.sleep(random.nextInt(100)+1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }



    }

    public String getName() {
        return name;
    }
}
