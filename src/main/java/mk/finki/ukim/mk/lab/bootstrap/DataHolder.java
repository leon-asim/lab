package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> eventList = new ArrayList<>();
    public static List<Location> locationList = new ArrayList<>();

    @PostConstruct
    public void init() {
        Location borisTrajkovski = new Location("Boris Trajkovski", "Skopje", "6000", "Arena");
        Location domNaKultura = new Location("Dom na kultura", "Bitola", "1000", "kulturen centar");
        Location mkc = new Location("MKC", "Skopje", "3000", "kulturen centar");
        Location neta = new Location("Netaville", "Skopje", "100", "coworking space");
        Location trchajBe =new Location("Trchaj Be Route", "Bitola", "10000", "Streets in BT");

        locationList.add(borisTrajkovski);
        locationList.add(domNaKultura);
        locationList.add(mkc);
        locationList.add(neta);
        locationList.add(trchajBe);

        eventList.add(new Event("Taksirat", "Music", 7.9, borisTrajkovski));
        eventList.add(new Event("Brakja Manaki", "Film", 8.3, domNaKultura));
        eventList.add(new Event("Trcaj Be", "Race", 9.0, trchajBe));


    }
}
