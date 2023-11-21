package cz.incad.nkp.inprove.permonikapi.entities.user.manager;

import cz.incad.nkp.inprove.permonikapi.base.api.ApiResource;
import cz.incad.nkp.inprove.permonikapi.base.api.ManagerApi;
import cz.incad.nkp.inprove.permonikapi.entities.user.NewUser;
import cz.incad.nkp.inprove.permonikapi.entities.user.dto.ResetPasswordDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static cz.incad.nkp.inprove.permonikapi.security.permission.ResourcesConstants.USER;

@ApiResource(USER)
@RestController
@RequestMapping("/api/v2/user")
@Tag(name = "User Manager API", description = "API for managing users")
@RequiredArgsConstructor
public class UserManagerApi implements ManagerApi<NewUser> {

    @Getter
    private final UserManagerService service;

    @PostMapping("/reset-password")
    @Operation(summary = "Resets password of currently logged-in user")
    @PreAuthorize("isAuthenticated()")
    public void resetPassword(@RequestBody ResetPasswordDto dto) {
        service.resetPassword(dto);
    }

    @PutMapping("/{id}/change-password")
    @Operation(summary = "Changes password of user")
    @PreAuthorize("hasRole('ADMIN')")
    public void changePassword(@PathVariable String id, @RequestBody String newPassword) {
        service.changePassword(id, newPassword);
    }

}
