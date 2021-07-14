package ua.com.alevel.pharmbot.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.com.alevel.pharmbot.bot.state.PharmBotState;
import ua.com.alevel.pharmbot.bot.state.PharmBotStateContext;
import ua.com.alevel.pharmbot.cache.UserDataCache;
import ua.com.alevel.pharmbot.service.MainMenuService;

@Service
@Slf4j
public class BotFacade {

    private PharmBotStateContext context;
    private UserDataCache cache;

    public SendMessage handleUpdate(Update update) {
        SendMessage reply = null;

        if (update.hasCallbackQuery()) {
            log.info("New callbackQuery from User: {} with data: {}", update.getCallbackQuery().getFrom().getUserName(),
                    update.getCallbackQuery().getData());
        }

        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            log.info("New message from User:{}, chatId: {},  with text: {}",
                    message.getFrom().getUserName(), message.getChatId(), message.getText());
            reply = handleMessage(message);
        }
        return reply;
    }

    private SendMessage handleMessage (Message message){

        String messageText = message.getText();
        PharmBotState state;
        Long chatId = message.getChatId();

        switch (messageText) {
            case MainMenuService.FIND_MEDS_BUTTON:
                state = PharmBotState.MEDS_SEARCH;
                break;
            case MainMenuService.GET_MEDS_INSTRUCTION_BUTTON:
                state = PharmBotState.INSTRUCTION_SEARCH;
                break;
            case MainMenuService.SEARCH_HISTORY_BUTTON:
                state = PharmBotState.SEARCH_HISTORY_REQUEST;
                break;
            default:
                state = cache.getUsersCurrentBotState(chatId);
                break;

        }
        cache.setUsersCurrentBotState(chatId, state);

        return context.process(message, state);
    }


}
