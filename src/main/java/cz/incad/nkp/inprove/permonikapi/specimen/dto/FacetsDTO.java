package cz.incad.nkp.inprove.permonikapi.specimen.dto;

import java.util.List;

public record FacetsDTO(List<FacetFieldDTO> names, List<FacetFieldDTO> subNames, List<FacetFieldDTO> mutations, List<FacetFieldDTO> publications, List<FacetFieldDTO> publicationMarks, List<FacetFieldDTO> owners, List<FacetFieldDTO> states){ }
