package cz.incad.nkp.inprove.permonikapi.specimen;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.incad.nkp.inprove.permonikapi.specimen.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.*;
import org.springframework.stereotype.Service;

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



    public SpecimensStatsDTO getOverviewStats(String idTitle) {

        Criteria criteria = new Criteria(ID_META_TITLE_FIELD).is(idTitle).and(NUM_EXISTS_FIELD).is(true);

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


        return new SpecimensStatsDTO(publicationDayMin, publicationDayMax, mutationsCount, ownersCount, groupedSpecimens, matchedSpecimens);

    }


    public SpecimensWithFacetsDTO getSpecimensWithFacetsByMetaTitle(String idTitle, Integer offset, Integer rows, String facets) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        SpecimenFacets specimenFacets = objectMapper.readValue(facets, SpecimenFacets.class);

        Criteria criteria = new Criteria(ID_META_TITLE_FIELD).is(idTitle)
                .and(NUM_EXISTS_FIELD).is(true);

        for (String name : specimenFacets.getNames()) {
            if (name.isEmpty()) {
                criteria.and(new SimpleStringCriteria(NAME_FIELD + ":\"\""));
            } else {
                criteria.and(PUBLICATION_MARK_FIELD).is(name);
            }
        }

        if(!specimenFacets.getMutations().isEmpty()){
            criteria.and(MUTATION_FIELD).is(specimenFacets.getMutations());
        }

        if(!specimenFacets.getPublications().isEmpty()){
            criteria.and(PUBLICATION_FIELD).is(specimenFacets.getPublications());
        }

        for (String publicationMark : specimenFacets.getPublicationMarks()) {
            if (publicationMark.isEmpty()) {
                criteria.and(new SimpleStringCriteria(PUBLICATION_MARK_FIELD + ":\"\""));
            } else {
                criteria.and(PUBLICATION_MARK_FIELD).is(publicationMark);
            }
        }

        if(!specimenFacets.getOwners().isEmpty()){
            criteria.and(OWNER_FIELD).is(specimenFacets.getOwners());
        }

        if(!specimenFacets.getStates().isEmpty()){
            criteria.and(STATES_FIELD).is(specimenFacets.getStates());
        }

        if(!specimenFacets.getVolume().isEmpty()){
            criteria.and(BAR_CODE_FIELD).contains(specimenFacets.getVolume());

        }

        // Add filtering based on year interval
        if(specimenFacets.getDateStart() > 0 && specimenFacets.getDateEnd() > 0){
            criteria
                .and(PUBLICATION_DAY_FIELD).greaterThanEqual(specimenFacets.getDateStart() + "0101")
                .and(PUBLICATION_DAY_FIELD).lessThanEqual(specimenFacets.getDateEnd() + "1231");
        }

        FacetOptions facetOptions = new FacetOptions();
        facetOptions.addFacetOnField(NAME_FIELD);
        facetOptions.addFacetOnField(MUTATION_FIELD);
        facetOptions.addFacetOnField(PUBLICATION_FIELD);
        facetOptions.addFacetOnField(PUBLICATION_MARK_FIELD);
        facetOptions.addFacetOnField(OWNER_FIELD);
        facetOptions.addFacetOnField(STATES_FIELD);

        SimpleFacetQuery facetQuery = new SimpleFacetQuery(criteria);
        facetQuery.setRows(rows);
        facetQuery.setOffset(Long.valueOf(offset));
        facetQuery.setFacetOptions(facetOptions);
        facetQuery.addSort(Sort.by(Sort.Direction.ASC, PUBLICATION_DAY_FIELD));
        facetQuery.addSort(Sort.by(Sort.Direction.DESC, PUBLICATION_FIELD));

        //Execute query with params
        FacetPage<Specimen> facetPage = solrTemplate.queryForFacetPage(SPECIMEN_CORE_NAME, facetQuery, Specimen.class);


        StatsOptions statsOptions = new StatsOptions();
        statsOptions.addField(PUBLICATION_DAY_FIELD);

        SimpleQuery statsQuery = new SimpleQuery(
                new Criteria(ID_META_TITLE_FIELD)
                        .is(idTitle)
                        .and(NUM_EXISTS_FIELD)
                        .is(true)
        );
        statsQuery.setRows(0);
        statsQuery.setStatsOptions(statsOptions);

        StatsPage<Specimen> statsPage = solrTemplate.queryForStatsPage(SPECIMEN_CORE_NAME, statsQuery, Specimen.class);
        Object publicationDayMin = statsPage.getFieldStatsResult(PUBLICATION_DAY_FIELD).getMin();
        Object publicationDayMax = statsPage.getFieldStatsResult(PUBLICATION_DAY_FIELD).getMax();




        GroupOptions groupOptions = new GroupOptions();
        groupOptions.addGroupByField(ID_ISSUE_FIELD);
        groupOptions.setLimit(20);
        groupOptions.setTotalCount(true);

        SimpleQuery groupQuery = new SimpleQuery(criteria);
        groupQuery.setRows(rows);
        groupQuery.setOffset(Long.valueOf(offset));
        groupQuery.setGroupOptions(groupOptions);
        groupQuery.addSort(Sort.by(Sort.Direction.ASC, PUBLICATION_DAY_FIELD));
        groupQuery.addSort(Sort.by(Sort.Direction.DESC, PUBLICATION_FIELD));

        GroupPage<Specimen> countPage = solrTemplate.queryForGroupPage(SPECIMEN_CORE_NAME, groupQuery, Specimen.class);

        Integer groupedSpecimens = countPage.getGroupResult(ID_ISSUE_FIELD).getMatches();


        return new SpecimensWithFacetsDTO(
                facetPage.getContent(),
                new FacetsDTO(
                        facetPage.getFacetResultPage(NAME_FIELD).stream().map(facetFieldEntry -> new FacetFieldDTO(facetFieldEntry.getValue(), facetFieldEntry.getValueCount())).toList(),
                        facetPage.getFacetResultPage(MUTATION_FIELD).stream().map(facetFieldEntry -> new FacetFieldDTO(facetFieldEntry.getValue(), facetFieldEntry.getValueCount())).toList(),
                        facetPage.getFacetResultPage(PUBLICATION_FIELD).stream().map(facetFieldEntry -> new FacetFieldDTO(facetFieldEntry.getValue(), facetFieldEntry.getValueCount())).toList(),
                        facetPage.getFacetResultPage(PUBLICATION_MARK_FIELD).stream().map(facetFieldEntry -> new FacetFieldDTO(facetFieldEntry.getValue(), facetFieldEntry.getValueCount())).toList(),
                        facetPage.getFacetResultPage(OWNER_FIELD).stream().map(facetFieldEntry -> new FacetFieldDTO(facetFieldEntry.getValue(), facetFieldEntry.getValueCount())).toList(),
                        facetPage.getFacetResultPage(STATES_FIELD).stream().map(facetFieldEntry -> new FacetFieldDTO(facetFieldEntry.getValue(), facetFieldEntry.getValueCount())).toList()
                ),
                publicationDayMax,
                publicationDayMin,
                groupedSpecimens

        );

    }
}
