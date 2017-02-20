package hello;

<<<<<<< HEAD
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
=======
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
>>>>>>> origin/master
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
<<<<<<< HEAD
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import repository.DBService;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan("repository")
=======
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
>>>>>>> origin/master
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class FormControllerTest {
<<<<<<< HEAD
=======

>>>>>>> origin/master
    @Value("${local.server.port}")
    private int port;

    private URL base;
    private RestTemplate template;
<<<<<<< HEAD
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private DBService db;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
        template = new TestRestTemplate();
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testLoginPage() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertThat(response.getBody(), containsString("Hola"));
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Username:")))
                .andExpect(content().string(containsString("Password:")));
    }

    @Test
    public void testLoginCorrect() throws Exception {
        UserInfo user = new UserInfo("pass", "name", "surname", "ma@il.com", new Date());
        db.insertUser(user);

        mockMvc.perform(post("/login")
                //.contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .requestAttr("login", "ma@il.com")
                .requestAttr("password", "pass"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("name1", equalTo("name")))
                .andExpect(content().string(containsString("Name:")))
                .andExpect(content().string(containsString("Birthdate:")));
=======

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/login");
        //noinspection deprecation
        template = new TestRestTemplate();
    }


    @Test
    public void getLogin() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertThat(response.getBody(), containsString("login"));
>>>>>>> origin/master
    }

}