package ua.com.alevel.pharmbot.bot.state;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ua.com.alevel.handlers.MessageHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PharmBotStateContext {

    private Map<PharmBotState, MessageHandler> handlers = new HashMap<>();

    public PharmBotStateContext(List<MessageHandler> messageHandlers) {
        messageHandlers.forEach(handler -> handlers.put(handler.getName(), handler));
    }

    public SendMessage process(Message message, PharmBotState current) {
        MessageHandler handler = findCorrespondingHandler(current);
        return handler.handle(message);
    }

    private MessageHandler findCorrespondingHandler(PharmBotState current) {
        switch (current) {
            case MEDS_SEARCH:
            case ASK_MEDICINE_NAME:
            case MEDS_SEARCH_STARTED:
            case MEDICINE_NAME_RECEIVED:
            case MEDS_SEARCH_FINISHED:
            case ASK_ADDRESS:
            case ASK_ABOUT_ADDRESS_CHANGE:
            case ADDRESS_CHANGE_ANSWER_RECEIVED:
                return handlers.get(PharmBotState.MEDS_SEARCH);
            case INSTRUCTION_SEARCH:
            case ASK_NEEDED_MEDICINE_NAME:
            case NEEDED_MEDICINE_NAME_RECEIVED:
            case ASK_MEDICINE_FORM_NAME:
            case MEDICINE_FORM_NAME_RECEIVED:
                return handlers.get(PharmBotState.INSTRUCTION_SEARCH);
        }

        return handlers.get(current);

    }
}
