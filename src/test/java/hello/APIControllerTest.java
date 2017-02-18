package hello;

import org.apache.tomcat.jni.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import repository.DBService;
import repository.DBServiceClass;

import java.net.URL;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by guille on 17/02/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class APIControllerTest {
    @Value("${local.server.port}")
    private int port;

    private URL base;
    private RestTemplate template;

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
	public void testUser() throws Exception {
        DBService db = new DBServiceClass();
        UserInfo expected = new UserInfo("pass", "name", "surname", "ma@il.com", 20);
        db.insertUser(expected);
        String request = "{ login: \"ma@il.com\", password: \"pass\"}";


        String userURI = base.toString() + "/user";
        ResponseEntity<UserInfo> response = template.postForEntity(userURI, request, UserInfo.class);
        assertThat(response.getBody(), equalTo(expected));
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        //assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }
}
