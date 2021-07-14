package ua.com.alevel.pharmbot.model.dto;

import lombok.Value;
import ua.com.alevel.pharmbot.model.entity.Account;
import ua.com.alevel.pharmbot.model.entity.CategoryType;
import ua.com.alevel.pharmbot.model.entity.IncomeCategory;
import ua.com.alevel.pharmbot.model.entity.OutcomeCategory;

import java.time.Instant;
import java.util.Set;

@Value
public class OperationRecord {

    Long id;

    Account account;

    CategoryType categoryType;

    Set<IncomeCategory> incomeCategories;

    Set<OutcomeCategory> outcomeCategories;

    Instant performedAt;

    Long turnover;


}
