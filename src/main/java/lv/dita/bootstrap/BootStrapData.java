package lv.dita.bootstrap;

import lv.dita.entity.Artist;
import lv.dita.entity.Gig;
import lv.dita.entity.Manager;
import lv.dita.entity.Venue;
import lv.dita.enums.GigType;
import lv.dita.enums.VenueType;
import lv.dita.repositories.ArtistRepository;
import lv.dita.repositories.GigRepository;
import lv.dita.repositories.ManagerRepository;
import lv.dita.repositories.VenueRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BootStrapData implements CommandLineRunner {

    private final ArtistRepository artistRepository;
    private final ManagerRepository managerRepository;
    private final GigRepository gigRepository;
    private final VenueRepository venueRepository;

    public BootStrapData(ArtistRepository artistRepository, ManagerRepository managerRepository, GigRepository gigRepository, VenueRepository venueRepository) {
        this.artistRepository = artistRepository;
        this.managerRepository = managerRepository;
        this.gigRepository = gigRepository;
        this.venueRepository = venueRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Artist juuk = new Artist(1l,"Juuk", "juuk@juuk.com");
        Artist manta = new Artist(2l, "Manta", "manta@manta.com");
        Manager manager1 = new Manager (1L, "John", "Manager", "john@manager.com");
        Manager manager2= new Manager (2L, "Jane", "Jamanerr", "jane@manager.com");
        Venue depo = new Venue(1L, "Depo", VenueType.ALTERNATIVE_CLUB, "Latvia", "Riga");
        Venue instore = new Venue (2l,"InStore", VenueType.PRIVATE_VENUE, "Romania", "Bucharest");
        Gig firstGig = new Gig(1l, LocalDate.of(2020, 11, 25), GigType.LIVE_CONCERT);


        //firstGig.addArtists(juuk);
        //depo.addGig(firstGig);

//        depo.getGigs().add(firstGig);
//        firstGig.getArtists().add(juuk);
//        firstGig.getVenues().add(depo);

        artistRepository.save(juuk);

        artistRepository.save(manta);
        managerRepository.save(manager1);
        managerRepository.save(manager2);
        venueRepository.save(depo);
        venueRepository.save(instore);
        gigRepository.save(firstGig);

    }
}
