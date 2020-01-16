package contact.model;

import javax.persistence.*;

@Entity
public class Mail {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;
    private String adresseMail;

    @ManyToOne
    @JoinColumn(name="contact_fk")
    private Contact contact;

    protected Mail(){}
    public Mail(String adresseMail, Contact contact){
        this.adresseMail=adresseMail;
        this.contact = contact;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
