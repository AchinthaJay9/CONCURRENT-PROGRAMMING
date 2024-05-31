package lk.iit.level6.concurrent.assignment;

import java.math.BigDecimal;

public class Ticket {

    private final int ticketNumber;
    private final BigDecimal ticketPrice;

    private final PassengerInfo passengerInfo;

    private final TravelInfo locationInfo;


    public Ticket(int ticketNumber, BigDecimal ticketPrice, PassengerInfo passengerInfo, TravelInfo locationInfo) {
        this.ticketNumber = ticketNumber;
        this.ticketPrice = ticketPrice;
        this.passengerInfo = passengerInfo;
        this.locationInfo = locationInfo;
    }


    public int getTicketNumber() {
        return ticketNumber;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public PassengerInfo getPassengerInfo() {
        return passengerInfo;
    }

    public TravelInfo getLocationInfo() {
        return locationInfo;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketNumber=" + ticketNumber +
                ", ticketPrice=" + ticketPrice +
                ", passengerInfo=" + passengerInfo +
                ", locationInfo=" + locationInfo +
                '}';
    }
}
