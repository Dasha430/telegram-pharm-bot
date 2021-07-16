package ua.com.alevel.pharmbot.bot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@Getter
@Setter
@PropertySource("classpath:application.yml")
public class BotConfig {

    @Value("${telegram.bot.token}")
    private String token;
    @Value("${telegram.bot.username}")
    private String username;
    @Value("${telegram.bot.webhook.path}")
    private String webHookPath;


}
