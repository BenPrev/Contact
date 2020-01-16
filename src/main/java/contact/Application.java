package contact;

import contact.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Collection;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(ContactRepository repository, MailRepository mRepository, AdresseRepository aRepository) {
        return (args) -> {
            // save a few customers
            Adresse ad1 = new Adresse("rue de la zekrj", 3, 34234, "Reims");
            Adresse ad3 = new Adresse("rue de la zekdsfsrj", 1, 34254, "Paris");
            Adresse ad2 = new Adresse("rue de la zekqsdfqsdfsqrj", 2, 35634, "Laon");
            aRepository.save(ad1);
            aRepository.save(ad2);
            aRepository.save(ad3);
            Collection<Adresse> adresse1 = Arrays.asList(ad1,ad2,ad3);
            Contact contact1 = new Contact("Jack", "Bauer",adresse1);
            Contact contact2 = new Contact("Bertha", "Maure",adresse1);
            repository.save(contact1);
            repository.save(contact2);

            Mail m1 = new Mail("toto@bidon.fr",contact1);
            Mail m2 = new Mail("titi@bidon.fr",contact2);
            Mail m3 = new Mail("tata@bidon.fr",contact2);
            mRepository.save(m1);
            mRepository.save(m2);
            mRepository.save(m3);
        };
    }

}