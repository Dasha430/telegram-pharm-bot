package ua.com.alevel.model.dto;

import lombok.Value;
import ua.com.alevel.model.entity.Operation;
import ua.com.alevel.model.entity.User;

import java.util.List;

@Value
public class AccountRecord {
    Long id;

    List<Operation> operations;

    Long balance;

}
