package ua.com.alevel.model.dto;

import lombok.Value;
import ua.com.alevel.model.entity.IncomeCategory;

@Value
public class IncomeOperationRecord {

    IncomeCategory incomeCategory;

    Long turnover;
}
