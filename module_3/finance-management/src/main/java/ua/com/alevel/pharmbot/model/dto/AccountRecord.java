package ua.com.alevel.pharmbot.model.dto;

import lombok.Value;
import ua.com.alevel.pharmbot.model.entity.Operation;

import java.util.List;

@Value
public class AccountRecord {
    Long id;

    List<Operation> operations;

    Long balance;

}
