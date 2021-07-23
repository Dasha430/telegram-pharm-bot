package ua.com.alevel.pharmbot.service;

import ua.com.alevel.pharmbot.model.records.MedInPharmacyRecord;

public interface MessageService {
    String buildMessage(Long id, MedInPharmacyRecord record);

    void sendMessage(Long id, String text);
}
