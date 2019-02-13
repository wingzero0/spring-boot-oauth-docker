package kit.personal.ssoserver.repo;

import kit.personal.ssoserver.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    List<Role> findAllByFuncNo(Integer funcNo);
    List<Role> findAllByApp(String app);
    Page<Role> findAllByApp(String app, Pageable pageable);
    List<Role> findAllByAppAndRole(String app, String role);
    List<Role> findAllByAppAndFuncNo(String app, Integer funcNo);
    long countByAppAndFuncNoAndRole(String app, Integer funcNo, String role);
    Long deleteByAppAndFuncNoAndRole(String app, Integer funcNo, String role);
}
