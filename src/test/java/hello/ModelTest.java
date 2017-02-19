package hello;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import repository.DBService;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by guille on 17/02/2017.
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan("repository")
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class ModelTest {
    @Value("${local.server.port}")
    private int port;

    private URL base;
    private RestTemplate template;

    @Autowired
    private DBService db;

    private UserInfo user;
    private CitizenLogin citizenLogin;
    private CitizenDTO citizenDTO;


    @SuppressWarnings("deprecation")
    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
        //noinspection deprecation
        template = new TestRestTemplate();
    }

    @Test
    public void getLanding() throws Exception {
        String userURI = base.toString() + "/user";
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertThat(response.getBody(), containsString("Hola"));
    }

    @Test
    public void testUserInfo() throws Exception {
        String password, firstName, lastName, email, address, nationality, ID, NIF;
        Integer pollingStation;
        String birthDate;
        firstName = "name";
        lastName = "lastName";
        password = "password";
        email = "mail@mail.com";
        birthDate = "17/09/1990";
        address = "Calle";
        nationality = "Ingles";
        NIF = "10203040A";
        pollingStation = 123;
        ID = "@irsohgjisejgoiesgj";
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        UserInfo user = new UserInfo(password, firstName, lastName, email, format.parse(birthDate));
        UserInfo user2 = new UserInfo(firstName,lastName, email, format.parse(birthDate));
        UserInfo user3 = new UserInfo(firstName, lastName, email, birthDate, address, nationality, ID, NIF, pollingStation.toString());

        assertTrue(user.getPassword().equals(password));
        assertTrue(user.getFirstName().equals(firstName));
        assertTrue(user.getLastName().equals(lastName));
        assertTrue(user.getEmail().equals(email));
        assertTrue(user.getBirthDate().equals(format.parse(birthDate)));
        assertTrue(user2.getFirstName().equals(firstName));
        assertTrue(user2.getLastName().equals(lastName));
        assertTrue(user2.getEmail().equals(email));
        assertTrue(user2.getBirthDate().equals(format.parse(birthDate)));
        assertTrue(user3.getFirstName().equals(firstName));
        assertTrue(user3.getLastName().equals(lastName));
        assertTrue(user3.getEmail().equals(email));
        assertTrue(user3.getBirthDate().equals(format.parse(birthDate)));
        assertTrue(user3.getAddress().equals(address));
        assertTrue(user3.getNationality().equals(nationality));
        assertTrue(user3.getId().equals(ID));
        assertTrue(user3.getNIF().equals(NIF));
        assertEquals((Integer)user3.getPollingStation(),pollingStation);


    }

}
