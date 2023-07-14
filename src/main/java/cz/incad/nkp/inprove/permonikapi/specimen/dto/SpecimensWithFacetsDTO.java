package cz.incad.nkp.inprove.permonikapi.specimen.dto;

import java.util.List;

public record SpecimensWithFacetsDTO (List<SpecimenDTO> specimens, FacetsDTO facets, Object publicationDayMax, Object publicationDayMin, Integer count){ }
