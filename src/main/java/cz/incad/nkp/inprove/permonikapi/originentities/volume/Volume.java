package cz.incad.nkp.inprove.permonikapi.originentities.volume;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.Objects;

import static cz.incad.nkp.inprove.permonikapi.originentities.volume.VolumeDefinition.VOLUME_CORE_NAME;

@SolrDocument(collection = VOLUME_CORE_NAME)
public class Volume implements VolumeDefinition{


    @Id @Indexed(value = ID_FIELD, required = true)
    private String id;

    @Indexed(BAR_CODE_FIELD)
    private String barCode;

    @Indexed(DATE_FROM_FIELD)
    private String dateFrom;

    @Indexed(DATE_TO_FIELD)
    private String dateTo;

    @Indexed(META_TITLE_ID_FIELD)
    private String metaTitleId;

    @Indexed(MUTATION_FIELD)
    private String mutation;

    @Indexed(PERIODICITY_FIELD)
    private String periodicity;

    @Indexed(PAGES_COUNT_FIELD)
    private Integer pagesCount;

    @Indexed(FIRST_NUMBER_FIELD)
    private String firstNumber;

    @Indexed(LAST_NUMBER_FIELD)
    private String lastNumber;

    @Indexed(NOTE_FIELD)
    private String note;

    @Indexed(SHOW_ATTACHMENTS_AT_THE_END_FIELD)
    private Boolean showAttachmentsAtTheEnd;

    @Indexed(SIGNATURE_FIELD)
    private String signature;

    @Indexed(OWNER_FIELD)
    private String owner;

    @Indexed(YEAR_FIELD)
    private String year;

    @Indexed(PUBLICATION_MARK_FIELD)
    private String publicationMark;

    public Volume() {
    }

    public Volume(String id, String barCode, String dateFrom, String dateTo, String metaTitleId, String mutation, String periodicity, Integer pagesCount, String firstNumber, String lastNumber, String note, Boolean showAttachmentsAtTheEnd, String signature, String owner, String year, String publicationMark) {
        this.id = id;
        this.barCode = barCode;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.metaTitleId = metaTitleId;
        this.mutation = mutation;
        this.periodicity = periodicity;
        this.pagesCount = pagesCount;
        this.firstNumber = firstNumber;
        this.lastNumber = lastNumber;
        this.note = note;
        this.showAttachmentsAtTheEnd = showAttachmentsAtTheEnd;
        this.signature = signature;
        this.owner = owner;
        this.year = year;
        this.publicationMark = publicationMark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getMetaTitleId() {
        return metaTitleId;
    }

    public void setMetaTitleId(String metaTitleId) {
        this.metaTitleId = metaTitleId;
    }

    public String getMutation() {
        return mutation;
    }

    public void setMutation(String mutation) {
        this.mutation = mutation;
    }

    public String getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(String periodicity) {
        this.periodicity = periodicity;
    }

    public Integer getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(Integer pagesCount) {
        this.pagesCount = pagesCount;
    }

    public String getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(String firstNumber) {
        this.firstNumber = firstNumber;
    }

    public String getLastNumber() {
        return lastNumber;
    }

    public void setLastNumber(String lastNumber) {
        this.lastNumber = lastNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getShowAttachmentsAtTheEnd() {
        return showAttachmentsAtTheEnd;
    }

    public void setShowAttachmentsAtTheEnd(Boolean showAttachmentsAtTheEnd) {
        this.showAttachmentsAtTheEnd = showAttachmentsAtTheEnd;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPublicationMark() {
        return publicationMark;
    }

    public void setPublicationMark(String publicationMark) {
        this.publicationMark = publicationMark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volume volume = (Volume) o;
        return Objects.equals(id, volume.id) && Objects.equals(barCode, volume.barCode) && Objects.equals(dateFrom, volume.dateFrom) && Objects.equals(dateTo, volume.dateTo) && Objects.equals(metaTitleId, volume.metaTitleId) && Objects.equals(mutation, volume.mutation) && Objects.equals(periodicity, volume.periodicity) && Objects.equals(pagesCount, volume.pagesCount) && Objects.equals(firstNumber, volume.firstNumber) && Objects.equals(lastNumber, volume.lastNumber) && Objects.equals(note, volume.note) && Objects.equals(showAttachmentsAtTheEnd, volume.showAttachmentsAtTheEnd) && Objects.equals(signature, volume.signature) && Objects.equals(owner, volume.owner) && Objects.equals(year, volume.year) && Objects.equals(publicationMark, volume.publicationMark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, barCode, dateFrom, dateTo, metaTitleId, mutation, periodicity, pagesCount, firstNumber, lastNumber, note, showAttachmentsAtTheEnd, signature, owner, year, publicationMark);
    }

    @Override
    public String toString() {
        return "Volume{" +
                "id='" + id + '\'' +
                ", barCode='" + barCode + '\'' +
                ", dateFrom='" + dateFrom + '\'' +
                ", dateTo='" + dateTo + '\'' +
                ", metaTitleId='" + metaTitleId + '\'' +
                ", mutation='" + mutation + '\'' +
                ", periodicity='" + periodicity + '\'' +
                ", pagesCount=" + pagesCount +
                ", firstNumber='" + firstNumber + '\'' +
                ", lastNumber='" + lastNumber + '\'' +
                ", note='" + note + '\'' +
                ", showAttachmentsAtTheEnd=" + showAttachmentsAtTheEnd +
                ", signature='" + signature + '\'' +
                ", owner='" + owner + '\'' +
                ", year='" + year + '\'' +
                ", publicationMark='" + publicationMark + '\'' +
                '}';
    }
}
