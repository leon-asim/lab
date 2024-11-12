package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.EventBooking;

import java.util.List;

public interface EventBookingService{
    EventBooking placeBooking(String eventName, String attendeeName, Long numberOfTickets);
    List<EventBooking> eventBookings();
    List<EventBooking> getBookingsByName(String text);
}