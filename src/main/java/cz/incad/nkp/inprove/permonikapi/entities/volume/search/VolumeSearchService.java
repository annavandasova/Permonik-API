package cz.incad.nkp.inprove.permonikapi.entities.volume.search;

import cz.incad.nkp.inprove.permonikapi.base.service.BaseSearchService;
import cz.incad.nkp.inprove.permonikapi.entities.volume.NewVolume;
import cz.incad.nkp.inprove.permonikapi.entities.volume.VolumeRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@Getter
public class VolumeSearchService extends BaseSearchService<NewVolume> {

    private final VolumeRepo repo;

    private final String solrCollection = NewVolume.COLLECTION;

    private final Class<NewVolume> clazz = NewVolume.class;
}
