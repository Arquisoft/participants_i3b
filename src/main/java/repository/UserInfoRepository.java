package repository;

import hello.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Juan Francisco Piñera on 06/02/2017.
 */
@Repository
public interface UserInfoRepository extends MongoRepository<UserInfo, String> {

    UserInfo insert(UserInfo user);

    UserInfo findByEmail(String email);


}
