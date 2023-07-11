package cz.incad.nkp.inprove.permonikapi.specimen.dto;

import java.util.List;

public record SpecimenPagesDTO (List<String> damaged, List<String> missing) { }
