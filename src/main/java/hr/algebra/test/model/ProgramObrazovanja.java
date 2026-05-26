package hr.algebra.test.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProgramObrazovanja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int programObrazovanjaID;

    private String naziv;
    private int csvat;

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
        return csvat;
    }

    public void setCsvat(int csvat) {
        this.csvat = csvat;
    }
}
