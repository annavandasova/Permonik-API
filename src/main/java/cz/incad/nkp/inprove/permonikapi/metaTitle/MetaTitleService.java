package cz.incad.nkp.inprove.permonikapi.metaTitle;

import cz.incad.nkp.inprove.permonikapi.metaTitle.dto.MetaTitleWithSpecimensStatsDTO;
import cz.incad.nkp.inprove.permonikapi.specimen.SpecimenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static cz.incad.nkp.inprove.permonikapi.metaTitle.MetaTitleDefinition.*;

@Service
public class MetaTitleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpecimenService.class);

    private final MetaTitleRepository metaTitleRepository;
    private final SpecimenService specimenService;
    private final SolrOperations solrTemplate;

    @Autowired
    public MetaTitleService(MetaTitleRepository metaTitleRepository, SpecimenService specimenService, SolrOperations solrTemplate) {
        this.metaTitleRepository = metaTitleRepository;
        this.specimenService = specimenService;
        this.solrTemplate = solrTemplate;
    }

    public Optional<MetaTitle> getMetaTitleById(String metaTitleId) {
        Criteria criteria = new Criteria(SHOW_TO_NOT_LOGGED_USERS_FIELD).is(true).and(ID_FIELD).is(metaTitleId);

        SimpleQuery simpleQuery = new SimpleQuery(criteria);
        simpleQuery.setRows(1);

        Page<MetaTitle> page = solrTemplate.queryForPage(META_TITLE_CORE_NAME, simpleQuery, MetaTitle.class);


        return page.stream().findAny();
    }

    public List<MetaTitle> getAllMetaTitles(){
        Criteria criteria = new Criteria(SHOW_TO_NOT_LOGGED_USERS_FIELD).is(true);

        SimpleQuery simpleQuery = new SimpleQuery(criteria);
        simpleQuery.setRows(100000);

        Page<MetaTitle> page = solrTemplate.queryForPage(META_TITLE_CORE_NAME, simpleQuery, MetaTitle.class);

        return page.getContent().stream().toList();
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
