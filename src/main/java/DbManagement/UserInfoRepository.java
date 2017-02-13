package DbManagement;

import hello.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.Repository;

/**
 * Created by Juan Francisco Piñera on 06/02/2017.
 */
public interface UserInfoRepository extends MongoRepository<UserInfo, String> {

    UserInfo findByFirstName(String firstName);

    UserInfo findByEmail(String email);


}
