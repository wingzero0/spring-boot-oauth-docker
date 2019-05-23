package kit.personal.ssoserver.repo;

import kit.personal.ssoserver.entity.ActingRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActingRoleRepository extends CrudRepository<ActingRole, Integer> {
    List<ActingRole> findAllByPkUsername(String username);
    List<ActingRole> findAllByAppIdAndPkUsername(String appId, String username);
    List<ActingRole> findAllByAppIdAndAppRole(String appId, String AppRole);
}
