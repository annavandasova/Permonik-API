package cz.incad.nkp.inprove.permonikapi.entities.volume;

import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolumeRepo extends SolrCrudRepository<NewVolume, String> {

}
