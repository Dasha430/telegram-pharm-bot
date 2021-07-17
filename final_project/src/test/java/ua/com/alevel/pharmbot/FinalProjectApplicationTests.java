package ua.com.alevel.pharmbot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ua.com.alevel.pharmbot.controller.WebHookController;
import ua.com.alevel.pharmbot.model.User;
import ua.com.alevel.pharmbot.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("h2db")
class FinalProjectApplicationTests {

    @Autowired
    private WebHookController controller;

    @Autowired
    private TestRestTemplate rest;

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void testCreateUser() {
        long id = 100873L;

        User u = userService.createUser(id);
        assertThat(u).isNotNull();
        assertThat(u.getChatId()).isEqualTo(id);

    }

}
