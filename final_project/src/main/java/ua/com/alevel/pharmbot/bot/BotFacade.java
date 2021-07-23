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

    private final PharmBotStateContext context;
    private final UserDataCache cache;
    private final CallbackQueryFacade callbackQueryFacade;

    public BotFacade(PharmBotStateContext context,
                     UserDataCache cache,
                     CallbackQueryFacade callbackQueryFacade) {
        this.context = context;
        this.cache = cache;
        this.callbackQueryFacade = callbackQueryFacade;
    }

    public SendMessage handleUpdate(Update update) {
        SendMessage reply = null;

        if (update.hasCallbackQuery()) {
            log.info("New callbackQuery from User: {} with data: {}", update.getCallbackQuery().getFrom().getUserName(),
                    update.getCallbackQuery().getData());
            return callbackQueryFacade.handleQuery(update.getCallbackQuery());
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
            case "/start":
                state = PharmBotState.SHOW_MAIN_MENU;
                break;
            default:
                state = cache.getUsersCurrentBotState(chatId);
                break;

        }
        if (state == null) {
            cache.setUsersCurrentBotState(chatId,PharmBotState.INSTRUCTION_SEARCH);
        } else cache.setUsersCurrentBotState(chatId,state);

        return context.process(message, state);
    }


}
