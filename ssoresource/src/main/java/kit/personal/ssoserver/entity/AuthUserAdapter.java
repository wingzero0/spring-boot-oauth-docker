package kit.personal.ssoserver.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AuthUserAdapter extends User {
    private AppUser appUser;
    public AuthUserAdapter(String username, String password, Collection<? extends GrantedAuthority> authorities, AppUser gps){
        super(username, password, authorities);
        this.appUser = gps;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public AuthUserAdapter setAppUser(AppUser appUser) {
        this.appUser = appUser;
        return this;
    }
}
