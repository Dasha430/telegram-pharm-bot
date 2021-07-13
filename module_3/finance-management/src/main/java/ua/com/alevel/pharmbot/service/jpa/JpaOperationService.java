package ua.com.alevel.service.jpa;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.apache.log4j.Logger;
import ua.com.alevel.exception.ObjectNotFoundInDBException;
import ua.com.alevel.model.dto.*;
import ua.com.alevel.model.entity.Account;
import ua.com.alevel.model.entity.IncomeCategory;
import ua.com.alevel.model.entity.Operation;
import ua.com.alevel.model.entity.OutcomeCategory;
import ua.com.alevel.service.OperationService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.Set;
import java.util.function.Supplier;

public class JpaOperationService implements OperationService{

    private static final Logger logger = Logger.getLogger(JpaOperationService.class) ;

    private final Supplier<EntityManager> persistence;

    private final Validator validator;

    public JpaOperationService(Supplier<EntityManager> persistence, ValidatorFactory validator) {
        this.persistence = persistence;
        this.validator = validator.getValidator();
    }

    @Override
    public OperationRecord getById(Long id) throws ObjectNotFoundInDBException {
        EntityManager jpa = persistence.get();

        Operation operation = jpa.find(Operation.class, id);

        if (operation != null) {
            OperationRecord op = toRecord(operation);
            return op;
        } else throw new ObjectNotFoundInDBException(Operation.class, id);

    }

    @Override
    public OperationRecord save(SaveOutcomeOperation operation) {

        validate(operation);
        EntityManager jpa = persistence.get();
        EntityTransaction transaction = jpa.getTransaction();
        transaction.begin();

        try {
            logger.info("start creating operation");

            if (operation.getTurnover() > operation.getAccount().getBalance()) {
                logger.error("not enough money");
                throw new IllegalArgumentException("not enough money");
            }

            Operation o = new Operation();
            mergeEntityAndRecord(o, operation);

            updateAccount(operation, jpa);
            jpa.persist(o);
            transaction.commit();
            logger.info("operation created successfully");
            return toRecord(o);
        } catch (RuntimeException e) {
            logger.error("failed to create operation");
            transaction.rollback();
            throw new RuntimeException("failed to create operation");
        }

    }



    @Override
    public OperationRecord save(SaveIncomeOperation operation) {
        validate(operation);
        EntityManager jpa = persistence.get();
        EntityTransaction transaction = jpa.getTransaction();
        transaction.begin();

        try {
            logger.info("start creating operation");

            Operation o = new Operation();
            mergeEntityAndRecord(o, operation);

            updateAccount(operation, jpa);

            jpa.persist(o);
            transaction.commit();
            logger.info("operation created successfully");
            return toRecord(o);
        } catch (RuntimeException e) {
            logger.error("failed to create operation");
            transaction.rollback();
            throw new RuntimeException("failed to create operation");
        }
    }



    private  OperationRecord toRecord (Operation operation) {
        return new OperationRecord(
                operation.getId(),
                operation.getAccount(),
                operation.getCategoryType(),
                operation.getIncomeCategories(),
                operation.getOutcomeCategories(),
                operation.getPerformedAt(),
                operation.getTurnover()
        );
    }

    private void validate(SaveIncomeOperation operation) {
        Set<ConstraintViolation<SaveIncomeOperation>> constraintViolations = validator.validate(operation);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

    private void validate(SaveOutcomeOperation operation) {
        Set<ConstraintViolation<SaveOutcomeOperation>> constraintViolations = validator.validate(operation);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

    private void mergeEntityAndRecord(Operation entity, SaveOutcomeOperation record) {

        EntityManager jpa = persistence.get();
        entity.setAccount(jpa.find(Account.class, record.getAccount().getId()));
        entity.setCategoryType(record.getCategoryType());
        entity.addCategory(getOutcomeCategory(record.getOutcomeCategory()));
        entity.setTurnover(record.getTurnover());
    }
    private OutcomeCategory getOutcomeCategory(String name) {
        EntityManager jpa = persistence.get();
        TypedQuery<OutcomeCategory> q = jpa.createQuery("select oc from OutcomeCategory oc where oc.name = :name", OutcomeCategory.class);
        q.setParameter("name", name);

        return q.getSingleResult();
    }

    private void mergeEntityAndRecord(Operation entity, SaveIncomeOperation record) {
        EntityManager jpa = persistence.get();
        entity.setAccount(jpa.find(Account.class, record.getAccount().getId()));
        entity.setCategoryType(record.getCategoryType());
        entity.addCategory(getIncomeCategory(record.getIncomeCategory()));
        entity.setTurnover(record.getTurnover());
    }
    private IncomeCategory getIncomeCategory(String name) {
        EntityManager jpa = persistence.get();
        TypedQuery<IncomeCategory> q = jpa.createQuery("select ic from IncomeCategory ic where ic.name = :name", IncomeCategory.class);
        q.setParameter("name", name);

        return q.getSingleResult();
    }

    private void updateAccount(SaveIncomeOperation operation, EntityManager jpa) {

        logger.info("start updating account (income category)");
        Account persisted = jpa.find(Account.class, operation.getAccount().getId());
        persisted.setBalance(persisted.getBalance() + operation.getTurnover());

        jpa.merge(persisted);

        logger.info("finish updating account (income category)");
    }

    private void updateAccount(SaveOutcomeOperation operation, EntityManager jpa) {

        logger.info("start updating account (income category)");

        Account persisted = jpa.find(Account.class, operation.getAccount().getId());
        persisted.setBalance(persisted.getBalance() - operation.getTurnover());

        jpa.merge(persisted);

        logger.info("finish updating account (income category)");
    }


}
