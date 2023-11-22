package cz.incad.nkp.inprove.permonikapi.entities.user;

import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends SolrCrudRepository<NewUser, String> {

    NewUser findByUsernameIgnoreCase(String username);
}
