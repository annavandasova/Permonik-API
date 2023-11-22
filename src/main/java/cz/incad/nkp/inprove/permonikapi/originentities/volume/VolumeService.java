package cz.incad.nkp.inprove.permonikapi.originentities.volume;

import cz.incad.nkp.inprove.permonikapi.originentities.metatitle.MetaTitle;
import cz.incad.nkp.inprove.permonikapi.originentities.metatitle.MetaTitleService;
import cz.incad.nkp.inprove.permonikapi.originentities.specimen.SpecimenService;
import cz.incad.nkp.inprove.permonikapi.originentities.specimen.dto.SpecimensWithDatesDTO;
import cz.incad.nkp.inprove.permonikapi.originentities.specimen.dto.SpecimensWithFacetsAndStatsDTO;
import cz.incad.nkp.inprove.permonikapi.originentities.volume.dto.VolumeDTO;
import cz.incad.nkp.inprove.permonikapi.originentities.volume.dto.VolumeDetailDTO;
import cz.incad.nkp.inprove.permonikapi.originentities.volume.dto.VolumeOverviewStatsDTO;
import cz.incad.nkp.inprove.permonikapi.originentities.volume.mapper.VolumeDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VolumeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VolumeService.class);

    private final VolumeRepository volumeRepository;
    private final VolumeDTOMapper volumeDTOMapper;
    private final MetaTitleService metaTitleService;
    private final SpecimenService specimenService;
    private final SolrOperations solrTemplate;

    @Autowired
    public VolumeService(VolumeRepository volumeRepository, VolumeDTOMapper volumeDTOMapper, MetaTitleService metaTitleService, SpecimenService specimenService, SolrOperations solrTemplate) {
        this.volumeRepository = volumeRepository;
        this.volumeDTOMapper = volumeDTOMapper;
        this.metaTitleService = metaTitleService;
        this.specimenService = specimenService;
        this.solrTemplate = solrTemplate;
    }

    public Optional<VolumeDTO> getVolumeById(String volumeId) {
        Optional<Volume> volume = volumeRepository.findById(volumeId);

        if(volume.isEmpty()){
            return Optional.empty();
        }

        return volume.stream().map(volumeDTOMapper).findFirst();
    }

    public Optional<VolumeDetailDTO> getVolumeDetailById(String volumeId) {
        Optional<VolumeDTO> volumeDTO = getVolumeById(volumeId);

        if(volumeDTO.isEmpty()){
            return Optional.empty();
        }

        Optional<MetaTitle> metaTitle = metaTitleService.getMetaTitleById(volumeDTO.get().metaTitleId());

        if(metaTitle.isEmpty()){
            return Optional.empty();
        }

        SpecimensWithDatesDTO specimensWithDatesDTO = specimenService.getSpecimensForVolumeWithDates(volumeDTO.get().barCode(), volumeDTO.get().dateFrom(), volumeDTO.get().dateTo());

        return Optional.of(new VolumeDetailDTO(volumeDTO, metaTitle, specimensWithDatesDTO));

    }

    public Optional<VolumeOverviewStatsDTO> getVolumeOverviewStats(String volumeId) {
        Optional<VolumeDTO> volumeDTO = getVolumeById(volumeId);

        if(volumeDTO.isEmpty()){
            return Optional.empty();
        }

        Optional<MetaTitle> metaTitle = metaTitleService.getMetaTitleById(volumeDTO.get().metaTitleId());

        if(metaTitle.isEmpty()){
            return Optional.empty();
        }

        SpecimensWithFacetsAndStatsDTO specimensWithFacetsAndStatsDTO = specimenService.getSpecimensWithFacetsAndStatsByVolume(volumeId);

        return Optional.of(new VolumeOverviewStatsDTO(
            metaTitle.get().getName(),
            volumeDTO.get().owner(),
            volumeDTO.get().signature(),
            volumeDTO.get().barCode(),
            specimensWithFacetsAndStatsDTO.publicationDayMin(),
            specimensWithFacetsAndStatsDTO.publicationDayMax(),
            specimensWithFacetsAndStatsDTO.numberMin(),
            specimensWithFacetsAndStatsDTO.numberMax(),
            specimensWithFacetsAndStatsDTO.pagesCount(),
            specimensWithFacetsAndStatsDTO.mutations(),
            specimensWithFacetsAndStatsDTO.publicationMark(),
            specimensWithFacetsAndStatsDTO.publication(),
            specimensWithFacetsAndStatsDTO.states(),
            specimensWithFacetsAndStatsDTO.publicationDayRanges(),
            specimensWithFacetsAndStatsDTO.specimens()
        ));

    }
}
