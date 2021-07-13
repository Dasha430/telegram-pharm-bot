package ua.com.alevel.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;
import ua.com.alevel.model.entity.Account;
import ua.com.alevel.model.entity.CategoryType;
import ua.com.alevel.model.entity.IncomeCategory;

import java.time.Instant;
import java.util.Set;

@Value
public class SaveIncomeOperation {

    AccountRecord account;

    @NotNull
    long turnover;

    @NotNull
    String incomeCategory;

    CategoryType categoryType = CategoryType.INCOME;
}
