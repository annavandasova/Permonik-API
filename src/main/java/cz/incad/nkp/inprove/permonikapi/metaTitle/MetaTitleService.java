package cz.incad.nkp.inprove.permonikapi.metaTitle;

import cz.incad.nkp.inprove.permonikapi.metaTitle.dto.MetaTitleWithSpecimensOverviewDTO;
import cz.incad.nkp.inprove.permonikapi.specimen.SpecimenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public List<MetaTitle> getAll(){
        Iterable<MetaTitle> iterable = metaTitleRepository.findAll();
        return Streamable.of(iterable).toList();
    }

    public List<MetaTitleWithSpecimensOverviewDTO> getOverviews(){
        List<MetaTitle> metaTitles = getAll();

        return metaTitles
                .stream()
                .map(metaTitle -> new MetaTitleWithSpecimensOverviewDTO(
                metaTitle.getId(),
                metaTitle.getName(),
                specimenService.getOverview(metaTitle.getId())
                )).toList();
    }

}
