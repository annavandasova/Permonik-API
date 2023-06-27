package cz.incad.nkp.inprove.permonikapi.specimen.dto;

import cz.incad.nkp.inprove.permonikapi.specimen.Specimen;

import java.util.List;

public record SpecimensWithFacetsDTO (List<Specimen> specimens, FacetsDTO facets, Object publicationDayMax, Object publicationDayMin, Integer count){ }
