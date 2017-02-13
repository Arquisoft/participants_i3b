package DbManagement;

import hello.Citizen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Juan Francisco Piñera on 06/02/2017.
 */
@Service
public class DBManagementServiceImpl implements DBManagementService {

    @Autowired
    CitizenRepository repository;

    @Override
    public boolean updateInfo(long id, String oldPass, String newPass) {
        return false;
    }

    @Override
    public Citizen getParticipant(String email, String password) {
        Citizen citizen = repository.findByEmail(email);
        if (citizen.getPassword().equals(password))
            return citizen;
        else
            return null;
    }
}
