package hr.algebra.test.model;

import jakarta.persistence.*;

@Entity
public class ProgramObrazovanja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int programObrazovanjaID;

    private String naziv;
    @Column(name = "CSVET")
    private int csvet;

    public ProgramObrazovanja() {
        this.csvet = csvet;
    }

    public int getProgramObrazovanjaID() {
        return programObrazovanjaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getCsvat() {
        return csvet;
    }

    public void setCsvat(int csvet) {
        this.csvet = csvet;
    }
}
