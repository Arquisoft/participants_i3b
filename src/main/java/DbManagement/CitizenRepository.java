package DbManagement;

import hello.Citizen;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Juan Francisco Piñera on 06/02/2017.
 */
@Repository
public interface CitizenRepository extends MongoRepository<Citizen, String> {

    Citizen findByFirstName(String firstName);

    Citizen findByEmail(String email);


}
