package cz.incad.nkp.inprove.permonikapi.originentities.volume.dto;

import cz.incad.nkp.inprove.permonikapi.originentities.metatitle.MetaTitle;
import cz.incad.nkp.inprove.permonikapi.originentities.specimen.dto.SpecimensWithDatesDTO;

import java.util.Optional;

public record VolumeDetailDTO (Optional<VolumeDTO> volume, Optional<MetaTitle> metaTitle, SpecimensWithDatesDTO specimens) { }
