package ua.com.alevel.pharmbot.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import ua.com.alevel.pharmbot.bot.state.PharmBotState;
import ua.com.alevel.pharmbot.cache.UserDataCache;
import ua.com.alevel.pharmbot.model.records.MedInPharmacyRecord;
import ua.com.alevel.pharmbot.service.*;
import ua.com.alevel.templates.MessageTemplates;

import java.util.List;

@Component
public class FindMedsCommandHandler implements MessageHandler{

    private UserDataCache cache;
    private FindMedicineService medicineService;
    private UserService userService;
    private ReplyMessageService replyService;
    private SearchHistoryService searchService;
    private MessageService messageService;



    public FindMedsCommandHandler(UserDataCache cache,
                                  FindMedicineService medicineService,
                                  UserService userService,
                                  ReplyMessageService replyService,
                                  SearchHistoryService searchService,
                                  MessageService messageService
                                  ) {
        this.cache = cache;
        this.medicineService = medicineService;
        this.userService = userService;
        this.replyService = replyService;
        this.searchService = searchService;
        this.messageService = messageService;
    }

    @Override
    public SendMessage handle(Message message) {
        Long chatId = message.getChatId();

        if (cache.getUsersCurrentBotState(chatId).equals(PharmBotState.MEDS_SEARCH)) {
            cache.setUsersCurrentBotState(chatId, PharmBotState.ASK_MEDICINE_NAME);
        }
        return process(message);
    }

    @Override
    public PharmBotState getName() {
        return PharmBotState.MEDS_SEARCH;
    }

    private SendMessage process(Message message) {
        String answer = message.getText();
        Long chatId = message.getChatId();
        SendMessage reply = replyService.getWarningReplyMessage(chatId, MessageTemplates.NOT_FOUND_MESSAGE);
        String medicine;
        String address;

        if(cache.getUsersCurrentBotState(chatId).equals(PharmBotState.ASK_MEDICINE_NAME)) {
            reply = replyService.getReplyMessage(chatId, MessageTemplates.ASK_MEDS_NAME_MESSAGE);
            userService.createUser(chatId);
            if (userService.userHasAddress(chatId)) {
                cache.setUsersCurrentBotState(chatId, PharmBotState.ASK_ABOUT_ADDRESS_CHANGE);
            } else {
                cache.setUsersCurrentBotState(chatId, PharmBotState.ASK_ADDRESS);
            }
            return reply;
        }
        if (cache.getUsersCurrentBotState(chatId).equals(PharmBotState.ASK_ABOUT_ADDRESS_CHANGE)) {
            medicine = answer;

            if (medicine != null) {
                if (medicineService.getByName(medicine) == null) {
                    reply = replyService.getWarningReplyMessage(chatId, MessageTemplates.NOT_FOUND_MESSAGE);
                    cache.setUsersCurrentBotState(chatId, PharmBotState.SHOW_MAIN_MENU);
                    return reply;
                }
                searchService.save(chatId, medicine);
                String currentAddress = userService.getUserAddress(chatId);
                reply = replyService.getReplyMessage(chatId, MessageTemplates.askAboutAddressChange(currentAddress));
                cache.setUsersCurrentBotState(chatId, PharmBotState.ADDRESS_CHANGE_ANSWER_RECEIVED);
            }
            return reply;
        }
        if (cache.getUsersCurrentBotState(chatId).equals(PharmBotState.ADDRESS_CHANGE_ANSWER_RECEIVED)) {
            switch (answer.toUpperCase()) {
                case "Y":
                    cache.setUsersCurrentBotState(chatId,PharmBotState.ASK_ADDRESS);
                case"N":
                    cache.setUsersCurrentBotState(chatId, PharmBotState.MEDS_SEARCH_STARTED);
                default:
                    reply = replyService.getWarningReplyMessage(chatId, MessageTemplates.WRONG_ANSWER_CHOICE);
            }
        }
        if (cache.getUsersCurrentBotState(chatId).equals(PharmBotState.ASK_ADDRESS)) {
            medicine = answer;
            searchService.save(chatId, medicine);
            reply = replyService.getReplyMessage(chatId, MessageTemplates.ASK_ADDRESS_MESSAGE);
            cache.setUsersCurrentBotState(chatId, PharmBotState.ADDRESS_RECEIVED);

            return reply;
        }
        if (cache.getUsersCurrentBotState(chatId).equals(PharmBotState.ADDRESS_RECEIVED)) {
            address = answer;
            userService.updateUserAddress(address, chatId);
            cache.setUsersCurrentBotState(chatId, PharmBotState.MEDS_SEARCH_STARTED);
        }

        if (cache.getUsersCurrentBotState(chatId).equals(PharmBotState.MEDS_SEARCH_STARTED)) {
            address = userService.getUserAddressGeoCode(chatId);
            medicine = searchService.getLatestMedicine(chatId);
            List<String> addresses = medicineService.getNearestAddressesWhereMedicinePresent(medicine, address);

            if (addresses == null) {
                reply = replyService.getWarningReplyMessage(chatId, MessageTemplates.NOT_FOUND_MESSAGE);
                cache.setUsersCurrentBotState(chatId, PharmBotState.SHOW_MAIN_MENU);
                return reply;
            }
            for (String a: addresses) {
                List<MedInPharmacyRecord> records = medicineService.getByNameAndAddress(medicine, a);
                for (MedInPharmacyRecord r: records) {
                    String output = messageService.buildMessage(chatId, r);
                    if (output != null) {
                        messageService.sendMessage(chatId, output);
                    }
                }

            }
            cache.setUsersCurrentBotState(chatId, PharmBotState.SHOW_MAIN_MENU);
            reply = replyService.getReplyMessage(chatId, MessageTemplates.SUCCESS_MESSAGE);
        }


        return reply;
    }


}
