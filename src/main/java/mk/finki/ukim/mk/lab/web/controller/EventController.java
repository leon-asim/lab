package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.service.EventService;
import mk.finki.ukim.mk.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final LocationService locationService;
    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error,
                                @RequestParam(required = false) String search,
                                @RequestParam(required = false) Double rating,
                                @RequestParam(required = false) Long location,
                                Model model) {

        List<Event> eventList;
        if(location != null){
            eventList = eventService.findAllByLocation_Id(location);
        } else if(search != null && rating != null && search != "") {
            eventList = eventService.searchEventsByNameAndRating(search, rating);
        } else if(search != null && search != "") {
            eventList = eventService.searchEvents(search);
        } else if(rating != null) {
            eventList = eventService.searchEventsByRating(rating);
        } else
            eventList = eventService.listAll();

        if(error != null) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Location> locationList = locationService.findAll();
        model.addAttribute("events", eventList);
        model.addAttribute("locations", locationList);
        return "listEvents";
    }


    @PostMapping("/add")
    public String saveEvent(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam String popularityScore,
                            @RequestParam String location) {
        eventService.save(name, description, Double.parseDouble(popularityScore), Long.parseLong(location));

        return "redirect:/events";
    }

    @PostMapping("/edit/{eventId}")
    public String editEvent(@PathVariable Long eventId,
                            @RequestParam String name,
                            @RequestParam String description,
                            @RequestParam Double popularityScore,
                            @RequestParam Long location) {
        eventService.edit(eventId, name, description, popularityScore, location);

        return "redirect:/events";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteById(id);

        return "redirect:/events";
    }

    @GetMapping("/add-form")
    public String getAddEventPage(Model model) {
        List<Location> locationList = locationService.findAll();
        model.addAttribute("locations", locationList);

        return "add-event";
    }
    @GetMapping("/edit-form/{id}")
    public String getEditEventForm(@PathVariable Long id, Model model) {
        if(eventService.findById(id).isPresent()) {
            Event event = eventService.findById(id).get();
            List<Location> locationList = locationService.findAll();
            model.addAttribute("event", event);
            model.addAttribute("locations", locationList);

            return "add-event";
        }
        return "redirect:/events?error=EventNotFound";
    }
}
