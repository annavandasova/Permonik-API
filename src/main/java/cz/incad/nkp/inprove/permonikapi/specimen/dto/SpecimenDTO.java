package cz.incad.nkp.inprove.permonikapi.specimen.dto;

import java.util.List;

public record SpecimenDTO (
    String id,
    String idIssue,
    String idMetaTitle,
    String barCode,
    Boolean numExists,
    Boolean numMissing,
    String signature,
    String owner,
    List<String> states,
    String state,
    String stateDescription,
    SpecimenPagesDTO pages,
    String note,
    String name,
    String subName,
    String publication,
    String mutation,
    String publicationMark,
    String publicationDate,
    String publicationDay,
    String periodicity,
    String number,
    String metaTitleName,
    Integer pagesCount,
    Boolean isAttachment
){ }
