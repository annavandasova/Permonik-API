package cz.incad.nkp.inprove.permonikapi.specimen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.StatsPage;
import org.springframework.stereotype.Service;

@Service
public class SpecimenService {

    private final SpecimenRepository specimenRepository;

    @Autowired
    public SpecimenService(SpecimenRepository specimenRepository) {
        this.specimenRepository = specimenRepository;
    }


    public StatsPage<Specimen> getOverview(Pageable pageable) {
        return specimenRepository.getSpecimensOverview(pageable);
    }
}
