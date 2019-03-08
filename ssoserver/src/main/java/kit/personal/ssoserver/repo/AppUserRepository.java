package kit.personal.ssoserver.repo;

import kit.personal.ssoserver.entity.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
    AppUser findOneByFuncNo(Integer funcNo);
    AppUser findOneByEmail(String email);
    List<AppUser> findAllByFuncNoIn(Collection<Integer> funcNos);
}
