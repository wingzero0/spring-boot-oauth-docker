package kit.personal.ssoserver.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class GPSUser extends User {
    private GPS gps;
    public GPSUser(String username, String password, Collection<? extends GrantedAuthority> authorities, GPS gps){
        super(username, password, authorities);
        this.gps = gps;
    }

    public GPS getGps() {
        return gps;
    }

    public GPSUser setGps(GPS gps) {
        this.gps = gps;
        return this;
    }
}
