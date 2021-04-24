package lv.dita;

import lv.dita.repositories.ArtistRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GigLogApplication {

    public static void main(String[] args) {

                SpringApplication.run(GigLogApplication.class, args);

    }
}
