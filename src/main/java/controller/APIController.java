package controller;


import hello.CitizenLogin;
import hello.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repository.DBService;

@RestController
public class APIController {

    private final DBService service;

    @Autowired
    APIController(DBService service) {
        this.service = service;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public UserInfo user(@RequestBody CitizenLogin login) {
        //Try/Catch: If participant is not found, create a dummy user and return it (to test it is good)

        UserInfo user;
        try {
            user = service.getParticipant(login.getLogin(), login.getPassword());
        } catch (Exception e) {
            String firstName = "dummy";
            String pass = "dummy";
            String lastName = "dummy";
            String emai = "dummy@dummy.es";
            int age = 0;

            user = new UserInfo(pass, firstName, lastName, emai, age);
            service.insertUser(user);
        }
        return user;

    }

}