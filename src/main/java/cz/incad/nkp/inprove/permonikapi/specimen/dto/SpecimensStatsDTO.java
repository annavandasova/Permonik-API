package cz.incad.nkp.inprove.permonikapi.specimen.dto;

public record SpecimensStatsDTO(
        Object publicationDayMin,
        Object publicationDayMax,
        Long mutationsCount,
        Long ownersCount,
//        Integer groupedSpecimens,
        Integer matchedSpecimens
) { }
