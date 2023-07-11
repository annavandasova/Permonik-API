package cz.incad.nkp.inprove.permonikapi.specimen.dto;

import java.util.List;

public record SpecimensWithDatesDTO (List<SpecimenDTO> specimenList, SpecimensPublicationRangeDTO specimensDateRange) { }
