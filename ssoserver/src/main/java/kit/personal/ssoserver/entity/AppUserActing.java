package kit.personal.ssoserver.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;
import java.util.Date;

@Entity
public class AppUserActing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    private Date fromDate;
    private Date toDate;
    private String username;
    private String actingForUsername;

    public BigInteger getId() {
        return id;
    }

    public AppUserActing setId(BigInteger id) {
        this.id = id;
        return this;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public AppUserActing setFromDate(Date fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    public Date getToDate() {
        return toDate;
    }

    public AppUserActing setToDate(Date toDate) {
        this.toDate = toDate;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AppUserActing setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getActingForUsername() {
        return actingForUsername;
    }

    public AppUserActing setActingForUsername(String actingForUsername) {
        this.actingForUsername = actingForUsername;
        return this;
    }
}
