package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("eventBooking")
public class EventBookingController {
    private final EventBookingService eventBookingService;

    public EventBookingController(EventBookingService eventBookingService) {
        this.eventBookingService = eventBookingService;
    }

    @GetMapping("/reserve")
    public String makeReservation(@RequestParam(required = false) String eventName,
                                  @RequestParam String userName,
                                  @RequestParam Long numTickets,
                                  Model model) {

        eventBookingService.placeBooking(eventName, userName, numTickets);

        model.addAttribute("eventName", eventName);
        model.addAttribute("user", userName);
        model.addAttribute("tickets", numTickets);

        return "bookingConfirmation";
    }

    @GetMapping("/allBookings")
    public String getAllBookingsView(@RequestParam(required = false) String searchText, Model model) {
        List<EventBooking> eventBookings;

        if(searchText != null && searchText != "") {
            eventBookings = eventBookingService.getBookingsByName(searchText);
        } else
            eventBookings = eventBookingService.eventBookings();

        model.addAttribute("bookings", eventBookings);

        return "listBooking";
    }
}
