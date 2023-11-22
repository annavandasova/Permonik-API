package cz.incad.nkp.inprove.permonikapi.entities.calendar.manager;

import cz.incad.nkp.inprove.permonikapi.base.api.ApiResource;
import cz.incad.nkp.inprove.permonikapi.base.api.ManagerApi;
import cz.incad.nkp.inprove.permonikapi.entities.calendar.Calendar;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cz.incad.nkp.inprove.permonikapi.security.permission.ResourcesConstants.CALENDAR;

@ApiResource(CALENDAR)
@RestController
@RequestMapping("/api/v2/calendar")
@Tag(name = "Calendar Manager API", description = "API for managing calendar")
@RequiredArgsConstructor
public class CalendarManagerApi implements ManagerApi<Calendar> {

    @Getter
    private final CalendarManagerService service;

}
