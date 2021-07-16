package ua.com.alevel.pharmbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.com.alevel.pharmbot.bot.PharmBot;

@RestController
public class WebHookController {

    private final PharmBot bot;

    public WebHookController(PharmBot bot) {
        this.bot = bot;
    }

    @RequestMapping (value = "/", method = RequestMethod.POST)
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update){
        return bot.onWebhookUpdateReceived(update);
    }
}
