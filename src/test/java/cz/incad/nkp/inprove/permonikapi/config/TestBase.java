package cz.incad.nkp.inprove.permonikapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.incad.nkp.inprove.permonikapi.entities.exemplar.Exemplar;
import cz.incad.nkp.inprove.permonikapi.entities.exemplar.ExemplarRepo;
import cz.incad.nkp.inprove.permonikapi.entities.user.NewUser;
import cz.incad.nkp.inprove.permonikapi.entities.user.UserRepo;
import cz.incad.nkp.inprove.permonikapi.entities.user.search.UserSearchService;
import cz.incad.nkp.inprove.permonikapi.parser.QueryParser;
import cz.incad.nkp.inprove.permonikapi.Initializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

@SpringBootTest(classes = Initializer.class)
@ActiveProfiles("test")
public abstract class TestBase {

    @Autowired
    protected UserSearchService userSearchService;

    @Autowired
    protected UserRepo userRepo;

    @Autowired
    protected QueryParser criteriaDtoParser;

    @Autowired
    protected ExemplarRepo exemplarRepo;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected SolrTemplate solrTemplate;

    protected NewUser getSamplePerson() {
        NewUser user = NewUser.builder()
                .id("testuser")
                .email("test@mail.com")
                .username("testuser")
                .nazev("Testing User")
                .heslo("password")
                .role("ADMIN")
                .poznamka("Some note about user")
                .owner("NKP")
                .indextime(new Date())
                .active(true)
                .build();

        return userRepo.save(user);
    }

    protected Exemplar getSampleExemplar() {
        Exemplar exemplar = Exemplar.builder()
                .id("testexemplar")
                .nazev("testexemplar")
                .poznamka("Some note about exemplar")
                .pocet_stran(5)
                .id_issue("id_issue")
                .id_titul("id_titul")
                .build();

        return exemplarRepo.save(exemplar);
    }
}
