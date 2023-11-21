package cz.incad.nkp.inprove.permonikapi.entities.metatitle.search;

import cz.incad.nkp.inprove.permonikapi.base.service.BaseSearchService;
import cz.incad.nkp.inprove.permonikapi.entities.metatitle.NewMetaTitle;
import cz.incad.nkp.inprove.permonikapi.entities.metatitle.MetaTitleRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@Getter
public class MetaTitleSearchService extends BaseSearchService<NewMetaTitle> {

    private final MetaTitleRepo repo;

    private final String solrCollection = NewMetaTitle.COLLECTION;

    private final Class<NewMetaTitle> clazz = NewMetaTitle.class;
}
