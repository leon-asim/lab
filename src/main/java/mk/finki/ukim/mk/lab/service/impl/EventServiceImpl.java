package mk.finki.ukim.mk.lab.service.impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.model.exception.EventNotFoundException;
import mk.finki.ukim.mk.lab.model.exception.LocationNotFoundException;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.repository.LocationRepository;
import mk.finki.ukim.mk.lab.repository.inmemory.InMemoryEventRepository;
import mk.finki.ukim.mk.lab.repository.inmemory.InMemoryLocationRepository;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return eventRepository.findByName(text);
    }

    @Override
    public List<Event> searchEventsByNameAndRating(String text, Double rating) {
        return eventRepository.findByNameAndPopularityScore(text, rating);
    }

    @Override
    public List<Event> searchEventsByRating(Double rating) {
        return eventRepository.findByPopularityScore(rating);
    }

    @Override
    public List<Event> findAllByLocation_Id(Long locationId) {
        return eventRepository.findAllByLocation_Id(locationId);
    }

    @Override
    public Optional<Event> save(String name, String description, Double rating, Long locationId) {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new LocationNotFoundException(locationId));

        return Optional.of(eventRepository.save(new Event(name, description, rating, location)));
    }

    @Override
    public Optional<Event> edit(Long id, String name, String description, Double rating, Long locationId) {
//        Location location = locationRepository.findById(locationId).orElseThrow(() -> new LocationNotFoundException(locationId));
//        eventRepository.deleteById(id);
//        return Optional.of(eventRepository.save(new Event(name, description, rating, location)));

        Location location = locationRepository.findById(locationId).orElseThrow(() -> new LocationNotFoundException(locationId));
        Event event = eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(id));

        event.setName(name);
        event.setDescription(description);
        event.setPopularityScore(rating);
        event.setLocation(location);

        return Optional.of(eventRepository.save(event));
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }
}
