package Contact;

import javax.persistence.*;

@Entity
public class Mail {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;
    private String Libelle;
    private String adresseMail;

    @ManyToOne
    @JoinColumn(name="contact_fk")
    private Contact contact;

    protected Mail(){}
    public Mail(String Libelle, String adresseMail, Contact contact){
        this.Libelle=Libelle;
        this.adresseMail=adresseMail;
        this.contact=contact;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String libelle) {
        Libelle = libelle;
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
