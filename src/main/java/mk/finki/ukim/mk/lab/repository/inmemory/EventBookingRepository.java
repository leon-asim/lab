package mk.finki.ukim.mk.lab.repository.inmemory;

import mk.finki.ukim.mk.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventBookingRepository {
    public static List<EventBooking> eventBookingList = new ArrayList<>();

    public EventBooking placeBooking(String eventName, String attendeeName, Long numberOfTickets) {
        EventBooking savedBooking = new EventBooking(eventName, attendeeName, numberOfTickets);
        eventBookingList.add(savedBooking);

        return savedBooking;
    }
    public List<EventBooking> getBookings() {
        return eventBookingList;
    }

    public List<EventBooking> getBookingsByName(String text) {
        return eventBookingList.stream().filter(i -> i.getEventName().contains(text)).collect(Collectors.toList());
    }
}
