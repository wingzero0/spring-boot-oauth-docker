package kit.personal.ssoserver.entity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
public class AppUserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    private String username;
    private String appId;
    private String appRole;

    public BigInteger getId() {
        return id;
    }

    public AppUserRole setId(BigInteger id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AppUserRole setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getAppId() {
        return appId;
    }

    public AppUserRole setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getAppRole() {
        return appRole;
    }

    public AppUserRole setAppRole(String role) {
        this.appRole = role;
        return this;
    }
}
