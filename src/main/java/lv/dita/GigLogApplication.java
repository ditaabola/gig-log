package lv.dita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class GigLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(GigLogApplication.class, args);}
//
//        @Bean
//        public CommandLineRunner initialCreate(ManagerService managerService, ArtistService artistService) {
//            return (args) -> {
//
//                Artist juuk = new Artist ("Juuk", "juuk@juuk.com");
//                Artist manta = new Artist ("Manta", "manta@manta.com");
//                Manager manager = new Manager("John", "Manager", "manager@manager.com");
//                Venue depo = new Venue("Depo", VenueType.ALTERNATIVE_CLUB, "Latvia", "Riga");
//                Venue instore = new Venue ("InStore", VenueType.PRIVATE_VENUE, "Romania", "Bucharest");
//                Gig firstGig = new Gig(LocalDate.of(2020, 11, 25), GigType.LIVE_CONCERT);
//                Gig secondGig = new Gig(LocalDate.of(2020, 11, 26), GigType.LIVE_CONCERT);
//
  //              juuk.addManagers(manager);
//                manta.addManagers(manager);
////                firstGig.addArtists(juuk);
////                firstGig.addVenue(depo);
////                secondGig.addArtists(juuk);
////                secondGig.addVenue(instore);
//////
//                artistService.createArtist(juuk);
//                artistService.createArtist(manta);
//                managerService.createManager(manager);
//                venueService.createVenue(depo);
//                venueService.createVenue(instore);
//                gigService.createGig(firstGig);
//                gigService.createGig(secondGig);
//
//
//
            };
  //      }
//}
