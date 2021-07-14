package ua.com.alevel.pharmbot.model.dto;

import lombok.Value;
import ua.com.alevel.pharmbot.model.entity.IncomeCategory;

@Value
public class IncomeOperationRecord {

    IncomeCategory incomeCategory;

    Long turnover;
}
