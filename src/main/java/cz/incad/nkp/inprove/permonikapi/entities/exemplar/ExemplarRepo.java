package cz.incad.nkp.inprove.permonikapi.entities.exemplar;

import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemplarRepo extends SolrCrudRepository<Exemplar, String> {

}
