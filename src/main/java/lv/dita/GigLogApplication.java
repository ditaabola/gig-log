package lv.dita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class GigLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(GigLogApplication.class, args);
    }
}
