package cz.incad.nkp.inprove.permonikapi.specimen;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.List;
import java.util.Objects;

@SolrDocument(collection = "exemplar")
public class Specimen implements SpecimenDefinition{

    @Id @Indexed(ID_FIELD)
    private String id;

    @Indexed(ID_ISSUE_FIELD)
    private String idIssue;

    @Indexed(ID_META_TITLE_FIELD)
    private String idMetaTitle;

    @Indexed(BAR_CODE_FIELD)
    private String barCode;

    @Indexed(NUM_EXISTS_FIELD)
    private Boolean numExists;

    @Indexed(NUM_MISSING_FIELD)
    private Boolean numMissing;

    @Indexed(SIGNATURE_FIELD)
    private String signature;

    @Indexed(OWNER_FIELD)
    private String owner;

    @Indexed(STATES_FIELD)
    private List<String> states;

    @Indexed(STATE_DESCRIPTION_FIELD)
    private String stateDescription;

    @Indexed(PAGES_FIELD)
    private String pages;

    @Indexed(NOTE_FIELD)
    private String note;

    @Indexed(NAME_FIELD)
    private String name;

    @Indexed(SUB_NAME_FIELD)
    private String subName;

    @Indexed(PUBLICATION_FIELD)
    private String publication;

    @Indexed(MUTATION_FIELD)
    private String mutation;

    @Indexed(PUBLICATION_MARK_FIELD)
    private String publicationMark;

    @Indexed(PUBLICATION_DATE_FIELD)
    private String publicationDate;

    @Indexed(PUBLICATION_DAY_FIELD)
    private String publicationDay;

    @Indexed(PERIODICITY_FIELD)
    private String periodicity;

    @Indexed(NUMBER_FIELD)
    private String number;

    @Indexed(META_TITLE_NAME_FIELD)
    private String metaTitleName;

    @Indexed(PAGES_COUNT_FIELD)
    private Integer pagesCount;

    @Indexed(IS_ATTACHMENT_FIELD)
    private Boolean isAttachment;

    public Specimen(String id, String idIssue, String idMetaTitle, String barCode, Boolean numExists, Boolean numMissing, String signature, String owner, List<String> states, String stateDescription, String pages, String note, String name, String subName, String publication, String mutation, String publicationMark, String publicationDate, String publicationDay, String periodicity, String number, String metaTitleName, Integer pagesCount, Boolean isAttachment) {
        this.id = id;
        this.idIssue = idIssue;
        this.idMetaTitle = idMetaTitle;
        this.barCode = barCode;
        this.numExists = numExists;
        this.numMissing = numMissing;
        this.signature = signature;
        this.owner = owner;
        this.states = states;
        this.stateDescription = stateDescription;
        this.pages = pages;
        this.note = note;
        this.name = name;
        this.subName = subName;
        this.publication = publication;
        this.mutation = mutation;
        this.publicationMark = publicationMark;
        this.publicationDate = publicationDate;
        this.publicationDay = publicationDay;
        this.periodicity = periodicity;
        this.number = number;
        this.metaTitleName = metaTitleName;
        this.pagesCount = pagesCount;
        this.isAttachment = isAttachment;
    }

    public Specimen() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdIssue() {
        return idIssue;
    }

    public void setIdIssue(String idIssue) {
        this.idIssue = idIssue;
    }

    public String getIdMetaTitle() {
        return idMetaTitle;
    }

    public void setIdMetaTitle(String idMetaTitle) {
        this.idMetaTitle = idMetaTitle;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Boolean getNumExists() {
        return numExists;
    }

    public void setNumExists(Boolean numExists) {
        this.numExists = numExists;
    }

    public Boolean getNumMissing() {
        return numMissing;
    }

    public void setNumMissing(Boolean numMissing) {
        this.numMissing = numMissing;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }

    public String getStateDescription() {
        return stateDescription;
    }

    public void setStateDescription(String stateDescription) {
        this.stateDescription = stateDescription;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getMutation() {
        return mutation;
    }

    public void setMutation(String mutation) {
        this.mutation = mutation;
    }

    public String getPublicationMark() {
        return publicationMark;
    }

    public void setPublicationMark(String publicationMark) {
        this.publicationMark = publicationMark;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getPublicationDay() {
        return publicationDay;
    }

    public void setPublicationDay(String publicationDay) {
        this.publicationDay = publicationDay;
    }

    public String getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(String periodicity) {
        this.periodicity = periodicity;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMetaTitleName() {
        return metaTitleName;
    }

    public void setMetaTitleName(String metaTitleName) {
        this.metaTitleName = metaTitleName;
    }

    public Integer getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(Integer pagesCount) {
        this.pagesCount = pagesCount;
    }

    public Boolean getAttachment() {
        return isAttachment;
    }

    public void setAttachment(Boolean attachment) {
        isAttachment = attachment;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specimen specimen = (Specimen) o;
        return Objects.equals(id, specimen.id) && Objects.equals(idIssue, specimen.idIssue) && Objects.equals(idMetaTitle, specimen.idMetaTitle) && Objects.equals(barCode, specimen.barCode) && Objects.equals(numExists, specimen.numExists) && Objects.equals(numMissing, specimen.numMissing) && Objects.equals(signature, specimen.signature) && Objects.equals(owner, specimen.owner) && Objects.equals(states, specimen.states) && Objects.equals(stateDescription, specimen.stateDescription) && Objects.equals(pages, specimen.pages) && Objects.equals(note, specimen.note) && Objects.equals(name, specimen.name) && Objects.equals(subName, specimen.subName) && Objects.equals(publication, specimen.publication) && Objects.equals(mutation, specimen.mutation) && Objects.equals(publicationMark, specimen.publicationMark) && Objects.equals(publicationDate, specimen.publicationDate) && Objects.equals(publicationDay, specimen.publicationDay) && Objects.equals(periodicity, specimen.periodicity) && Objects.equals(number, specimen.number) && Objects.equals(metaTitleName, specimen.metaTitleName) && Objects.equals(pagesCount, specimen.pagesCount) && Objects.equals(isAttachment, specimen.isAttachment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idIssue, idMetaTitle, barCode, numExists, numMissing, signature, owner, states, stateDescription, pages, note, name, subName, publication, mutation, publicationMark, publicationDate, publicationDay, periodicity, number, metaTitleName, pagesCount, isAttachment);
    }


    @Override
    public String toString() {
        return "Specimen{" +
                "id='" + id + '\'' +
                ", idIssue='" + idIssue + '\'' +
                ", idMetaTitle='" + idMetaTitle + '\'' +
                ", barCode='" + barCode + '\'' +
                ", numExists=" + numExists +
                ", numMissing=" + numMissing +
                ", signature='" + signature + '\'' +
                ", owner='" + owner + '\'' +
                ", states=" + states +
                ", stateDescription='" + stateDescription + '\'' +
                ", pages='" + pages + '\'' +
                ", note='" + note + '\'' +
                ", name='" + name + '\'' +
                ", subName='" + subName + '\'' +
                ", publication='" + publication + '\'' +
                ", mutation='" + mutation + '\'' +
                ", releaseMark='" + publicationMark + '\'' +
                ", publicationDate=" + publicationDate +
                ", publicationDay='" + publicationDay + '\'' +
                ", periodicity='" + periodicity + '\'' +
                ", number='" + number + '\'' +
                ", metaTitleName='" + metaTitleName + '\'' +
                ", pagesCount=" + pagesCount +
                ", isAttachment=" + isAttachment +
                '}';
    }
}
