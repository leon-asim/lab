package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventRepository {
    public static List<Event> eventList = new ArrayList<>();

    @PostConstruct
    public void init() {
        double a=7.8;
        eventList.add(new Event("Dfest", "Dojran festival", 7.8));
        eventList.add(new Event("Brakja Manaki", "Bitola", 8.3));
        eventList.add(new Event("Ohrid Calling", "Ohrid", 6.9));
    }
    public List<Event> findAll() {
        return eventList;
    }

    public List<Event> searchEvents(String text) {
        return eventList.stream()
                .filter(i -> i.getName().contains(text) || i.getDescription().contains(text))
                .collect(Collectors.toList());
    }

    public List<Event> searchEventsByNameAndRating(String text, Double rating) {
        return eventList.stream()
                .filter(i -> i.getName().contains(text) && i.getPopularityScore() >= rating)
                .collect(Collectors.toList());
    }

    public List<Event> searchEventsByRating(Double rating) {
        return eventList.stream()
                .filter(i -> i.getPopularityScore() >= rating)
                .collect(Collectors.toList());
    }
}
