package cz.incad.nkp.inprove.permonikapi.specimen;

import cz.incad.nkp.inprove.permonikapi.specimen.dto.SpecimensOverviewDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.GroupOptions;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.StatsOptions;
import org.springframework.data.solr.core.query.result.*;
import org.springframework.stereotype.Service;

import java.util.List;

import static cz.incad.nkp.inprove.permonikapi.specimen.SpecimenDefinition.*;

@Service
public class SpecimenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpecimenService.class);

    private final SpecimenRepository specimenRepository;
    private final SolrOperations solrTemplate;

    @Autowired
    public SpecimenService(SpecimenRepository specimenRepository, SolrOperations solrTemplate) {
        this.specimenRepository = specimenRepository;
        this.solrTemplate = solrTemplate;
    }


    public List<SpecimensOverviewDTO> getOverviews(List<String> titles){
        return titles.stream().map(this::getOverview).toList();
    }

    public SpecimensOverviewDTO getOverview(String idTitle) {

        Criteria criteria = new Criteria(ID_META_TITLE_FIELD).contains(idTitle);

        StatsOptions statsOptions = new StatsOptions();
        statsOptions.addField(MUTATION_FIELD).setSelectiveCalcDistinct(true).setCalcDistinct(true);
        statsOptions.addField(PUBLICATION_DAY_FIELD);
        statsOptions.addField(OWNER_FIELD).setSelectiveCalcDistinct(true).setCalcDistinct(true);


        SimpleQuery statsQuery = new SimpleQuery(criteria);
        statsQuery.setRows(0);
        statsQuery.setStatsOptions(statsOptions);
        //Execute query with params
        StatsPage<Specimen> statsPage = solrTemplate.queryForStatsPage(SPECIMEN_CORE_NAME, statsQuery, Specimen.class);

        //Get specimens date range
        Object publicationDayMin = statsPage.getFieldStatsResult(PUBLICATION_DAY_FIELD).getMin();
        Object publicationDayMax = statsPage.getFieldStatsResult(PUBLICATION_DAY_FIELD).getMax();

        //Get mutations count
        Long mutationsCount = statsPage.getFieldStatsResult(MUTATION_FIELD).getDistinctCount();

        //Get owners count
        Long ownersCount = statsPage.getFieldStatsResult(OWNER_FIELD).getDistinctCount();



        GroupOptions groupOptions = new GroupOptions();
        groupOptions.addGroupByField(ID_ISSUE_FIELD);
        groupOptions.setLimit(1);
        groupOptions.setTotalCount(true);

        SimpleQuery groupQuery = new SimpleQuery(criteria);
        groupQuery.setRows(0);
        groupQuery.setGroupOptions(groupOptions);
        //Execute query
        GroupPage<Specimen> countPage = solrTemplate.queryForGroupPage(SPECIMEN_CORE_NAME, groupQuery, Specimen.class);

        //Get grouped specimens based on ID_ISSUE_FIELD
        Integer groupedSpecimens = countPage.getGroupResult(ID_ISSUE_FIELD).getGroupsCount();
        Integer matchedSpecimens = countPage.getGroupResult(ID_ISSUE_FIELD).getMatches();


        return new SpecimensOverviewDTO(publicationDayMin, publicationDayMax, mutationsCount, ownersCount, groupedSpecimens, matchedSpecimens);

    }



}
