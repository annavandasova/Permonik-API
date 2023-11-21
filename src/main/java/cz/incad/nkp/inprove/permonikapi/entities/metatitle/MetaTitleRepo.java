package cz.incad.nkp.inprove.permonikapi.entities.metatitle;

import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaTitleRepo extends SolrCrudRepository<NewMetaTitle, String> {

    Iterable<NewMetaTitle> findAllByPoznamka(String poznamka);
}
