package cz.incad.nkp.inprove.permonikapi.specimen;

//import org.apache.solr.client.solrj.beans.Field;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.solr.core.SolrTemplate;
//import org.springframework.data.solr.core.query.GroupOptions;
//import org.springframework.data.solr.core.query.SimpleQuery;
//import org.springframework.data.solr.core.query.StatsOptions;
//import org.springframework.data.solr.core.query.result.FacetPage;
//import org.springframework.data.solr.core.query.result.FieldStatsResult;
//import org.springframework.data.solr.core.query.result.StatsPage;
//import org.springframework.data.solr.repository.Facet;
//import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
//import org.springframework.data.solr.repository.Stats;
import org.springframework.stereotype.Repository;



@Repository
public interface SpecimenRepository extends SolrCrudRepository<Specimen, String> {

//    @Query(value = "numExists:true AND id_titul:?0")
//    Page<Specimen> getSpecimensOverview(StatsOptions statsOptions, GroupOptions groupOptions, Pageable pageable, String idTitle);

}
