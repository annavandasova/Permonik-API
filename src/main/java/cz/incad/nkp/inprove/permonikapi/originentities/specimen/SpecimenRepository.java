package cz.incad.nkp.inprove.permonikapi.originentities.specimen;

import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface SpecimenRepository extends SolrCrudRepository<Specimen, String> {


}
