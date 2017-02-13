package DbManagement;

import hello.UserInfo;
import org.springframework.stereotype.Service;

/**
 * Created by Juan Francisco Piñera on 06/02/2017.
 */
@Service
public interface DBService {

    boolean updateInfo(long id, String oldPass, String newPass );
    UserInfo getParticipant(String email, String password);
}
