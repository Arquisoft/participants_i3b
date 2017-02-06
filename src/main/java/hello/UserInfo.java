package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserInfo {
	
	// Log
	private static final Logger LOG = LoggerFactory.getLogger(UserInfo.class);

    @Id
    private final long ID;

    private final String login;
    private final String password;

    private final String firstName;
    private final String lastName;
    private final String email;
    private final Integer age;



    public UserInfo(String name, long id, String login, String password, String firstName, String lastName, String email, Integer age) {
        ID = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public long getID() {
        return ID;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }
}