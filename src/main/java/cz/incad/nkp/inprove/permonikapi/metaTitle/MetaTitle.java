package cz.incad.nkp.inprove.permonikapi.metaTitle;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.Objects;

@SolrDocument(collection = "titul")
public class MetaTitle implements MetaTitleDefinition {

    @Id @Indexed(ID_FIELD)
    private String id;

    @Field(NAME_FIELD)
    private String name;

    @Indexed(PERIODICITY_FIELD)
    private String periodicity;

    @Field(NOTE_FIELD)
    private String note;

    @Indexed(SHOW_TO_NOT_LOGGED_USERS_FIELD)
    private Boolean showToNotLoggedUsers;


    public MetaTitle(String id, String name, String periodicity, String note, Boolean showToNotLoggedUsers) {
        this.id = id;
        this.name = name;
        this.periodicity = periodicity;
        this.note = note;
        this.showToNotLoggedUsers = showToNotLoggedUsers;
    }

    public MetaTitle() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(String periodicity) {
        this.periodicity = periodicity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getShowToNotLoggedUsers() {
        return showToNotLoggedUsers;
    }

    public void setShowToNotLoggedUsers(Boolean showToNotLoggedUsers) {
        this.showToNotLoggedUsers = showToNotLoggedUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetaTitle metaTitle = (MetaTitle) o;
        return Objects.equals(id, metaTitle.id) && Objects.equals(name, metaTitle.name) && Objects.equals(periodicity, metaTitle.periodicity) && Objects.equals(note, metaTitle.note) && Objects.equals(showToNotLoggedUsers, metaTitle.showToNotLoggedUsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, periodicity, note, showToNotLoggedUsers);
    }

    @Override
    public String toString() {
        return "MetaTitle{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", periodicity='" + periodicity + '\'' +
                ", note='" + note + '\'' +
                ", showToNotLoggedUsers=" + showToNotLoggedUsers +
                '}';
    }
}
