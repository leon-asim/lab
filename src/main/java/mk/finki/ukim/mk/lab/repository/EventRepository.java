package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByName(String text);
    List<Event> findByNameAndPopularityScore(String name, Double popularityScore);
    List<Event> findByPopularityScore(Double popularityScore);
    List<Event> findAllByLocation_Id(Long locationId);
}
