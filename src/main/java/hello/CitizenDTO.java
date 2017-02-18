package hello;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by juanf on 18/02/2017.
 */
public class CitizenDTO {

    public String firstName;
    public String lastName;
    public Integer age;
    public String NIF;
    public String email;

    public CitizenDTO(){}

    public CitizenDTO(UserInfo user){
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.age = user.getAge();
        this.NIF = user.getNIF();
        this.email= user.getEmail();
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getNIF() {
        return NIF;
    }

    public String getEmail() {
        return email;
    }

}
