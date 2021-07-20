package ua.com.alevel.pharmbot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ua.com.alevel.pharmbot.model.User;
import ua.com.alevel.pharmbot.service.SearchHistoryService;
import ua.com.alevel.pharmbot.service.UserService;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("h2db")
public class SearchHistoryTest {

    @Autowired
    SearchHistoryService searchService;

    @Autowired
    private UserService userService;

    @Test
    void getHistoryTest() {
        long id = 100009L;

        User u = userService.createUser(id);
        List<String> results = searchService.getHistory(id);
        assertThat(results).isNotNull();
        assertThat(results).isEmpty();
    }
}
