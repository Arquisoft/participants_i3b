package DbManagement;

/**
 * Created by Juan Francisco Piñera on 06/02/2017.
 */
public class SingletonDBManagement {
    private static DBManagement ourInstance = new DBManagementClass();

    public static DBManagement getInstance() {
        return ourInstance;
    }

    private SingletonDBManagement() {
    }
}
