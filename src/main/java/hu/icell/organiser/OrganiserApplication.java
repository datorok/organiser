package hu.icell.organiser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Ez a main class felelős a program elindításáért. Nagyon fontos, hogy szerepeljen benne a
 * SpringBootApplication annotáció, ugyanis ez szükséges ahhoz, hogy az alkalmazás tényleg Spring alkalmazásként legyen kezelve
 */


@SpringBootApplication
public class OrganiserApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrganiserApplication.class, args);
    }
}
