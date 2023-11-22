package cz.incad.nkp.inprove.permonikapi.security.user;

import cz.incad.nkp.inprove.permonikapi.entities.user.NewUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class UserProducer {

    private UserProducer() {}

    public static UserDelegate getCurrentUserDelegate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication != null ? authentication.getPrincipal() : null;
        return principal instanceof UserDelegate ? (UserDelegate) principal : null;
    }

    public static NewUser getCurrentUser() {
        UserDelegate currentUserDelegate = getCurrentUserDelegate();
        return currentUserDelegate != null ? currentUserDelegate.getUser() : null;
    }
}
