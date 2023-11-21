package cz.incad.nkp.inprove.permonikapi.entities.user.manager;

import cz.incad.nkp.inprove.permonikapi.base.BaseEntity;
import cz.incad.nkp.inprove.permonikapi.base.service.ManagerService;
import cz.incad.nkp.inprove.permonikapi.entities.user.NewUser;
import cz.incad.nkp.inprove.permonikapi.entities.user.UserRepo;
import cz.incad.nkp.inprove.permonikapi.entities.user.dto.ResetPasswordDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotFoundException;
import java.util.List;

import static cz.incad.nkp.inprove.permonikapi.security.user.UserProducer.getCurrentUser;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserManagerService implements ManagerService<NewUser> {

    @Getter
    private final UserRepo repo;

    public void resetPassword(ResetPasswordDto resetPasswordDto) {
        NewUser currentUser = getCurrentUser();

        if (!DigestUtils.md5Hex(resetPasswordDto.getOldheslo()).equals(currentUser.getHeslo())) {
            throw new ForbiddenException("Old password does not correspond to actual password of user");
        }

        currentUser.setHeslo(DigestUtils.md5Hex(resetPasswordDto.getNewheslo()));
        repo.save(currentUser);
    }

    public void changePassword(String id, String newPassword) {
        NewUser user = repo.findById(id).orElseThrow(() -> new NotFoundException("User with id '" + id + "' not found"));
        user.setHeslo(DigestUtils.md5Hex(newPassword));
        repo.save(user);
    }

    @Override
    public void save(String id, NewUser entity) {
        repo.findById(id).ifPresent(u -> entity.setHeslo(u.getHeslo()));

        ManagerService.super.save(id, entity);
    }

    @Override
    public void saveAll(List<NewUser> entities) {
        List<NewUser> saved = ((Page<NewUser>) repo.findAllById(entities.stream()
                .map(BaseEntity::getId)
                .toList())).getContent();

        saved.forEach(s -> entities.stream()
                .filter(e -> e.getId().equals(s.getId()))
                .forEach(e -> e.setHeslo(s.getHeslo())));

        ManagerService.super.saveAll(entities);
    }
}
