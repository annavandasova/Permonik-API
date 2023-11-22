package cz.incad.nkp.inprove.permonikapi.entities.user.search;

import cz.incad.nkp.inprove.permonikapi.base.api.ApiResource;
import cz.incad.nkp.inprove.permonikapi.base.api.SearchApi;
import cz.incad.nkp.inprove.permonikapi.entities.user.NewUser;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cz.incad.nkp.inprove.permonikapi.security.permission.ResourcesConstants.USER;

@ApiResource(USER)
@RestController
@RequestMapping("/api/v2/user")
@Tag(name = "User Search API", description = "API for retrieving users")
@RequiredArgsConstructor
public class UserSearchApi implements SearchApi<NewUser> {

    @Getter
    private final UserSearchService service;

    @GetMapping("/current")
    public NewUser getCurrentUser() {
        return service.getCurrentUser();
    }
}
