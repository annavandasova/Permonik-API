package cz.incad.nkp.inprove.permonikapi.specimen;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.StatsPage;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.data.solr.repository.Stats;
import org.springframework.stereotype.Repository;



@Repository
public interface SpecimenRepository extends SolrCrudRepository<Specimen, String> {

    @Query("*")
    @Stats(value = {"mutace", "datum_vydani_den", "vlastnik"}, distinct = true)
    StatsPage<Specimen> getSpecimensOverview(Pageable pageable);

}
