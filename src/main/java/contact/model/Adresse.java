package contact.model;

import javax.persistence.*;

@Entity
public class Adresse {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String rue;
    private int NDeRue;
    private int CP;
    private String ville;


    protected Adresse(){}

    public Adresse(String rue, int NDeRue, int CP, String ville){
        this.rue = rue;
        this.NDeRue=NDeRue;
        this.CP=CP;
        this.ville=ville;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getNDeRue() {
        return NDeRue;
    }

    public void setNDeRue(int NDeRue) {
        this.NDeRue = NDeRue;
    }

    public int getCP() {
        return CP;
    }

    public void setCP(int CP) {
        this.CP = CP;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString(){
        return "" + this.NDeRue + " " + this.rue +", "+ this.ville + " " + this.CP;
    }

}
