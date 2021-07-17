package ua.com.alevel.pharmbot.bot.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ua.com.alevel.pharmbot.handlers.MessageHandler;

import java.util.*;

@Component
public class PharmBotStateContext {

    private final Map<PharmBotState, MessageHandler> handlers = new EnumMap<>(PharmBotState.class);

    @Autowired
    public PharmBotStateContext(List<MessageHandler> handlers) {
        handlers.forEach(handler -> this.handlers.put(handler.getName(), handler));
    }


    public SendMessage process(Message message, PharmBotState current) {
        MessageHandler handler = findCorrespondingHandler(current);
        return handler.handle(message);
    }

    private MessageHandler findCorrespondingHandler(PharmBotState current) {

        if (current == null) {
            current = PharmBotState.MEDS_SEARCH;
        }
        switch (current) {
            case MEDS_SEARCH:
            case ASK_MEDICINE_NAME:
            case MEDS_SEARCH_STARTED:
            case MEDICINE_NAME_RECEIVED:
            case MEDS_SEARCH_FINISHED:
            case ASK_ADDRESS:
            case ADDRESS_RECEIVED:
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
