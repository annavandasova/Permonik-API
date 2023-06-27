package cz.incad.nkp.inprove.permonikapi.metaTitle;

import cz.incad.nkp.inprove.permonikapi.metaTitle.dto.MetaTitleWithSpecimensStatsDTO;
import cz.incad.nkp.inprove.permonikapi.specimen.SpecimenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetaTitleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpecimenService.class);

    private final MetaTitleRepository metaTitleRepository;
    private final SpecimenService specimenService;

    @Autowired
    public MetaTitleService(MetaTitleRepository metaTitleRepository, SpecimenService specimenService) {
        this.metaTitleRepository = metaTitleRepository;
        this.specimenService = specimenService;
    }

    public Optional<MetaTitle> getMetaTitleById(String metaTitleId) {
        return metaTitleRepository.findById(metaTitleId);
    }

    public List<MetaTitle> getAllMetaTitles(){
        Iterable<MetaTitle> iterable = metaTitleRepository.findAll();
        return Streamable.of(iterable).toList();
    }

    public List<MetaTitleWithSpecimensStatsDTO> getOverviewsWithStats(){
        List<MetaTitle> metaTitles = getAllMetaTitles();

        return metaTitles
                .stream()
                .map(metaTitle -> new MetaTitleWithSpecimensStatsDTO(
                metaTitle.getId(),
                metaTitle.getName(),
                specimenService.getOverviewStats(metaTitle.getId())
                )).toList();
    }

}
