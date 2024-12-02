package mk.finki.ukim.mk.lab.repository.inmemory;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryEventRepository {
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

    public Optional<Event> save(String name, String description, Double rating, Location location) {
        if (location == null) {
            throw new IllegalArgumentException();
        }
        DataHolder.eventList.removeIf(i -> i.getName().equals(name));
        Event event = new Event(name, description, rating, location);
        DataHolder.eventList.add(event);

        return Optional.of(event);
    }

    public Optional<Event> edit(Long id, String name, String description, Double rating, Location location) {
        if (location == null) {
            throw new IllegalArgumentException();
        }
//        DataHolder.eventList.removeIf(i -> i.getId().equals(id));
//        Event event = new Event(name, description, rating, location);
//        DataHolder.eventList.add(event);

        Event editedEvent = DataHolder.eventList.stream().filter(i -> i.getId().equals(id)).findFirst().orElseThrow();
        editedEvent.setName(name);
        editedEvent.setDescription(description);
        editedEvent.setPopularityScore(rating);
        editedEvent.setLocation(location);

        return Optional.of(editedEvent);
    }

    public void deleteById(Long id) {
        DataHolder.eventList.removeIf(i -> i.getId().equals(id));
    }

    public Optional<Event> findById(Long id) {
        return DataHolder.eventList.stream().filter(i -> i.getId().equals(id)).findFirst();
    }


}
