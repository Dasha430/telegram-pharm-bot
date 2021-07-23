package ua.com.alevel.pharmbot.bot.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import ua.com.alevel.pharmbot.bot.BotFacade;
import ua.com.alevel.pharmbot.bot.PharmBot;

@Configuration
public class AppConfig {

    private final BotConfig botConfig;

    public AppConfig(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource
                = new ResourceBundleMessageSource();

        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    @Bean
    public PharmBot myBot(BotFacade facade) {
        DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);

        PharmBot bot = new PharmBot(options, facade);
        bot.setToken(botConfig.getToken());
        bot.setUsername(botConfig.getUsername());
        bot.setWebHookPath(botConfig.getWebHookPath());

        return bot;
    }
}
