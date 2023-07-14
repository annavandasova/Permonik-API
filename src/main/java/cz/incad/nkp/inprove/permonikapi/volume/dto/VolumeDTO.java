package cz.incad.nkp.inprove.permonikapi.volume.dto;

import java.util.List;

public record VolumeDTO (
        String id,
        String barCode,
        String dateFrom,
        String dateTo,
        String metaTitleId,
        String mutation,
        List<VolumePeriodicityDTO> periodicity,
        Integer pagesCount,
        String firstNumber,
        String lastNumber,
        String note,
        Boolean showAttachmentsAtTheEnd,
        String signature,
        String owner,
        String year,
        String publicationMark
) { }
