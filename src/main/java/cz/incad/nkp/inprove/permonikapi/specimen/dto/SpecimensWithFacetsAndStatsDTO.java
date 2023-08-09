package cz.incad.nkp.inprove.permonikapi.specimen.dto;


import java.util.List;

public record SpecimensWithFacetsAndStatsDTO (
        Object publicationDayMin,
        Object publicationDayMax,
        Object numberMin,
        Object numberMax,
        Object pagesCount,
        List<FacetFieldDTO> mutations,
        List<FacetFieldDTO> publicationMark,
        List<FacetFieldDTO> publication,
        List<FacetFieldDTO> states,
        List<FacetFieldDTO> publicationDayRanges,
        List<SpecimenDTO> specimens
) { }
