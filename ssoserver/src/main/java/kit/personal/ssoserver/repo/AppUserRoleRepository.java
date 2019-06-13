package kit.personal.ssoserver.repo;

import kit.personal.ssoserver.entity.AppUserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface AppUserRoleRepository extends CrudRepository<AppUserRole, Integer> {
    List<AppUserRole> findAllByUsername(String username);
    List<AppUserRole> findAllByAppId(String app);
    Page<AppUserRole> findAllByAppId(String app, Pageable pageable);
    List<AppUserRole> findAllByAppIdAndAppRole(String appId, String role);
    List<AppUserRole> findAllByAppIdAndUsername(String appId, String username);
    List<AppUserRole> findAllByAppIdAndUsernameIn(String appId, Collection<String> usernames);
    long countByAppIdAndUsernameAndAppRole(String appId, String username, String role);
    Long deleteByAppIdAndUsernameAndAppRole(String appId, String username, String role);
}
