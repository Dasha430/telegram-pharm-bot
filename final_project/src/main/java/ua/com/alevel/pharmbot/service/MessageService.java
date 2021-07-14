package ua.com.alevel.pharmbot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ua.com.alevel.pharmbot.model.records.MedInPharmacyRecord;

import java.util.List;

public interface MessageService {
    String buildMessage(Long id, MedInPharmacyRecord record);

    void sendMessage(Long id, String text);
}
