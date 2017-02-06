package DbManagement;

import hello.UserInfo;

/**
 * Created by Juan Francisco Piñera on 06/02/2017.
 */
public interface DBManagement {

    boolean updateInfo(long id, String oldPass, String newPass );
    UserInfo getParticipant(String email, String password);
}
