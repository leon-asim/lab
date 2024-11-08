package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventRepository {
    public List<Event> findAll() {
        return DataHolder.eventList;
    }

    public List<Event> searchEvents(String text) {
        return DataHolder.eventList.stream()
                .filter(i -> i.getName().contains(text) || i.getDescription().contains(text))
                .collect(Collectors.toList());
    }

    public List<Event> searchEventsByNameAndRating(String text, Double rating) {
        return DataHolder.eventList.stream()
                .filter(i -> i.getName().contains(text) && i.getPopularityScore() >= rating)
                .collect(Collectors.toList());
    }

    public List<Event> searchEventsByRating(Double rating) {
        return DataHolder.eventList.stream()
                .filter(i -> i.getPopularityScore() >= rating)
                .collect(Collectors.toList());
    }
}
