package ua.com.alevel.pharmbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class FinalProjectApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(FinalProjectApplication.class, args);
    }

}
