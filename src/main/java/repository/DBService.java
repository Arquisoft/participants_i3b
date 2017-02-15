package repository;

import hello.UserInfo;

public interface DBService {

    boolean updateInfo(long id, String oldPass, String newPass );
    UserInfo getParticipant(String email, String password);
    void insertUser(UserInfo user);
}
