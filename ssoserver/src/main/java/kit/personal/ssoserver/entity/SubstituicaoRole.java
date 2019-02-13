package kit.personal.ssoserver.entity;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
public class SubstituicaoRole {
    @EmbeddedId
    private Pk pk;
    private String app;
    private String role;

    @Embeddable
    public static class Pk implements Serializable {
        private static final long serialVersionUID = 1L;
        private Integer funcNo;
        private Date inicio;
        private Date fim;
        private Integer noChefe;

        public Integer getFuncNo() {
            return funcNo;
        }

        public Pk setFuncNo(Integer funcNo) {
            this.funcNo = funcNo;
            return this;
        }

        public Date getInicio() {
            return inicio;
        }

        public Pk setInicio(Date inicio) {
            this.inicio = inicio;
            return this;
        }

        public Date getFim() {
            return fim;
        }

        public Pk setFim(Date fim) {
            this.fim = fim;
            return this;
        }

        public Integer getNoChefe() {
            return noChefe;
        }

        public Pk setNoChefe(Integer noChefe) {
            this.noChefe = noChefe;
            return this;
        }
    }

    public Pk getPk() {
        return pk;
    }
    public void setPk(Pk pk) {
        this.pk = pk;
    }

    public String getApp() {
        return app;
    }

    public SubstituicaoRole setApp(String app) {
        this.app = app;
        return this;
    }

    public String getRole() {
        return role;
    }

    public SubstituicaoRole setRole(String role) {
        this.role = role;
        return this;
    }
}
