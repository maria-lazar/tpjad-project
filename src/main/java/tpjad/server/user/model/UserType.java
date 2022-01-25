package tpjad.server.user.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_type")
public class UserType implements GrantedAuthority, Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String authority;

    public static final String SUPERAGENT = "SUPERAGENT";
    public static final String DISPATCHER = "DISPATCHER";
    public static final String POLICEMAN = "POLICEMAN";
    public static final String DOCTOR = "DOCTOR";
    public static final String FIREFIGHTER = "FIREFIGHTER";

    public UserType() {
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserType(String authority) {
        this.authority = authority;
    }

    public UserType(String id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
