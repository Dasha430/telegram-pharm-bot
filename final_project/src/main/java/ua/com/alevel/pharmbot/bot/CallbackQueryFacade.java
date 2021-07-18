package ua.com.alevel.pharmbot.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ua.com.alevel.pharmbot.bot.state.PharmBotState;
import ua.com.alevel.pharmbot.cache.UserDataCache;
import ua.com.alevel.pharmbot.model.FormName;
import ua.com.alevel.pharmbot.service.MedicineInstructionService;
import ua.com.alevel.pharmbot.service.MessageService;
import ua.com.alevel.pharmbot.service.ReplyMessageService;
import ua.com.alevel.templates.MessageTemplates;

import java.util.Locale;

@Component
public class CallbackQueryFacade {
    private final ReplyMessageService replyService;
    private final MedicineInstructionService instructionService;
    private final UserDataCache cache;
    private final MessageService messageService;

    public CallbackQueryFacade(ReplyMessageService replyService,
                               MedicineInstructionService instructionService,
                               UserDataCache cache,
                               MessageService messageService) {
        this.replyService = replyService;
        this.instructionService = instructionService;
        this.cache = cache;
        this.messageService = messageService;
    }

    public SendMessage handleQuery(CallbackQuery query) {
        Long chatId = query.getMessage().getChatId();

        if (cache.getUsersCurrentBotState(chatId).equals(PharmBotState.MEDICINE_FORM_NAME_RECEIVED)) {

            String[] queryData = query.getData().split(" ");
            StringBuilder reply = new StringBuilder("Instruction to " + queryData[1] + "\n\n");

            reply.append(instructionService.findInstruction(queryData[1], queryData[0]));
            messageService.sendMessage(chatId, reply.toString());

            cache.setUsersCurrentBotState(chatId, PharmBotState.SHOW_MAIN_MENU);
            return replyService.getReplyMessage(chatId, MessageTemplates.SUCCESS_MESSAGE);
        }
        return replyService.getWarningReplyMessage(chatId, MessageTemplates.NOT_FOUND_MESSAGE);
    }

    private FormName findCorrespondingFormName(String formName) {
        switch (formName.toUpperCase()) {
            case "TABLET":
                return FormName.TABLET;
            case "LIQUID":
                return FormName.LIQUID;
            case "CAPSULES":
                return FormName.CAPSULES;
            case "TOPICAL":
                return FormName.TOPICAL;
            case "INJECTION":
                return FormName.INJECTION;
            default:
                return FormName.OTHER;
        }
    }
}
