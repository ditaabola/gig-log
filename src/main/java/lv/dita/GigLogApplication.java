package lv.dita;

import lv.dita.models.Artist;
import lv.dita.models.Gig;
import lv.dita.models.Manager;
import lv.dita.models.Venue;
import lv.dita.service.GigService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
public class GigLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(GigLogApplication.class, args);

    }

    @Bean
    public CommandLineRunner initialCreate(GigService gigService) {
        return (args) -> {

            Gig gig = new Gig("Test venue name", "Test date", "Test type");
            Artist artist = new Artist("Test artist name", "Test artist contact email");
            Manager user = new Manager("Test manager name", "Test manager surname", "Test manager email");
            Venue venue = new Venue("Test venue name", "Test venue type", "Test venue Country", "Test venue city");

            gig.addArtists(artist);
            gig.addVenue(venue);


        };
    }
}
