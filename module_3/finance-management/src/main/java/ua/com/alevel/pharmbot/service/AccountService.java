package ua.com.alevel.pharmbot.service;

import ua.com.alevel.pharmbot.model.dto.AccountRecord;
import ua.com.alevel.pharmbot.model.dto.AccountReport;


public interface AccountService {

    AccountReport getReportById(Long id, String start, String end);

    AccountRecord getByOwnerId(Long id);

}
