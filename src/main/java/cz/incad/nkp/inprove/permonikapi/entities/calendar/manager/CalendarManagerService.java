package cz.incad.nkp.inprove.permonikapi.entities.calendar.manager;

import cz.incad.nkp.inprove.permonikapi.base.service.ManagerService;
import cz.incad.nkp.inprove.permonikapi.entities.calendar.Calendar;
import cz.incad.nkp.inprove.permonikapi.entities.calendar.CalendarRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CalendarManagerService implements ManagerService<Calendar> {

    @Getter
    private final CalendarRepo repo;
}
