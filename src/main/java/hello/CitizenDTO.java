package hello;

public class CitizenDTO {

    public String firstName;
    public String lastName;
    public Integer age;
    public String NIF;
    public String email;

    public CitizenDTO() {}

    public CitizenDTO(UserInfo user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.age = user.getAge();
        this.NIF = user.getNIF();
        this.email = user.getEmail();
    }
}
