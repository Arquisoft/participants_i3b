package hello;


import DbManagement.DBManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@EnableMongoRepositories("DbManagement")
@ComponentScan("DbManagement")
@RestController
public class APIController {

    @Autowired
    private DBManagementService dbManagementService;


    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public Citizen user(@RequestBody CitizenLogin login) {
        //Hay que cambiar esto. Debería devolver el usuario si valida email y contraseña.

        Citizen citizen = dbManagementService.getParticipant(login.getLogin(), login.getPassword());
        if (citizen != null) {
            return citizen;
        } else {
            //throw new IllegalArgumentException("No existe esa combinación de usuario y contraseña");
            String firstName = "dummy";
            long ID = 0;
            String pass = "dummy";
            String lastName = "dummy";
            String emai = "dummy@dummy.es";
            Date age = new Date();

            return new Citizen(firstName, lastName, emai, age);
        }
    }


}