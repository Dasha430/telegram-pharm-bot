package ua.com.alevel.pharmbot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
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

    @Test
    void testGetUserById() {
        long id = 100873L;

        User u = userService.createUser(id);
        User user = userService.getById(u.getChatId());

        assertThat(user.getChatId()).isEqualTo(u.getChatId());

    }

    @Test
    void testUpdateUserAddress(){
        long id = 100873L;
        String newAddress = "Украина, Харьков, улица Сумская, 47";

        User u = userService.createUser(id);
        userService.updateUserAddress(newAddress, id);
    }

}
