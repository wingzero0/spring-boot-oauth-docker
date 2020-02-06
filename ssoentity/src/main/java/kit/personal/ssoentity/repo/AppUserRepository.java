package kit.personal.ssoentity.repo;

import kit.personal.ssoentity.entity.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
    AppUser findOneByUsername(String username);
    AppUser findOneByEmail(String email);
    List<AppUser> findAllByUsernameIn(Collection<String> usernames);
}
