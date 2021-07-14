package ua.com.alevel.pharmbot.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;
import ua.com.alevel.pharmbot.model.entity.CategoryType;

@Value
public class SaveOutcomeOperation {

    AccountRecord account;

    @NotNull
    long turnover;

    @NotNull
    String outcomeCategory;

    CategoryType categoryType = CategoryType.OUTCOME;
}
