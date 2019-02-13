package kit.personal.ssoserver.repo;

import kit.personal.ssoserver.entity.SubstituicaoRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubstituicaoRoleRepository extends CrudRepository<SubstituicaoRole, Integer> {
    List<SubstituicaoRole> findAllByPkFuncNo(Integer funcNo);
    List<SubstituicaoRole> findAllByAppAndPkFuncNo(String app, Integer funcNo);
    List<SubstituicaoRole> findAllByAppAndRole(String app, String role);
}
