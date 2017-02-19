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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import repository.DBService;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by guille on 17/02/2017.
 */
@SuppressWarnings("deprecation")
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
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private DBService db;


    @SuppressWarnings("deprecation")
    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
        //noinspection deprecation
        template = new TestRestTemplate();
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getLanding() throws Exception {
        String userURI = base.toString() + "/user";
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertThat(response.getBody(), containsString("Hola"));
    }

    @Test
    public void testDatabase() throws Exception {
        UserInfo user = new UserInfo("pass2", "name", "surname", "ma@il2.com", new Date());
        db.insertUser(user);
        UserInfo userFromDB = db.getParticipant("ma@il2.com", "pass2");
        assertThat(user.getEmail(), equalTo(userFromDB.getEmail()));
        assertThat(user.getPassword(), equalTo(userFromDB.getPassword()));
    }

    /*
        @Test
        public void testUser() throws Exception {
            UserInfo user = new UserInfo("pass", "name", "surname", "ma@il.com", new Date());
            db.insertUser(user);
            String request = "{ \"login\": \"ma@il.com\", \"password\": \"pass\"}";


            String userURI = base.toString() + "/user";
            ResponseEntity<CitizenDTO> response = template.postForEntity(userURI, request, CitizenDTO.class);
            CitizenDTO expected = new CitizenDTO(user);
            assertThat(response.getBody().getEmail(), equalTo(expected.getEmail())); //test si el email es igual
            //(response.getBody(), equalTo(expected));
            //assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
            assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));



        }
    */
    @Test
    public void postTestUser() throws Exception {
        UserInfo user = new UserInfo("pass", "name", "surname", "ma@il.com", new Date());
        db.insertUser(user);
        mockMvc.perform(post("/user")
                .content("{ \"login\": \"ma@il.com\", \"password\": \"pass\"}")
                .contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(),
                        Charset.forName("utf8"))))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().encoding("UTF-8"))
                .andExpect(content().json("{\"firstName\":\"name\",\"lastName\":\"surname\",\"age\":0,\"NIF\":null,\"email\":\"ma@il.com\",\"nif\":null}")
                );
    }

    @Test
    public void postTestNotFoundUser() throws Exception {
        mockMvc.perform(post("/user")
                .content("{ \"login\": \"ma@il.com\", \"password\": \"nothepassword\"}")
                .contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(),
                        Charset.forName("utf8"))))
                .andExpect(status().isNotFound()
                );

    }


}
