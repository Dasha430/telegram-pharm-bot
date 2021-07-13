package ua.com.alevel.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;
import ua.com.alevel.model.entity.Account;
import ua.com.alevel.model.entity.CategoryType;
import ua.com.alevel.model.entity.OutcomeCategory;


import java.time.Instant;
import java.util.Set;

@Value
public class SaveOutcomeOperation {

    AccountRecord account;

    @NotNull
    long turnover;

    @NotNull
    String outcomeCategory;

    CategoryType categoryType = CategoryType.OUTCOME;
}
