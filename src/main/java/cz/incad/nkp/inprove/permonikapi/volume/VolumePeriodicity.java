package cz.incad.nkp.inprove.permonikapi.volume;

import java.util.Objects;

public class VolumePeriodicity {

    private Boolean active;
    private String vydani;
    private String den;
    private Integer pocet_stran;
    private String nazev;
    private String podnazev;


    public VolumePeriodicity() {
    }

    public VolumePeriodicity(Boolean active, String vydani, String den, Integer pocet_stran, String nazev, String podnazev) {
        this.active = active;
        this.vydani = vydani;
        this.den = den;
        this.pocet_stran = pocet_stran;
        this.nazev = nazev;
        this.podnazev = podnazev;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getVydani() {
        return vydani;
    }

    public void setVydani(String vydani) {
        this.vydani = vydani;
    }

    public String getDen() {
        return den;
    }

    public void setDen(String den) {
        this.den = den;
    }

    public Integer getPocet_stran() {
        return pocet_stran;
    }

    public void setPocet_stran(Integer pocet_stran) {
        this.pocet_stran = pocet_stran;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getPodnazev() {
        return podnazev;
    }

    public void setPodnazev(String podnazev) {
        this.podnazev = podnazev;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VolumePeriodicity that = (VolumePeriodicity) o;
        return Objects.equals(active, that.active) && Objects.equals(vydani, that.vydani) && Objects.equals(den, that.den) && Objects.equals(pocet_stran, that.pocet_stran) && Objects.equals(nazev, that.nazev) && Objects.equals(podnazev, that.podnazev);
    }

    @Override
    public int hashCode() {
        return Objects.hash(active, vydani, den, pocet_stran, nazev, podnazev);
    }

    @Override
    public String toString() {
        return "VolumePeriodicity{" +
                "active=" + active +
                ", vydani='" + vydani + '\'' +
                ", den='" + den + '\'' +
                ", pocet_stran=" + pocet_stran +
                ", nazev='" + nazev + '\'' +
                ", podnazev='" + podnazev + '\'' +
                '}';
    }
}
