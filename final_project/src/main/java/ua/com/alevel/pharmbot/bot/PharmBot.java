package ua.com.alevel.pharmbot.bot;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Slf4j
@Getter
@Setter
public class PharmBot extends TelegramWebhookBot {

    private String token;
    private String username;
    private String webHookPath;

    private BotFacade facade;


    public PharmBot(DefaultBotOptions options, BotFacade facade) {
        super(options);
        this.facade = facade;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {

        SendMessage reply = facade.handleUpdate(update);
        return reply;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }

    public void sendMessage(long chatId, String textMessage) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(textMessage);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }





}
