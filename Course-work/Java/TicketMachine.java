package lk.iit.level6.concurrent.assignment;

public interface TicketMachine {

     int FULL_PAPER_TRAY = 20;//250
     int SHEETS_PER_PACK = 5;//50
     int LOW_PAPER_THRESHOLD = 0;//100



     int FULL_TONER_LEVEL = 15;//500
     int MINIMUM_TONER_LEVEL = 10;

     void printTicket(Ticket ticket);
}
