package DbManagement;

import hello.UserInfo;
import org.springframework.data.repository.Repository;

/**
 * Created by Juan Francisco Piñera on 06/02/2017.
 */
public interface UserInfoRepository extends Repository<UserInfo, String> {

    UserInfo findByFirstName(String firstName);

    UserInfo findByEmail(String email);


}
