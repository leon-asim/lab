package mk.finki.ukim.mk.lab.service.impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.repository.EventBookingRepository;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EventBookingServiceImpl implements EventBookingService {

    private final EventBookingRepository eventBookingRepository;

    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, String numberOfTickets) {
        int num = 0;
        if(Integer.getInteger(numberOfTickets) != null) {
            num = Integer.getInteger(numberOfTickets);
        }

        return eventBookingRepository.placeBooking(eventName, attendeeName, attendeeAddress, num);
    }

    @Override
    public List<EventBooking> eventBookings() {
        return eventBookingRepository.getBookings();
    }

    @Override
    public List<EventBooking> getBookingsByName(String text) {
        return eventBookingRepository.getBookingsByName(text);
    }
}
