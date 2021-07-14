package ua.com.alevel.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.com.alevel.pharmbot.bot.state.PharmBotState;

public interface MessageHandler {
    SendMessage handle(Message message);

    PharmBotState getName();
}
