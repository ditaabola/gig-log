package lv.dita;

import lv.dita.domain.Artist;
import lv.dita.domain.Manager;
import lv.dita.repositories.ArtistRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GigLogApplication {

    public static void main(String[] args) {

                SpringApplication.run(GigLogApplication.class, args);

    }

//    @Bean
//    public CommandLineRunner runner(ArtistRepository artistRepository){
//        return (args ->
//                artistRepository.save(new Artist(1l, "Juuk", "juuk@juuk.com"));
//    }
}
