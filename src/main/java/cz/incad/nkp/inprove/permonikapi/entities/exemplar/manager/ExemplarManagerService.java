package cz.incad.nkp.inprove.permonikapi.entities.exemplar.manager;

import cz.incad.nkp.inprove.permonikapi.base.service.ManagerService;
import cz.incad.nkp.inprove.permonikapi.entities.exemplar.Exemplar;
import cz.incad.nkp.inprove.permonikapi.entities.exemplar.ExemplarRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExemplarManagerService implements ManagerService<Exemplar> {

    @Getter
    private final ExemplarRepo repo;
}
