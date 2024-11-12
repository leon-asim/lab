package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventBooking {
    String eventName;
    String attendeeName;
    String attendeeAddress;
    Long numberOfTickets;

    public EventBooking(String eventName, String attendeeName, Long numberOfTickets) {
        this.eventName = eventName;
        this.attendeeName = attendeeName;
        this.numberOfTickets = numberOfTickets;
    }
}
