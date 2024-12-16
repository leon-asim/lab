package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text);
    List<Event> searchEventsByNameAndRating(String text, Double rating);
    List<Event> searchEventsByRating(Double rating);
    List<Event> findAllByLocation_Id(Long locationId);
    Optional<Event> save(String name, String description, Double rating, Long locationId, Long categoryId);
    Optional<Event> edit(Long id, String name, String description, Double rating, Long locationId, Long categoryId);
    void deleteById(Long id);
    Optional<Event> findById(Long id);
}
