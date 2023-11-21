package cz.incad.nkp.inprove.permonikapi.originentities.specimen;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.incad.nkp.inprove.permonikapi.originentities.specimen.mapper.SpecimenDTOMapper;
import cz.incad.nkp.inprove.permonikapi.originentities.specimen.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpecimenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpecimenService.class);

    private final SpecimenRepository specimenRepository;
    private final SpecimenDTOMapper specimenDTOMapper;
    private final SolrOperations solrTemplate;

    @Autowired
    public SpecimenService(SpecimenRepository specimenRepository, SpecimenDTOMapper specimenDTOMapper, SolrOperations solrTemplate) {
        this.specimenRepository = specimenRepository;
        this.specimenDTOMapper = specimenDTOMapper;
        this.solrTemplate = solrTemplate;
    }



    public SpecimensStatsDTO getBasicOverviewStatsForMetaTitle(String idTitle) {

        Criteria criteria = new Criteria(SpecimenDefinition.ID_META_TITLE_FIELD).is(idTitle).and(SpecimenDefinition.NUM_EXISTS_FIELD).is(true);

        StatsOptions statsOptions = new StatsOptions();
        statsOptions.addField(SpecimenDefinition.MUTATION_FIELD).setSelectiveCalcDistinct(true).setCalcDistinct(true);
        statsOptions.addField(SpecimenDefinition.PUBLICATION_DAY_FIELD);
        statsOptions.addField(SpecimenDefinition.OWNER_FIELD).setSelectiveCalcDistinct(true).setCalcDistinct(true);


        SimpleQuery statsQuery = new SimpleQuery(criteria);
        statsQuery.setRows(0);
        statsQuery.setStatsOptions(statsOptions);
        //Execute query with params
        StatsPage<Specimen> statsPage = solrTemplate.queryForStatsPage(SpecimenDefinition.SPECIMEN_CORE_NAME, statsQuery, Specimen.class);

        //Get specimens date range
        Object publicationDayMin = statsPage.getFieldStatsResult(SpecimenDefinition.PUBLICATION_DAY_FIELD).getMin();
        Object publicationDayMax = statsPage.getFieldStatsResult(SpecimenDefinition.PUBLICATION_DAY_FIELD).getMax();

        //Get mutations count
        Long mutationsCount = statsPage.getFieldStatsResult(SpecimenDefinition.MUTATION_FIELD).getDistinctCount();

        //Get owners count
        Long ownersCount = statsPage.getFieldStatsResult(SpecimenDefinition.OWNER_FIELD).getDistinctCount();



        GroupOptions groupOptions = new GroupOptions();
        groupOptions.addGroupByField(SpecimenDefinition.ID_ISSUE_FIELD);
        groupOptions.setLimit(1);
        groupOptions.setTotalCount(true);

        SimpleQuery groupQuery = new SimpleQuery(criteria);
        groupQuery.setRows(0);
        groupQuery.setGroupOptions(groupOptions);
        //Execute query
        GroupPage<Specimen> countPage = solrTemplate.queryForGroupPage(SpecimenDefinition.SPECIMEN_CORE_NAME, groupQuery, Specimen.class);

        //Get grouped specimens based on ID_ISSUE_FIELD
//        Integer groupedSpecimens = countPage.getGroupResult(ID_ISSUE_FIELD).getGroupsCount();
        Integer matchedSpecimens = countPage.getGroupResult(SpecimenDefinition.ID_ISSUE_FIELD).getMatches();


        return new SpecimensStatsDTO(publicationDayMin, publicationDayMax, mutationsCount, ownersCount, matchedSpecimens);

    }


    public SpecimensWithDatesAndCountDTO getSpecimensWithDatesAndCountByMetaTitle(String idTitle, Integer offset, Integer rows, String facets, String view) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        SpecimenFacets specimenFacets = objectMapper.readValue(facets, SpecimenFacets.class);

        Criteria criteria = new Criteria(SpecimenDefinition.ID_META_TITLE_FIELD).is(idTitle)
                .and(SpecimenDefinition.NUM_EXISTS_FIELD).is(true);

        for (String name : specimenFacets.getNames()) {
            if (name.isEmpty()) {
                criteria.and(new SimpleStringCriteria(SpecimenDefinition.NAME_FIELD + ":\"\""));
            } else {
                criteria.and(SpecimenDefinition.NAME_FIELD).is(name);
            }
        }

        for (String subName : specimenFacets.getSubNames()) {
            if (subName.isEmpty()) {
                criteria.and(new SimpleStringCriteria(SpecimenDefinition.SUB_NAME_FIELD + ":\"\""));
            } else {
                criteria.and(SpecimenDefinition.SUB_NAME_FIELD).is(subName);
            }
        }

        if(!specimenFacets.getMutations().isEmpty()){
            criteria.and(SpecimenDefinition.MUTATION_FIELD).is(specimenFacets.getMutations());
        }

        if(!specimenFacets.getPublications().isEmpty()){
            criteria.and(SpecimenDefinition.PUBLICATION_FIELD).is(specimenFacets.getPublications());
        }

        for (String publicationMark : specimenFacets.getPublicationMarks()) {
            if (publicationMark.isEmpty()) {
                criteria.and(new SimpleStringCriteria(SpecimenDefinition.PUBLICATION_MARK_FIELD + ":\"\""));
            } else {
                criteria.and(SpecimenDefinition.PUBLICATION_MARK_FIELD).is(publicationMark);
            }
        }

        if(!specimenFacets.getOwners().isEmpty()){
            criteria.and(SpecimenDefinition.OWNER_FIELD).is(specimenFacets.getOwners());
        }

        if(!specimenFacets.getStates().isEmpty()){
            criteria.and(SpecimenDefinition.STATES_FIELD).is(specimenFacets.getStates());
        }

        if(!specimenFacets.getVolume().isEmpty()){
            criteria.and(SpecimenDefinition.BAR_CODE_FIELD).contains(specimenFacets.getVolume());
        }

        // Add filtering based on year interval
        if(specimenFacets.getDateStart() > 0 && specimenFacets.getDateEnd() > 0) {
            criteria
                .and(SpecimenDefinition.PUBLICATION_DAY_FIELD).greaterThanEqual(specimenFacets.getDateStart() + "0101")
                .and(SpecimenDefinition.PUBLICATION_DAY_FIELD).lessThanEqual(specimenFacets.getDateEnd() + "1231");
        } else if (Objects.equals(view, "calendar") && !specimenFacets.getCalendarDateStart().isEmpty()) {
            criteria
                .and(SpecimenDefinition.PUBLICATION_DATE_FIELD).greaterThanEqual(specimenFacets.getCalendarDateStart())
                .and(SpecimenDefinition.PUBLICATION_DATE_FIELD).lessThanEqual(specimenFacets.getCalendarDateEnd());
        }

        FacetOptions facetOptions = new FacetOptions();
        facetOptions.addFacetOnField(SpecimenDefinition.NAME_FIELD);
        facetOptions.addFacetOnField(SpecimenDefinition.SUB_NAME_FIELD);
        facetOptions.addFacetOnField(SpecimenDefinition.MUTATION_FIELD);
        facetOptions.addFacetOnField(SpecimenDefinition.PUBLICATION_FIELD);
        facetOptions.addFacetOnField(SpecimenDefinition.PUBLICATION_MARK_FIELD);
        facetOptions.addFacetOnField(SpecimenDefinition.OWNER_FIELD);
        facetOptions.addFacetOnField(SpecimenDefinition.STATES_FIELD);

        SimpleFacetQuery facetQuery = new SimpleFacetQuery(criteria);
        facetQuery.setRows(rows);
        facetQuery.setOffset(Long.valueOf(offset));
        facetQuery.setFacetOptions(facetOptions);
        facetQuery.addSort(Sort.by(Sort.Direction.ASC, SpecimenDefinition.PUBLICATION_DAY_FIELD));
        facetQuery.addSort(Sort.by(Sort.Direction.DESC, SpecimenDefinition.PUBLICATION_FIELD));
//        facetQuery.addSort(Sort.by(Sort.Direction.ASC, MUTATION_FIELD));

        //Execute query with params
        FacetPage<Specimen> facetPage = solrTemplate.queryForFacetPage(SpecimenDefinition.SPECIMEN_CORE_NAME, facetQuery, Specimen.class);


        StatsOptions statsOptions = new StatsOptions();
        statsOptions.addField(SpecimenDefinition.PUBLICATION_DAY_FIELD);

        SimpleQuery statsQuery = new SimpleQuery(
                new Criteria(SpecimenDefinition.ID_META_TITLE_FIELD)
                        .is(idTitle)
                        .and(SpecimenDefinition.NUM_EXISTS_FIELD)
                        .is(true)
        );
        statsQuery.setRows(0);
        statsQuery.setStatsOptions(statsOptions);

        StatsPage<Specimen> statsPage = solrTemplate.queryForStatsPage(SpecimenDefinition.SPECIMEN_CORE_NAME, statsQuery, Specimen.class);
        Object publicationDayMin = statsPage.getFieldStatsResult(SpecimenDefinition.PUBLICATION_DAY_FIELD).getMin();
        Object publicationDayMax = statsPage.getFieldStatsResult(SpecimenDefinition.PUBLICATION_DAY_FIELD).getMax();




        GroupOptions groupOptions = new GroupOptions();
        groupOptions.addGroupByField(SpecimenDefinition.ID_ISSUE_FIELD);
        groupOptions.setLimit(20);
        groupOptions.setTotalCount(true);

        SimpleQuery groupQuery = new SimpleQuery(criteria);
        groupQuery.setRows(rows);
        groupQuery.setOffset(Long.valueOf(offset));
        groupQuery.setGroupOptions(groupOptions);
        groupQuery.addSort(Sort.by(Sort.Direction.ASC, SpecimenDefinition.PUBLICATION_DAY_FIELD));
        groupQuery.addSort(Sort.by(Sort.Direction.DESC, SpecimenDefinition.PUBLICATION_FIELD));

        GroupPage<Specimen> countPage = solrTemplate.queryForGroupPage(SpecimenDefinition.SPECIMEN_CORE_NAME, groupQuery, Specimen.class);

        Integer groupedSpecimens = countPage.getGroupResult(SpecimenDefinition.ID_ISSUE_FIELD).getMatches();


        return new SpecimensWithDatesAndCountDTO(
            facetPage.getContent().stream().map(specimenDTOMapper).toList(),
            publicationDayMax,
            publicationDayMin,
            groupedSpecimens
        );

    }

    public FacetsDTO getSpecimensFacets(String idTitle, String facets) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        SpecimenFacets specimenFacets = objectMapper.readValue(facets, SpecimenFacets.class);

        Criteria criteria = new Criteria(SpecimenDefinition.ID_META_TITLE_FIELD).is(idTitle)
                .and(SpecimenDefinition.NUM_EXISTS_FIELD).is(true);

        for (String name : specimenFacets.getNames()) {
            if (name.isEmpty()) {
                criteria.and(new SimpleStringCriteria(SpecimenDefinition.NAME_FIELD + ":\"\""));
            } else {
                criteria.and(SpecimenDefinition.NAME_FIELD).is(name);
            }
        }

        for (String subName : specimenFacets.getSubNames()) {
            if (subName.isEmpty()) {
                criteria.and(new SimpleStringCriteria(SpecimenDefinition.SUB_NAME_FIELD + ":\"\""));
            } else {
                criteria.and(SpecimenDefinition.SUB_NAME_FIELD).is(subName);
            }
        }

        if(!specimenFacets.getMutations().isEmpty()){
            criteria.and(SpecimenDefinition.MUTATION_FIELD).is(specimenFacets.getMutations());
        }

        if(!specimenFacets.getPublications().isEmpty()){
            criteria.and(SpecimenDefinition.PUBLICATION_FIELD).is(specimenFacets.getPublications());
        }

        for (String publicationMark : specimenFacets.getPublicationMarks()) {
            if (publicationMark.isEmpty()) {
                criteria.and(new SimpleStringCriteria(SpecimenDefinition.PUBLICATION_MARK_FIELD + ":\"\""));
            } else {
                criteria.and(SpecimenDefinition.PUBLICATION_MARK_FIELD).is(publicationMark);
            }
        }

        if(!specimenFacets.getOwners().isEmpty()){
            criteria.and(SpecimenDefinition.OWNER_FIELD).is(specimenFacets.getOwners());
        }

        if(!specimenFacets.getStates().isEmpty()){
            criteria.and(SpecimenDefinition.STATES_FIELD).is(specimenFacets.getStates());
        }

        if(!specimenFacets.getVolume().isEmpty()){
            criteria.and(SpecimenDefinition.BAR_CODE_FIELD).contains(specimenFacets.getVolume());
        }

        // Add filtering based on year interval
        if(specimenFacets.getDateStart() > 0 && specimenFacets.getDateEnd() > 0) {
            criteria
                .and(SpecimenDefinition.PUBLICATION_DAY_FIELD).greaterThanEqual(specimenFacets.getDateStart() + "0101")
                .and(SpecimenDefinition.PUBLICATION_DAY_FIELD).lessThanEqual(specimenFacets.getDateEnd() + "1231");
        }

        FacetOptions facetOptions = new FacetOptions();
        facetOptions.addFacetOnField(SpecimenDefinition.NAME_FIELD);
        facetOptions.addFacetOnField(SpecimenDefinition.SUB_NAME_FIELD);
        facetOptions.addFacetOnField(SpecimenDefinition.MUTATION_FIELD);
        facetOptions.addFacetOnField(SpecimenDefinition.PUBLICATION_FIELD);
        facetOptions.addFacetOnField(SpecimenDefinition.PUBLICATION_MARK_FIELD);
        facetOptions.addFacetOnField(SpecimenDefinition.OWNER_FIELD);
        facetOptions.addFacetOnField(SpecimenDefinition.STATES_FIELD);

        SimpleFacetQuery facetQuery = new SimpleFacetQuery(criteria);
        facetQuery.setRows(0);
        facetQuery.setOffset(0L);
        facetQuery.setFacetOptions(facetOptions);
        facetQuery.addSort(Sort.by(Sort.Direction.ASC, SpecimenDefinition.PUBLICATION_DAY_FIELD));
        facetQuery.addSort(Sort.by(Sort.Direction.DESC, SpecimenDefinition.PUBLICATION_FIELD));
//        facetQuery.addSort(Sort.by(Sort.Direction.ASC, MUTATION_FIELD));

        //Execute query with params
        FacetPage<Specimen> facetPage = solrTemplate.queryForFacetPage(SpecimenDefinition.SPECIMEN_CORE_NAME, facetQuery, Specimen.class);

        return new FacetsDTO(
            facetPage.getFacetResultPage(SpecimenDefinition.NAME_FIELD).stream().map(facetFieldEntry -> new FacetFieldDTO(facetFieldEntry.getValue(), facetFieldEntry.getValueCount())).toList(),
            facetPage.getFacetResultPage(SpecimenDefinition.SUB_NAME_FIELD).stream().map(facetFieldEntry -> new FacetFieldDTO(facetFieldEntry.getValue(), facetFieldEntry.getValueCount())).toList(),
            facetPage.getFacetResultPage(SpecimenDefinition.MUTATION_FIELD).stream().map(facetFieldEntry -> new FacetFieldDTO(facetFieldEntry.getValue(), facetFieldEntry.getValueCount())).toList(),
            facetPage.getFacetResultPage(SpecimenDefinition.PUBLICATION_FIELD).stream().map(facetFieldEntry -> new FacetFieldDTO(facetFieldEntry.getValue(), facetFieldEntry.getValueCount())).toList(),
            facetPage.getFacetResultPage(SpecimenDefinition.PUBLICATION_MARK_FIELD).stream().map(facetFieldEntry -> new FacetFieldDTO(facetFieldEntry.getValue(), facetFieldEntry.getValueCount())).toList(),
            facetPage.getFacetResultPage(SpecimenDefinition.OWNER_FIELD).stream().map(facetFieldEntry -> new FacetFieldDTO(facetFieldEntry.getValue(), facetFieldEntry.getValueCount())).toList(),
            facetPage.getFacetResultPage(SpecimenDefinition.STATES_FIELD).stream().map(facetFieldEntry -> new FacetFieldDTO(facetFieldEntry.getValue(), facetFieldEntry.getValueCount())).toList()
        );

    }


    public SpecimensWithDatesDTO getSpecimensForVolumeWithDates (String barCode, String dateFrom, String dateTo){

        Criteria criteria = new Criteria(SpecimenDefinition.BAR_CODE_FIELD).is(barCode)
                .and(SpecimenDefinition.PUBLICATION_DATE_FIELD).greaterThanEqual(dateFrom)
                .and(SpecimenDefinition.PUBLICATION_DATE_FIELD).lessThanEqual(dateTo)
                .and(new Criteria(SpecimenDefinition.NUM_EXISTS_FIELD).is(true).or(SpecimenDefinition.NUM_MISSING_FIELD).is(true));

        StatsOptions statsOptions = new StatsOptions();
        statsOptions.addField(SpecimenDefinition.PUBLICATION_DAY_FIELD);

        SimpleQuery statsQuery = new SimpleQuery(criteria);
        statsQuery.setRows(100000);
        statsQuery.setStatsOptions(statsOptions);

        StatsPage<Specimen> statsPage = solrTemplate.queryForStatsPage(SpecimenDefinition.SPECIMEN_CORE_NAME, statsQuery, Specimen.class);

        Object publicationDayMin = statsPage.getFieldStatsResult(SpecimenDefinition.PUBLICATION_DAY_FIELD).getMin();
        Object publicationDayMax = statsPage.getFieldStatsResult(SpecimenDefinition.PUBLICATION_DAY_FIELD).getMax();

        return new SpecimensWithDatesDTO(
                statsPage.getContent().stream().map(specimenDTOMapper).toList(),
                new SpecimensPublicationRangeDTO(
                        publicationDayMin,
                        publicationDayMax
                )
        );

    }

    public Object getSpecimensStartDateByMetaTitle(String idTitle) {

        StatsOptions statsOptions = new StatsOptions();
        statsOptions.addField(SpecimenDefinition.PUBLICATION_DAY_FIELD);

        SimpleQuery statsQuery = new SimpleQuery(
                new Criteria(SpecimenDefinition.ID_META_TITLE_FIELD)
                        .is(idTitle)
                        .and(SpecimenDefinition.NUM_EXISTS_FIELD)
                        .is(true)
        );
        statsQuery.setRows(0);
        statsQuery.setStatsOptions(statsOptions);

        StatsPage<Specimen> statsPage = solrTemplate.queryForStatsPage(SpecimenDefinition.SPECIMEN_CORE_NAME, statsQuery, Specimen.class);

        return statsPage.getFieldStatsResult(SpecimenDefinition.PUBLICATION_DAY_FIELD).getMin();
    }


    public SpecimensWithFacetsAndStatsDTO getSpecimensWithFacetsAndStatsByVolume (String volumeId){

        Criteria criteria = new Criteria(SpecimenDefinition.BAR_CODE_FIELD).is(volumeId).and(SpecimenDefinition.NUM_EXISTS_FIELD).is(true);

        StatsOptions statsOptions = new StatsOptions();
        statsOptions.addField(SpecimenDefinition.NUMBER_FIELD);
        statsOptions.addField(SpecimenDefinition.PUBLICATION_DAY_FIELD);
        statsOptions.addField(SpecimenDefinition.PAGES_COUNT_FIELD);


        SimpleQuery statsQuery = new SimpleQuery(criteria);
        statsQuery.setRows(0);
        statsQuery.setStatsOptions(statsOptions);
        //Execute query with params
        StatsPage<Specimen> statsPage = solrTemplate.queryForStatsPage(SpecimenDefinition.SPECIMEN_CORE_NAME, statsQuery, Specimen.class);

        //Get specimens date range
        Object publicationDayMin = statsPage.getFieldStatsResult(SpecimenDefinition.PUBLICATION_DAY_FIELD).getMin();
        Object publicationDayMax = statsPage.getFieldStatsResult(SpecimenDefinition.PUBLICATION_DAY_FIELD).getMax();

        //Get pages number range
        Object numberMin = statsPage.getFieldStatsResult(SpecimenDefinition.NUMBER_FIELD).getMin();
        Object numberMax = statsPage.getFieldStatsResult(SpecimenDefinition.NUMBER_FIELD).getMax();

        //Get pages count sum
        Object pagesCount = statsPage.getFieldStatsResult(SpecimenDefinition.PAGES_COUNT_FIELD).getSum();


        Calendar date = new GregorianCalendar();

        Calendar start = new GregorianCalendar(1700, Calendar.JANUARY, 1);
        Calendar end = new GregorianCalendar(date.get(Calendar.YEAR), Calendar.JANUARY, 1);

        Date startDate = start.getTime();
        Date endDate = end.getTime();

        FacetOptions facetOptions = new FacetOptions();
        facetOptions.addFacetOnField(SpecimenDefinition.MUTATION_FIELD);
        facetOptions.addFacetOnField(SpecimenDefinition.PUBLICATION_MARK_FIELD);
        facetOptions.addFacetOnField(SpecimenDefinition.PUBLICATION_FIELD);
        facetOptions.addFacetOnField(SpecimenDefinition.STATES_FIELD);
        facetOptions.addFacetByRange(new FacetOptions.FieldWithDateRangeParameters(SpecimenDefinition.PUBLICATION_DATE_FIELD, startDate, endDate, "+1YEAR"));

        SimpleFacetQuery facetQuery = new SimpleFacetQuery(criteria);
        facetQuery.setFacetOptions(facetOptions);
        facetQuery.setRows(0);

        FacetPage<Specimen> facetPage = solrTemplate.queryForFacetPage(SpecimenDefinition.SPECIMEN_CORE_NAME, facetQuery, Specimen.class);


        Criteria criteria2 = new Criteria(SpecimenDefinition.BAR_CODE_FIELD).is(volumeId).and(new Criteria(SpecimenDefinition.NUM_EXISTS_FIELD).is(true).or(SpecimenDefinition.NUM_MISSING_FIELD).is(true));
        SimpleQuery simpleQuery = new SimpleQuery(criteria2);
        simpleQuery.setRows(100000);

        Page<Specimen> specimenPage = solrTemplate.queryForPage(SpecimenDefinition.SPECIMEN_CORE_NAME, simpleQuery, Specimen.class);


        return new SpecimensWithFacetsAndStatsDTO(
            publicationDayMin,
            publicationDayMax,
            numberMin,
            numberMax,
            pagesCount,
            facetPage.getFacetResultPage(SpecimenDefinition.MUTATION_FIELD).stream().map(facetFieldEntry -> new FacetFieldDTO(facetFieldEntry.getValue(), facetFieldEntry.getValueCount())).toList(),
            facetPage.getFacetResultPage(SpecimenDefinition.PUBLICATION_MARK_FIELD).stream().map(facetFieldEntry -> new FacetFieldDTO(facetFieldEntry.getValue(), facetFieldEntry.getValueCount())).toList(),
            facetPage.getFacetResultPage(SpecimenDefinition.PUBLICATION_FIELD).stream().map(facetFieldEntry -> new FacetFieldDTO(facetFieldEntry.getValue(), facetFieldEntry.getValueCount())).toList(),
            facetPage.getFacetResultPage(SpecimenDefinition.STATES_FIELD).stream().map(facetFieldEntry -> new FacetFieldDTO(facetFieldEntry.getValue(), facetFieldEntry.getValueCount())).toList(),
            facetPage.getRangeFacetResultPage(SpecimenDefinition.PUBLICATION_DATE_FIELD).stream().map(facetFieldEntry -> new FacetFieldDTO(facetFieldEntry.getValue(), facetFieldEntry.getValueCount())).toList(),
            specimenPage.stream().map(specimenDTOMapper).toList()
        );

    }
}
