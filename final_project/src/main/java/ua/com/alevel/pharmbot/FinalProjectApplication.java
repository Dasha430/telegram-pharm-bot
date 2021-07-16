package ua.com.alevel.pharmbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class FinalProjectApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();

        SpringApplication.run(FinalProjectApplication.class, args);
    }

}
