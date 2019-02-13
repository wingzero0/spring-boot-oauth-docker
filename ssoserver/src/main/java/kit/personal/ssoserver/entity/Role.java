package kit.personal.ssoserver.entity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_generator")
    @SequenceGenerator(name = "role_generator", sequenceName = "ROLE_SEQ", allocationSize = 1)
    private BigInteger id;
    private Integer funcNo;
    private String app;
    private String role;

    public BigInteger getId() {
        return id;
    }

    public Role setId(BigInteger id) {
        this.id = id;
        return this;
    }

    public Integer getFuncNo() {
        return funcNo;
    }

    public Role setFuncNo(Integer funcNo) {
        this.funcNo = funcNo;
        return this;
    }

    public String getApp() {
        return app;
    }

    public Role setApp(String app) {
        this.app = app;
        return this;
    }

    public String getRole() {
        return role;
    }

    public Role setRole(String role) {
        this.role = role;
        return this;
    }
}
