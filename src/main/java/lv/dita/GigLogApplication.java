package lv.dita;

import lv.dita.entity.Artist;
import lv.dita.entity.Gig;
import lv.dita.entity.Manager;
import lv.dita.entity.Venue;
import lv.dita.enums.GigType;
import lv.dita.enums.VenueType;
import lv.dita.service.ArtistService;
import lv.dita.service.GigService;
import lv.dita.service.ManagerService;
import lv.dita.service.VenueService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class GigLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(GigLogApplication.class, args);}

//        @Bean
//        public CommandLineRunner initialCreate(ArtistService artistService, GigService gigService, ManagerService managerService, VenueService venueService) {
//            return (args) -> {
//
//                Artist juuk = new Artist ("Juuk", "juuk@juuk.com");
//                Manager manager = new Manager("John", "Manager", "manager@manager.com");
//                Venue depo = new Venue("Depo", VenueType.ALTERNATIVE_CLUB, "Latvia", "Riga");
//                Venue instore = new Venue ("InStore", VenueType.PRIVATE_VENUE, "Romania", "Bucharest");
//                Gig firstGig = new Gig(LocalDate.of(2020, 11, 25), GigType.LIVE_CONCERT);
//                Gig secondGig = new Gig(LocalDate.of(2020, 11, 26), GigType.LIVE_CONCERT);
//
//                manager.addArtists(juuk);
////                firstGig.addArtists(juuk);
////                firstGig.addVenue(depo);
////                secondGig.addArtists(juuk);
////                secondGig.addVenue(instore);
//
//                artistService.createArtist(juuk);
//                managerService.createManager(manager);
//                venueService.createVenue(depo);
//                venueService.createVenue(instore);
//                gigService.createGig(firstGig);
//                gigService.createGig(secondGig);
//
//
//
//            };
//        }
}
