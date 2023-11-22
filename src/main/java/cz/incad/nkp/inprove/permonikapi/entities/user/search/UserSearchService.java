package cz.incad.nkp.inprove.permonikapi.entities.user.search;

import cz.incad.nkp.inprove.permonikapi.base.service.BaseSearchService;
import cz.incad.nkp.inprove.permonikapi.entities.user.NewUser;
import cz.incad.nkp.inprove.permonikapi.entities.user.UserRepo;
import cz.incad.nkp.inprove.permonikapi.security.user.UserProducer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@Getter
public class UserSearchService extends BaseSearchService<NewUser> {

    private final UserRepo repo;

    private final String solrCollection = NewUser.COLLECTION;

    private final Class<NewUser> clazz = NewUser.class;

    public NewUser getCurrentUser() {
        return UserProducer.getCurrentUser();
    }
}
