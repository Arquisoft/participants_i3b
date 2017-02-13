package DbManagement;

import hello.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Juan Francisco Piñera on 06/02/2017.
 */
@Service
public class DBServiceClass implements DBService {

    @Autowired
    UserInfoRepository repository;

    @Override
    public boolean updateInfo(long id, String oldPass, String newPass) {
        return false;
    }

    @Override
    public UserInfo getParticipant(String email, String password) {
        UserInfo user = repository.findByEmail(email);
        if (user.getPassword().equals(password))
            return user;
        else
            return null;
    }
}