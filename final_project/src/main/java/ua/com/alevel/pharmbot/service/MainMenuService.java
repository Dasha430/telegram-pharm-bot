package ua.com.alevel.pharmbot.service;


import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainMenuService {
    public static final String FIND_MEDS_BUTTON = "Find medicines";
    public static final String GET_MEDS_INSTRUCTION_BUTTON = "See instruction";
    public static final String SEARCH_HISTORY_BUTTON = "Search history";

    public SendMessage getMainMenuMessage(final long chatId, final String textMessage) {
        final ReplyKeyboardMarkup replyKeyboardMarkup = getCustomReplyKeyboardMarkup();
        final SendMessage mainMenuMessage =
                createMessageWithKeyboard(chatId, textMessage, replyKeyboardMarkup);

        return mainMenuMessage;
    }

    private ReplyKeyboardMarkup getCustomReplyKeyboardMarkup() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton(FIND_MEDS_BUTTON));

        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton(GET_MEDS_INSTRUCTION_BUTTON));

        KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardThirdRow.add(new KeyboardButton(SEARCH_HISTORY_BUTTON));

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

    private SendMessage createMessageWithKeyboard(final long chatId,
                                                  String textMessage,
                                                  final ReplyKeyboardMarkup replyKeyboardMarkup) {
        final SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(textMessage);
        if (replyKeyboardMarkup != null) {
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
        }
        return sendMessage;
    }
}
