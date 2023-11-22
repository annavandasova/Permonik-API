package cz.incad.nkp.inprove.permonikapi.originentities.volume.dto;

import cz.incad.nkp.inprove.permonikapi.originentities.specimen.dto.FacetFieldDTO;
import cz.incad.nkp.inprove.permonikapi.originentities.specimen.dto.SpecimenDTO;

import java.util.List;

public record VolumeOverviewStatsDTO (
        String metaTitleName,
        String owner,
        String signature,
        String barCode,
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
