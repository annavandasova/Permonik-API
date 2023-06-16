package cz.incad.nkp.inprove.permonikapi.metaTitle;

import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaTitleRepository extends SolrCrudRepository<MetaTitle, String> {


}
