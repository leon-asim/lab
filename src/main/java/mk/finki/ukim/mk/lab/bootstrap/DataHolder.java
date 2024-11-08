package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Event;

import java.util.ArrayList;
import java.util.List;

public class DataHolder {
    public static List<Event> eventList = new ArrayList<>();

    @PostConstruct
    public void init() {
        double a=7.8;
        eventList.add(new Event("Dfest", "Dojran festival", 7.8));
        eventList.add(new Event("Brakja Manaki", "Bitola", 8.3));
        eventList.add(new Event("Ohrid Calling", "Ohrid", 6.9));
    }
}
