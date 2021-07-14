package ua.com.alevel.pharmbot.service;

import ua.com.alevel.exception.ObjectNotFoundInDBException;
import ua.com.alevel.pharmbot.model.dto.*;


public interface OperationService {

    OperationRecord getById(Long id) throws ObjectNotFoundInDBException;

    OperationRecord save(SaveIncomeOperation operation);

    OperationRecord save(SaveOutcomeOperation operation);

}
