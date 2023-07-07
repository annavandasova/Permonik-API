package cz.incad.nkp.inprove.permonikapi.volume;

import cz.incad.nkp.inprove.permonikapi.volume.dto.VolumeDTO;
import cz.incad.nkp.inprove.permonikapi.volume.mapper.VolumeDTOMapper;
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
    private final SolrOperations solrTemplate;

    @Autowired
    public VolumeService(VolumeRepository volumeRepository, VolumeDTOMapper volumeDTOMapper, SolrOperations solrTemplate) {
        this.volumeRepository = volumeRepository;
        this.volumeDTOMapper = volumeDTOMapper;
        this.solrTemplate = solrTemplate;
    }

    public Optional<VolumeDTO> getVolumeById(String volumeId) {
        Optional<Volume> volume = volumeRepository.findById(volumeId);

        if(volume.isEmpty()){
            return Optional.empty();
        }

        return volume.stream().map(volumeDTOMapper).findFirst();
    }
}
