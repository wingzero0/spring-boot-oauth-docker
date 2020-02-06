package kit.personal.ssoentity.repo;

import kit.personal.ssoentity.entity.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
    AppUser findOneByUsername(String username);
    AppUser findOneByEmail(String email);
    List<AppUser> findAllByUsernameIn(Collection<String> usernames);
}
