package mk.finki.ukim.mk.lab.repository.inmemory;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryLocationRepository {
    public List<Location> findAll() {
        return DataHolder.locationList;
    }

    public Optional<Location> findById(Long id) {
        return DataHolder.locationList.stream().filter(i -> i.getId().equals(id)).findFirst();
    }
}