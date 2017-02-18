package hello;

import controller.APIController;
import org.apache.tomcat.jni.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import repository.DBService;
import repository.DBServiceClass;

import java.net.URL;
import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by guille on 17/02/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan("repository")
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class APIControllerTest {
    @Value("${local.server.port}")
    private int port;

    private URL base;
    private RestTemplate template;

    @Autowired
    private DBService db;


    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
        template = new TestRestTemplate();
    }

    @Test
    public void getLanding() throws Exception {
        String userURI = base.toString() + "/user";
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertThat(response.getBody(), containsString("Hola"));
    }

    @Test
    public void testDatabase() throws Exception{
        UserInfo user = new UserInfo("pass2", "name", "surname", "ma@il2.com", new Date());
        db.insertUser(user);
        UserInfo userFromDB = db.getParticipant("ma@il2.com","pass2");
        assertThat(user.getEmail(), equalTo(userFromDB.getEmail()));
        assertThat(user.getPassword(), equalTo(userFromDB.getPassword()));
    }

    /*
    @Test
    public void testUser() throws Exception {
        UserInfo user = new UserInfo("pass", "name", "surname", "ma@il.com", new Date());
        db.insertUser(user);
        String request = "{ login: \"ma@il.com\", password: \"pass\"}";


        String userURI = base.toString() + "/user";
        ResponseEntity<CitizenDTO> response = template.postForEntity(userURI, request, CitizenDTO.class);
        CitizenDTO expected = new CitizenDTO(user);
        assertThat(response.getBody().getEmail(), equalTo(expected.getEmail())); //test si el email es igual
        //(response.getBody(), equalTo(expected));
        //assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        //assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));



    }
    */
}
