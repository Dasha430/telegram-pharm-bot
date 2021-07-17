package ua.com.alevel.pharmbot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.pharmbot.bot.PharmBot;
import ua.com.alevel.pharmbot.model.records.MedInPharmacyRecord;
import ua.com.alevel.pharmbot.repository.MedicineRepository;
import ua.com.alevel.pharmbot.service.MessageService;



@Service
@Slf4j
public class MessageServiceImpl implements MessageService {
    private PharmBot bot;
    private MedicineRepository repo;

    public MessageServiceImpl(@Lazy PharmBot bot, MedicineRepository repo) {
        this.bot = bot;
        this.repo = repo;
    }

    @Override
    public String buildMessage(Long id, MedInPharmacyRecord record) {
        log.info("Building message");
        StringBuilder output;
        if (record == null ) {
            log.info("No information");
            return null;
        }
        output = new StringBuilder("");
        output.append(record.getMedicine().getName() + "\n");
        output.append("Form: " + repo.findFormById(record.getMedicine().getId()) + "\n");
        output.append(record.getPharmacy().getName() + "\n");
        output.append("Address: " + record.getPharmacy().getAddress() + "\n");
        output.append("Price: " + record.getPrice());
        log.info("Message was built successfully");
        return output.toString();
    }

    @Override
    public void sendMessage(Long id, String text) {
        log.info("Sending message");
        bot.sendMessage(id, text);
        log.info("Message was sent");
    }
}
