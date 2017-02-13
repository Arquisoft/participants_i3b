package DbManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Juan Francisco Piñera on 06/02/2017.
 */
@Component
public class SingletonDBManagement {
    @Autowired
    private static DBService ourInstance;

    public static DBService getInstance() {
        return ourInstance;
    }

    private SingletonDBManagement() {
    }
}
