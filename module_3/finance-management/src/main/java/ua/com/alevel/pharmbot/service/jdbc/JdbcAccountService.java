package ua.com.alevel.service.jdbc;

import org.apache.log4j.Logger;
import ua.com.alevel.exception.ObjectNotFoundInDBException;
import ua.com.alevel.model.dto.*;
import ua.com.alevel.model.entity.Account;
import ua.com.alevel.model.entity.CategoryType;
import ua.com.alevel.model.entity.Operation;
import ua.com.alevel.service.AccountService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcAccountService implements AccountService {

    private Connection connection;

    private static final Logger logger = Logger.getLogger(JdbcAccountService.class);

    public JdbcAccountService(String url, String username, String password ) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            logger.error("connection failed");
            throw new RuntimeException("could not connect to database");

        }
    }


    @Override
    public AccountReport getReportById(Long id, String start, String end) {

        logger.info("Starting to fill in the report");
        AccountRecord record = getByOwnerIdWithTimeLimits(id, start, end);
        AccountReport report;

        List<Operation> operations = record.getOperations();
        Long allIncome = countAllIncome(operations);
        Long surplus = countSurplus(operations, allIncome);

        report = new AccountReport(record.getId(), operations, allIncome, record.getBalance(), surplus);
        logger.info("Report finished");

        return report;
    }

    private AccountRecord getByOwnerIdWithTimeLimits(Long id, String start, String end) {
        try {
            connection.setAutoCommit(false);

            AccountRecord record;
            List<Operation> operations = getAllOperationsWithTimeLimits(id, start, end);

            logger.info("Finding account by owner id");
            try (PreparedStatement getAccountById = connection
                    .prepareStatement("select a.id, a.user_balance " +
                            "from accounts a inner join operations o on a.id = o.account_id " +
                            "where a.owner_id = ?")) {

                getAccountById.setLong(1, id);
                ResultSet rs = getAccountById.executeQuery();
                if (!rs.next()) {
                    throw new ObjectNotFoundInDBException(Account.class, id);
                }

                record = new AccountRecord(
                        rs.getLong("id"),
                        operations,
                        rs.getLong("user_balance"));
            }
            logger.info("account found");

            return  record;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables.getMessage());
        } catch (ObjectNotFoundInDBException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
    private List<Operation> getAllOperationsWithTimeLimits (Long id, String start, String end) {
        try {
            connection.setAutoCommit(false);

            List<Operation> operations = new ArrayList<>();
            ResultSet rs;
            logger.info("Extracting all the income operations");
            try (PreparedStatement getAllIncomeOperations = connection
                    .prepareStatement("select id, categoryType, performed_at, turnover, account_id " +
                            "from operations_income_categories c " +
                            "inner join operations o on c.operation_id = o.id" +
                            " where account_id = ? " +
                            "and o.performed_at between " +
                            "cast('" + start + "' as date) and cast('" + end + "' as date)")){
                getAllIncomeOperations.setLong(1, id);

                rs = getAllIncomeOperations.executeQuery();

                while (rs.next()) {
                    Operation operation = new Operation();
                    operation.setId(rs.getLong(1));
                    operation.setCategoryType(CategoryType.INCOME);
                    operation.setPerformedAt(rs.getTimestamp(3).toInstant());
                    operation.setTurnover(rs.getLong(4));
                    operations.add(operation);
                }
                logger.info("Finished extracting all the income operations");

            }

            logger.info("Extracting all the outcome operations");
            try (PreparedStatement getAllOutcomeOperations = connection
                    .prepareStatement("select id, categoryType, performed_at, turnover, account_id " +
                            "from operations_outcome_categories c " +
                            "inner join operations o on c.operation_id = o.id" +
                            " where account_id = ? " +
                            "and o.performed_at " +
                            "between cast('" + start + "' as date) and cast('" + end + "' as date)")) {
                getAllOutcomeOperations.setLong(1, id);

                rs = getAllOutcomeOperations.executeQuery();

                while (rs.next()) {
                    Operation operation = new Operation();
                    operation.setId(rs.getLong(1));
                    operation.setCategoryType(CategoryType.OUTCOME);
                    operation.setPerformedAt(rs.getTimestamp(3).toInstant());
                    operation.setTurnover(rs.getLong(4));
                    operations.add(operation);
                }
                logger.info("Finished extracting all the outcome operations");
            }

            return operations;
        } catch (SQLException throwables) {
            throw new RuntimeException();
        }
    }

    private Long countAllIncome(List<Operation> operations) {
        Long sum = 0L;

        for (Operation o: operations) {
            if (o.getCategoryType() == CategoryType.INCOME) {
                sum += o.getTurnover();
            }
        }
        return sum;
    }

    private Long countSurplus(List<Operation> operations, Long income) {
        return income - countAllOutcome( operations);
    }

    private Long countAllOutcome(List<Operation> operations) {
        Long sum = 0L;

        for (Operation o: operations) {
            if (o.getCategoryType() == CategoryType.OUTCOME) {
                sum += o.getTurnover();
            }
        }
        return sum;
    }

    @Override
    public AccountRecord getByOwnerId(Long id) {
        try {
            connection.setAutoCommit(false);

            AccountRecord record;
            List<Operation> operations = getAllOperations(id);

            logger.info("Finding account by owner id");
            try (PreparedStatement getAccountById = connection
                    .prepareStatement("select id, user_balance from accounts where owner_id = ?")) {

                getAccountById.setLong(1, id);
                ResultSet rs = getAccountById.executeQuery();
                if (!rs.next()) {
                    throw new ObjectNotFoundInDBException(Account.class, id);
                }

                record = new AccountRecord(
                        rs.getLong("id"),
                        operations,
                        rs.getLong("user_balance"));
            }
            logger.info("account found");

            return  record;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables.getMessage());
        } catch (ObjectNotFoundInDBException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    private List<Operation> getAllOperations (Long id) {
        try {
            connection.setAutoCommit(false);

            List<Operation> operations = new ArrayList<>();
            ResultSet rs;
            logger.info("Extracting all the income operations");
            try (PreparedStatement getAllIncomeOperations = connection
                    .prepareStatement("select id, categoryType, performed_at, turnover, account_id " +
                            "from operations_income_categories c inner join operations o on c.operation_id = o.id" +
                            " where account_id = ?")){
                getAllIncomeOperations.setLong(1, id);

                rs = getAllIncomeOperations.executeQuery();

                while (rs.next()) {
                    Operation operation = new Operation();
                    operation.setId(rs.getLong(1));
                    operation.setCategoryType(CategoryType.INCOME);
                    operation.setPerformedAt(rs.getTimestamp(3).toInstant());
                    operation.setTurnover(rs.getLong(4));
                    operations.add(operation);
                }
                logger.info("Finished extracting all the income operations");

            }

            logger.info("Extracting all the outcome operations");
            try (PreparedStatement getAllOutcomeOperations = connection
                    .prepareStatement("select id, categoryType, performed_at, turnover, account_id " +
                            "from operations_outcome_categories c inner join operations o on c.operation_id = o.id" +
                            " where account_id = ?")) {
                getAllOutcomeOperations.setLong(1, id);

                rs = getAllOutcomeOperations.executeQuery();

                while (rs.next()) {
                    Operation operation = new Operation();
                    operation.setId(rs.getLong(1));
                    operation.setCategoryType(CategoryType.OUTCOME);
                    operation.setPerformedAt(rs.getTimestamp(3).toInstant());
                    operation.setTurnover(rs.getLong(4));
                    operations.add(operation);
                }
                logger.info("Finished extracting all the outcome operations");
            }

            return operations;
        } catch (SQLException throwables) {
           throw new RuntimeException();
        }
    }
}
