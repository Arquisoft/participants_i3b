package hello;


import DbManagement.SingletonDBManagement;
import org.springframework.web.bind.annotation.*;

@RestController
public class APIController {

    @RequestMapping(value="/user", method = RequestMethod.POST)
    @ResponseBody
    public UserInfo user(@RequestBody CitizenLogin login) {
        //Hay que cambiar esto. Debería devolver el usuario si valida email y contraseña.

        UserInfo user = SingletonDBManagement.getInstance().getParticipant(login.getLogin(), login.getPassword());
        if (user != null) {
            return user;
        } else {
            //throw new IllegalArgumentException("No existe esa combinación de usuario y contraseña");
            String firstName = "dummy";
            long ID = 0;
            String pass = "dummy";
            String lastName = "dummy";
            String emai = "dummy@dummy.es";
            int age = 0;

            return new UserInfo(ID, pass, firstName, lastName, emai, age);
        }
    }

}