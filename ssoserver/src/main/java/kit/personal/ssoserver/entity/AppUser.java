package kit.personal.ssoserver.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigInteger;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class AppUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @JsonView(EntityJsonView.PUBLIC_VIEW.class)
    private String username;
    private String password;
    @JsonView(EntityJsonView.PUBLIC_VIEW.class)
    private String email;

    public BigInteger getId() {
        return id;
    }

    public AppUser setId(BigInteger id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AppUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AppUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AppUser setEmail(String email) {
        this.email = email;
        return this;
    }
}
