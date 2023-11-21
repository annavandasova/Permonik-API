package cz.incad.nkp.inprove.permonikapi.entities.volume.manager;

import cz.incad.nkp.inprove.permonikapi.base.service.ManagerService;
import cz.incad.nkp.inprove.permonikapi.entities.volume.NewVolume;
import cz.incad.nkp.inprove.permonikapi.entities.volume.VolumeRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VolumeManagerService implements ManagerService<NewVolume> {

    @Getter
    private final VolumeRepo repo;
}
