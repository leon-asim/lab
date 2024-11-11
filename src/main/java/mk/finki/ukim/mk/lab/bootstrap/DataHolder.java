package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> eventList;

    @PostConstruct
    public void init() {
        eventList = new ArrayList<>();
        eventList.add(new Event("Dfest", "Dojran festival", 7.8));
        eventList.add(new Event("Brakja Manaki", "Bitola", 8.3));
        eventList.add(new Event("Ohrid Calling", "Ohrid", 6.9));
    }
}
