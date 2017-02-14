package repository;

import hello.UserInfo;
import org.springframework.stereotype.Service;

/**
 * Created by Juan Francisco Piñera on 06/02/2017.
 */
public interface DBService {

    boolean updateInfo(long id, String oldPass, String newPass );
    UserInfo getParticipant(String email, String password);
    void insertUser(UserInfo user);
}
