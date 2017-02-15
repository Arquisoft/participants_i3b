package controller;


import hello.CitizenLogin;
import hello.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserInfo> user(@RequestBody CitizenLogin login) {
    	// If the combination of email and password is correct, the data of the user is returned
    	// If not, 404 NOT FOUND is returned
    	
        UserInfo user = service.getParticipant(login.getLogin(), login.getPassword());
       
        if (user == null)
        	return new ResponseEntity<UserInfo>(HttpStatus.NOT_FOUND);
        else
        	return new ResponseEntity<UserInfo>(user, HttpStatus.OK);

    }

}