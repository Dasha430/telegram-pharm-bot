package ua.com.alevel.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ua.com.alevel.pharmbot.bot.state.PharmBotState;
import ua.com.alevel.pharmbot.cache.UserDataCache;
import ua.com.alevel.pharmbot.model.FormName;
import ua.com.alevel.pharmbot.service.MedicineInstructionService;
import ua.com.alevel.pharmbot.service.MessageService;
import ua.com.alevel.pharmbot.service.ReplyMessageService;
import ua.com.alevel.templates.MessageTemplates;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class FindInstructionCommandHandler implements MessageHandler {

    private UserDataCache cache;
    private MedicineInstructionService instructionService;
    private ReplyMessageService replyService;


    public FindInstructionCommandHandler(UserDataCache cache,
                                         MedicineInstructionService instructionService,
                                         ReplyMessageService replyService) {
        this.cache = cache;
        this.instructionService = instructionService;
        this.replyService = replyService;
    }

    @Override
    public SendMessage handle(Message message) {
        Long chatId = message.getChatId();
        if(cache.getUsersCurrentBotState(chatId).equals(PharmBotState.INSTRUCTION_SEARCH)) {
            cache.setUsersCurrentBotState(chatId, PharmBotState.ASK_NEEDED_MEDICINE_NAME);
        }
        return process(message);
    }

    private SendMessage process(Message message) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        String answer = message.getText();
        Long chatId = message.getChatId();
        PharmBotState state = cache.getUsersCurrentBotState(chatId);
        SendMessage reply = replyService.getWarningReplyMessage(chatId, MessageTemplates.NOT_FOUND_MESSAGE);
        String name;


        if (state.equals(PharmBotState.ASK_NEEDED_MEDICINE_NAME)) {
            reply = replyService.getReplyMessage(chatId, MessageTemplates.ASK_MEDS_NAME_MESSAGE);
            cache.setUsersCurrentBotState(chatId, PharmBotState.ASK_MEDICINE_FORM_NAME);
        }
        if (state.equals(PharmBotState.ASK_MEDICINE_FORM_NAME)) {
            name = answer;
            Set<FormName> forms = instructionService.findAllExistingForms(name);
            if (forms == null || forms.isEmpty()) {
                reply = replyService.getWarningReplyMessage(chatId, MessageTemplates.NOT_FOUND_MESSAGE);
            } else  {
                List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

                for (FormName f : forms) {
                    List<InlineKeyboardButton> row = new ArrayList<>();
                    row.add(new InlineKeyboardButton().setText(f.name()).setCallbackData(f + " " + name));
                    keyboard.add(row);
                }
                markup.setKeyboard(keyboard);
                reply = replyService.getReplyMessage(chatId, MessageTemplates.ASK_MEDS_FORM_MESSAGE, markup);
            }
        }

        return reply;
    }

    @Override
    public PharmBotState getName() {
        return PharmBotState.INSTRUCTION_SEARCH;
    }
}
