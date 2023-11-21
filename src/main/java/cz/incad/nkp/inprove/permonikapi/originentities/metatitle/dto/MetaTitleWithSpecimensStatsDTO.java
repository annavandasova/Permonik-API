package cz.incad.nkp.inprove.permonikapi.originentities.metatitle.dto;

import cz.incad.nkp.inprove.permonikapi.originentities.specimen.dto.SpecimensStatsDTO;

public record MetaTitleWithSpecimensStatsDTO(String id, String name, SpecimensStatsDTO specimens){ }
