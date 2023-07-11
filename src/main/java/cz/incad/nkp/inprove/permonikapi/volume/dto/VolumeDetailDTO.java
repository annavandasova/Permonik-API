package cz.incad.nkp.inprove.permonikapi.volume.dto;

import cz.incad.nkp.inprove.permonikapi.metaTitle.MetaTitle;
import cz.incad.nkp.inprove.permonikapi.specimen.dto.SpecimensWithDatesDTO;

import java.util.Optional;

public record VolumeDetailDTO (Optional<VolumeDTO> volume, Optional<MetaTitle> metaTitle, SpecimensWithDatesDTO specimens) { }
