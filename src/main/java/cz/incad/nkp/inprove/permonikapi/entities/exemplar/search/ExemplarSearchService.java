package cz.incad.nkp.inprove.permonikapi.entities.exemplar.search;

import cz.incad.nkp.inprove.permonikapi.base.service.BaseSearchService;
import cz.incad.nkp.inprove.permonikapi.entities.exemplar.Exemplar;
import cz.incad.nkp.inprove.permonikapi.entities.exemplar.ExemplarRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@Getter
public class ExemplarSearchService extends BaseSearchService<Exemplar> {

    private final ExemplarRepo repo;

    private final String solrCollection = Exemplar.COLLECTION;

    private final Class<Exemplar> clazz = Exemplar.class;
}
