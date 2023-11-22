package cz.incad.nkp.inprove.permonikapi.originentities.metatitle;

import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaTitleRepository extends SolrCrudRepository<MetaTitle, String> {


}
