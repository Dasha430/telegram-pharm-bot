package ua.com.alevel.pharmbot.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;
import ua.com.alevel.pharmbot.model.entity.CategoryType;

@Value
public class SaveIncomeOperation {

    AccountRecord account;

    @NotNull
    long turnover;

    @NotNull
    String incomeCategory;

    CategoryType categoryType = CategoryType.INCOME;
}
