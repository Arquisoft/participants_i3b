package hello;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "users")
public class UserInfo {

    // Log
    @JsonIgnore
    private static final Logger LOG = LoggerFactory.getLogger(UserInfo.class);

    @JsonIgnore
    @Id
    private String ID;

    @JsonIgnore
    private final String password;

    private final String firstName;
    private final String lastName;
    private final String email;
    private final Integer age;


    public UserInfo(String password, String firstName, String lastName, String email, Integer age) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
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