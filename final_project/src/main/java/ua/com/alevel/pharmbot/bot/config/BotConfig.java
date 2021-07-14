package ua.com.alevel.pharmbot.bot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import ua.com.alevel.pharmbot.bot.PharmBot;


@Configuration
@Getter
@Setter
public class BotConfig {

    @Value("${telegram.bot.token}")
    private String token;
    @Value("${telegram.bot.username}")
    private String username;
    @Value("${telegram.bot.webhook.path}")
    private String webHookPath;

    @Bean
    public PharmBot mySuperBot() {
        DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);

        PharmBot bot = new PharmBot(options);
        bot.setToken(this.token);
        bot.setUsername(this.username);
        bot.setWebHookPath(this.webHookPath);

        return bot;
    }



}
