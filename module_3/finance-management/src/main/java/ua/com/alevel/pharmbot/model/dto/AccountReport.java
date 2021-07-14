package ua.com.alevel.pharmbot.model.dto;

import lombok.Value;
import ua.com.alevel.pharmbot.model.entity.Operation;

import java.util.List;

@Value
public class AccountReport {

    Long id;

    List<Operation> operations;

    Long allIncome;

    Long balance;

    Long surplus;
}
