package kit.personal.ssoserver.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class GPSUser extends User {
    private AppUser gps;
    public GPSUser(String username, String password, Collection<? extends GrantedAuthority> authorities, AppUser gps){
        super(username, password, authorities);
        this.gps = gps;
    }

    public AppUser getGps() {
        return gps;
    }

    public GPSUser setGps(AppUser gps) {
        this.gps = gps;
        return this;
    }
}
