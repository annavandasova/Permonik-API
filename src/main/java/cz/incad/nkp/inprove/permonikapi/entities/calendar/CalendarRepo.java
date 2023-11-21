package cz.incad.nkp.inprove.permonikapi.entities.calendar;

import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepo extends SolrCrudRepository<Calendar, String> {

}
