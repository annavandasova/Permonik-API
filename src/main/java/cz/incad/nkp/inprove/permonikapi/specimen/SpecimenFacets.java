package cz.incad.nkp.inprove.permonikapi.specimen;

import java.util.List;
import java.util.Objects;

public class SpecimenFacets {

    private Integer dateStart;
    private Integer dateEnd;
    private String calendarDateStart;
    private String calendarDateEnd;
    private List<String> names;
    private List<String> mutations;
    private List<String> publications;
    private List<String> publicationMarks;
    private List<String> owners;
    private List<String> states;
    private String volume;

    public SpecimenFacets() {
    }

    public SpecimenFacets(Integer dateStart, Integer dateEnd, String calendarDateStart, String calendarDateEnd, List<String> names, List<String> mutations, List<String> publications, List<String> publicationMarks, List<String> owners, List<String> states, String volume) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.calendarDateStart = calendarDateStart;
        this.calendarDateEnd = calendarDateEnd;
        this.names = names;
        this.mutations = mutations;
        this.publications = publications;
        this.publicationMarks = publicationMarks;
        this.owners = owners;
        this.states = states;
        this.volume = volume;
    }

    public Integer getDateStart() {
        return dateStart;
    }

    public void setDateStart(Integer dateStart) {
        this.dateStart = dateStart;
    }

    public Integer getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Integer dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getCalendarDateStart() {
        return calendarDateStart;
    }

    public void setCalendarDateStart(String calendarDateStart) {
        this.calendarDateStart = calendarDateStart;
    }


    public String getCalendarDateEnd() {
        return calendarDateEnd;
    }

    public void setCalendarDateEnd(String calendarDateEnd) {
        this.calendarDateEnd = calendarDateEnd;
    }
    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<String> getMutations() {
        return mutations;
    }

    public void setMutations(List<String> mutations) {
        this.mutations = mutations;
    }

    public List<String> getPublications() {
        return publications;
    }

    public void setPublications(List<String> publications) {
        this.publications = publications;
    }

    public List<String> getPublicationMarks() {
        return publicationMarks;
    }

    public void setPublicationMarks(List<String> publicationMarks) {
        this.publicationMarks = publicationMarks;
    }

    public List<String> getOwners() {
        return owners;
    }

    public void setOwners(List<String> owners) {
        this.owners = owners;
    }

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecimenFacets that = (SpecimenFacets) o;
        return Objects.equals(dateStart, that.dateStart) && Objects.equals(dateEnd, that.dateEnd) && Objects.equals(calendarDateStart, that.calendarDateStart) && Objects.equals(calendarDateEnd, that.calendarDateEnd) && Objects.equals(names, that.names) && Objects.equals(mutations, that.mutations) && Objects.equals(publications, that.publications) && Objects.equals(publicationMarks, that.publicationMarks) && Objects.equals(owners, that.owners) && Objects.equals(states, that.states) && Objects.equals(volume, that.volume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateStart, dateEnd, calendarDateStart, calendarDateEnd, names, mutations, publications, publicationMarks, owners, states, volume);
    }

    @Override
    public String toString() {
        return "SpecimenFacets{" +
                "dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", calendarDateStart='" + calendarDateStart + '\'' +
                ", calendarDateEndt='" + calendarDateEnd + '\'' +
                ", names=" + names +
                ", mutations=" + mutations +
                ", publications=" + publications +
                ", publicationMarks=" + publicationMarks +
                ", owners=" + owners +
                ", states=" + states +
                ", volume='" + volume + '\'' +
                '}';
    }
}
