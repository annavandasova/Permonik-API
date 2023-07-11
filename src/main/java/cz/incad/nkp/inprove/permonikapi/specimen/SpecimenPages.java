package cz.incad.nkp.inprove.permonikapi.specimen;

import java.util.List;
import java.util.Objects;

public class SpecimenPages {

    private List<String> damaged;
    private List<String> missing;

    public SpecimenPages() {
    }

    public SpecimenPages(List<String> damaged, List<String> missing) {
        this.damaged = damaged;
        this.missing = missing;
    }

    public List<String> getDamaged() {
        return damaged;
    }

    public void setDamaged(List<String> damaged) {
        this.damaged = damaged;
    }

    public List<String> getMissing() {
        return missing;
    }

    public void setMissing(List<String> missing) {
        this.missing = missing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecimenPages that = (SpecimenPages) o;
        return Objects.equals(damaged, that.damaged) && Objects.equals(missing, that.missing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(damaged, missing);
    }

    @Override
    public String toString() {
        return "SpecimenPages{" +
                "damaged=" + damaged +
                ", missing=" + missing +
                '}';
    }
}
