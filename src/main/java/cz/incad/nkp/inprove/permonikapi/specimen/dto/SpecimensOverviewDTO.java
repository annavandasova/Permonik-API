package cz.incad.nkp.inprove.permonikapi.specimen.dto;

public record SpecimensOverviewDTO(
        Object publicationDayMin,
        Object publicationDayMax,
        Long mutationsCount,
        Long ownersCount,
        Integer groupedSpecimens,
        Integer matchedSpecimens
) { }
