package contact.model;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface MailRepository extends CrudRepository<Mail, Long> {
    Collection<Mail> findByContact(Contact contact);
}

