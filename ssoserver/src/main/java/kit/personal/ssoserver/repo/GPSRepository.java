package kit.personal.ssoserver.repo;

import kit.personal.ssoserver.entity.GPS;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface GPSRepository extends CrudRepository<GPS, Integer> {
    GPS findOneByFuncNo(Integer funcNo);
    GPS findOneByEmail(String email);
    List<GPS> findAllByFuncNoIn(Collection<Integer> funcNos);
}
