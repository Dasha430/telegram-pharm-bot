package ua.com.alevel.controller;

import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import ua.com.alevel.exception.WrongInputException;
import ua.com.alevel.model.dto.AccountRecord;
import ua.com.alevel.model.dto.AccountReport;
import ua.com.alevel.model.dto.SaveIncomeOperation;
import ua.com.alevel.model.dto.SaveOutcomeOperation;
import ua.com.alevel.model.entity.CategoryType;
import ua.com.alevel.parser.ReportParser;
import ua.com.alevel.service.AccountService;
import ua.com.alevel.service.OperationService;
import ua.com.alevel.service.jdbc.JdbcAccountService;
import ua.com.alevel.service.jpa.JpaOperationService;
import ua.com.alevel.view.FinanceManagementView;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

public class FinanceManagementController {

    private static final FinanceManagementView view = new FinanceManagementView();
    private static final ReportParser parser = new ReportParser();

    public void run(String username, String password) {
        Configuration configuration = new Configuration().configure()
                .setProperty(Environment.USER, username)
                .setProperty(Environment.PASS, password);


        try (SessionFactory sf = configuration.buildSessionFactory(); Session session = sf.openSession()) {

            EntityManager em = sf.createEntityManager();
            Supplier<EntityManager> supplier = () ->  {return em;};

            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

            OperationService operationService = new JpaOperationService(supplier, validatorFactory);
            AccountService accountService = new JdbcAccountService("jdbc:mysql://localhost/finance_management?serverTimezone=UTC", username, password);

            while(true) {
                String option = view.getToDoOption();

                switch ((option)) {
                    case "1":
                        CategoryType type = view.getCategoryType();

                        if (type == CategoryType.INCOME) {
                            String category = view.chooseIncomeCategory();
                            switch(category) {
                                case "1":
                                    category = "salary";
                                    break;
                                case "2":
                                    category = "dividends";
                                    break;
                                case "3":
                                    category = "pension";
                                    break;
                                case "4":
                                    category = "trade";
                                    break;
                                case "5":
                                    category = "other";
                                default:
                                    throw new WrongInputException("1 - 5", category);
                            }
                            AccountRecord account = accountService.getByOwnerId(Long.parseLong(view.getUserId()));
                            Long turnover = view.getTurnover();
                            SaveIncomeOperation operation = new SaveIncomeOperation(account, turnover, category);

                            operationService.save(operation);
                        } else {
                            String category = view.chooseOutcomeCategory();
                            switch(category) {
                                case "1":
                                    category = "food";
                                    break;
                                case "2":
                                    category = "transport";
                                    break;
                                case "3":
                                    category = "banking";
                                    break;
                                case "4":
                                    category = "entertainment";
                                    break;
                                case "5":
                                    category = "services";
                                    break;
                                case "6":
                                    category = "other";
                                    break;
                                default:
                                    throw new WrongInputException("1 - 6", category);
                            }

                            AccountRecord account = accountService.getByOwnerId(Long.parseLong(view.getUserId()));
                            Long turnover = view.getTurnover();
                            SaveOutcomeOperation operation = new SaveOutcomeOperation(account, turnover, category);
                            operationService.save(operation);
                        }
                        break;
                    case "2":
                        List<String> timeLimits = view.getTimeLimits();
                        AccountReport report = accountService
                                .getReportById(Long.parseLong(view.getUserId()), timeLimits.get(0), timeLimits.get(1));
                        parser.writeReport(report);
                        break;
                    case "0":
                        System.exit(0);
                    default:
                        throw new WrongInputException("1, 2 or 0", option);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
        }
    }
}
