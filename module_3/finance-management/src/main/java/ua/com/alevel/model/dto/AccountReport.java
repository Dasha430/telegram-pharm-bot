package ua.com.alevel.model.dto;

import lombok.Value;
import ua.com.alevel.model.entity.Operation;

import java.util.List;

@Value
public class AccountReport {

    Long id;

    List<Operation> operations;

    Long allIncome;

    Long balance;

    Long surplus;
}
