package cz.incad.nkp.inprove.permonikapi.originentities.specimen.dto;

import java.util.List;

public record SpecimensWithDatesDTO (List<SpecimenDTO> specimenList, SpecimensPublicationRangeDTO specimensDateRange) { }
