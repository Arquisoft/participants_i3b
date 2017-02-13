package DbManagement;

import hello.Citizen;

/**
 * Created by Juan Francisco Piñera on 13/02/2017.
 */
public interface DBManagementService {
    boolean updateInfo(long id, String oldPass, String newPass);

    Citizen getParticipant(String email, String password);
}
