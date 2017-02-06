package hello;


import DbManagement.SingletonDBManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

    @RequestMapping("/user")
    public UserInfo user(@RequestParam(value="login")String email, @RequestParam (value = "password") String password) {
        //Hay que cambiar esto. Debería devolver el usuario si valida email y contraseña.

        UserInfo user = SingletonDBManagement.getInstance().getParticipant(email, password);
        if (user!= null){
            return user;
        }
        else{
            throw new IllegalArgumentException("No existe esa combinación de usuario y contraseña");
        }
    }

}