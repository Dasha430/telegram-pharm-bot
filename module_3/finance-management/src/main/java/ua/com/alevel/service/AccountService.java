package ua.com.alevel.service;

import ua.com.alevel.model.dto.AccountRecord;
import ua.com.alevel.model.dto.AccountReport;


public interface AccountService {

    AccountReport getReportById(Long id, String start, String end);

    AccountRecord getByOwnerId(Long id);

}
