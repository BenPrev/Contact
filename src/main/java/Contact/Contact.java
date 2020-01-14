package Contact;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;

    @ManyToMany
    @JoinTable(name="lieuHabitations")
    private Collection<Adresse> lieuHabitation;



    public Contact(){};

    public Contact(String firstname, String lastname, Collection<Adresse> lieuHabitation){
        this.firstname=firstname;
        this.lastname=lastname;
        this.lieuHabitation = lieuHabitation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Collection<Adresse> getLieuHabitation() {
        return lieuHabitation;
    }

    public void setLieuHabitation(Collection<Adresse> lieuHabitation) {
        this.lieuHabitation = lieuHabitation;
    }


}
