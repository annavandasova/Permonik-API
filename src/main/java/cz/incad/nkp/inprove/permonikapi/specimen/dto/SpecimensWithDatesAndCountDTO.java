package cz.incad.nkp.inprove.permonikapi.specimen.dto;

import java.util.List;

public record SpecimensWithDatesAndCountDTO(List<SpecimenDTO> specimens, Object publicationDayMax, Object publicationDayMin, Integer count){ }
