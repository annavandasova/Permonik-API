package cz.incad.nkp.inprove.permonikapi.entities.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.incad.nkp.inprove.permonikapi.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@SolrDocument(collection = NewUser.COLLECTION)
public class NewUser extends BaseEntity implements Serializable {

    public static final String COLLECTION = "user";

    @Indexed(name = "email", type = "string")
    private String email;

    @Indexed(name = "username", type = "string")
    private String username;

    @Indexed(name = "nazev", type = "string")
    private String nazev;

    @JsonIgnore
    @Indexed(name = "heslo", type = "string")
    private String heslo;

    @Indexed(name = "role", type = "string")
    private String role;

    @Indexed(name = "active", type = "boolean")
    private Boolean active;

    @Indexed(name = "poznamka", type = "string")
    private String poznamka;

    @Indexed(name = "owner", type = "string")
    private String owner;

    @Indexed(name = "indextime", type = "pdate")
    private Date indextime;
}
