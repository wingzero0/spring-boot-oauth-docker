package kit.personal.ssoserver.repo;


import kit.personal.ssoserver.entity.AppUserActing;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface AppUserActingRepository extends CrudRepository<AppUserActing, Integer> {
    AppUserActing findOneByFromDateAndToDateAndUsernameAndActingForUsername(Date fromDate, Date toDate, String username, String actingForUsername);
}
