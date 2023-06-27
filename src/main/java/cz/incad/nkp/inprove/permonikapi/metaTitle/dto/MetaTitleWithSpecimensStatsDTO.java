package cz.incad.nkp.inprove.permonikapi.metaTitle.dto;

import cz.incad.nkp.inprove.permonikapi.specimen.dto.SpecimensStatsDTO;

public record MetaTitleWithSpecimensStatsDTO(String id, String name, SpecimensStatsDTO specimens){ }
