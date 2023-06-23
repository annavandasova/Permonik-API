package cz.incad.nkp.inprove.permonikapi.metaTitle.dto;

import cz.incad.nkp.inprove.permonikapi.specimen.dto.SpecimensOverviewDTO;

public record MetaTitleWithSpecimensOverviewDTO(String id, String name, SpecimensOverviewDTO specimens){ }
