package mk.finki.ukim.mk.lab.service.impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.repository.EventBookingRepository;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EventBookingServiceImpl implements EventBookingService {

    private final EventBookingRepository eventBookingRepository;

    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        return eventBookingRepository.placeBooking(eventName, attendeeName, attendeeAddress, numberOfTickets);
    }
}
