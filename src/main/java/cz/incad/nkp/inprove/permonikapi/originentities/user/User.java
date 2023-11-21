package cz.incad.nkp.inprove.permonikapi.originentities.user;

import nonapi.io.github.classgraph.json.Id;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.Objects;

import static cz.incad.nkp.inprove.permonikapi.originentities.user.UserDefinition.USER_CORE_NAME;

@SolrDocument(collection = USER_CORE_NAME)
public class User implements UserDefinition {

    @Id @Indexed(value = ID_FIELD, required = true)
    private String id;

    @Indexed(ACTIVE_FIELD)
    private Boolean active;

    @Field(EMAIL_FIELD)
    private String email;

    @Indexed(PASSWORD_FIELD)
    private String password;

    @Indexed(NAME_FIELD)
    private String name;

    @Indexed(OWNER_FIELD)
    private String owner;

    @Field(NOTE_FIELD)
    private String note;

    @Indexed(ROLE_FIELD)
    private String role;

    @Indexed(USER_NAME_FIELD)
    private String userName;

    public User() {
    }

    public User(String id, Boolean active, String email, String password, String name, String owner, String note, String role, String userName) {
        this.id = id;
        this.active = active;
        this.email = email;
        this.password = password;
        this.name = name;
        this.owner = owner;
        this.note = note;
        this.role = role;
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(active, user.active) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(name, user.name) && Objects.equals(owner, user.owner) && Objects.equals(note, user.note) && Objects.equals(role, user.role) && Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, active, email, password, name, owner, note, role, userName);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", active=" + active +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", note='" + note + '\'' +
                ", role='" + role + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
