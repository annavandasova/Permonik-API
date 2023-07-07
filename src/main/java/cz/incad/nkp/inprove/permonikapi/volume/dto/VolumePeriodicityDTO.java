package cz.incad.nkp.inprove.permonikapi.volume.dto;

public record VolumePeriodicityDTO (

        Boolean active,
        String publication,
        String day,
        Integer pagesCount,
        String name,
        String subName
) { }
