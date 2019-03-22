package kit.personal.ssoserver.entity;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
public class ActingRole {
    @EmbeddedId
    private Pk pk;
    private String appId;
    private String appRole;

    @Embeddable
    public static class Pk implements Serializable {
        private static final long serialVersionUID = 1L;
        private Date fromDate;
        private Date toDate;
        private Integer username;
        private Integer actingForUsername;

        public Date getFromDate() {
            return fromDate;
        }

        public void setFromDate(Date fromDate) {
            this.fromDate = fromDate;
        }

        public Date getToDate() {
            return toDate;
        }

        public void setToDate(Date toDate) {
            this.toDate = toDate;
        }

        public Integer getUsername() {
            return username;
        }

        public void setUsername(Integer username) {
            this.username = username;
        }

        public Integer getActingForUsername() {
            return actingForUsername;
        }

        public void setActingForUsername(Integer actingForUsername) {
            this.actingForUsername = actingForUsername;
        }
    }

    public Pk getPk() {
        return pk;
    }
    public void setPk(Pk pk) {
        this.pk = pk;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppRole() {
        return appRole;
    }

    public ActingRole setAppRole(String appRole) {
        this.appRole = appRole;
        return this;
    }
}
