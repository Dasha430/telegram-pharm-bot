package ua.com.alevel.view;

import lombok.SneakyThrows;
import ua.com.alevel.controller.FinanceManagementController;
import ua.com.alevel.exception.WrongInputException;
import ua.com.alevel.pharmbot.model.entity.CategoryType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FinanceManagementView {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final FinanceManagementController controller = new FinanceManagementController();

    @SneakyThrows
    public void run() {


        System.out.println("To work with this app please enter username and password");
        System.out.println("to connect to database");

        System.out.print("username: ");
        String username = reader.readLine();
        System.out.println();
        System.out.print("password: ");
        String password = reader.readLine();
        controller.run(username, password);

    }


    public CategoryType getCategoryType() throws WrongInputException, IOException {
        System.out.println("Select category type of operation");
        System.out.println("1 - income category");
        System.out.println("2 - outcome category");
        String option = reader.readLine();

        if ("1".equals(option)) {
            return CategoryType.INCOME;
        } else if ("2".equals(option)) {
            return CategoryType.OUTCOME;
        } else {
            throw new WrongInputException("1 or 2", option);
        }

    }

    public String getToDoOption() throws IOException {
        System.out.println("Enter what you would like to do:");
        System.out.println("1 - perform income/outcome operation");
        System.out.println("2 - get account report in csv format (it will be saved in resources module)");
        System.out.println("0 - exit");
        return reader.readLine();
    }

    public String getUserId() throws IOException {
        System.out.println("Enter your user id: ");
        return reader.readLine();
    }

    public String chooseIncomeCategory() throws IOException {
        System.out.println("1 - salary");
        System.out.println("2 - dividends");
        System.out.println("3 - pension");
        System.out.println("4 - trade");
        System.out.println("5 - other");

        return reader.readLine();
    }

    public String chooseOutcomeCategory() throws IOException {
        System.out.println("1 - food");
        System.out.println("2 - transport");
        System.out.println("3 - banking");
        System.out.println("4 - entertainment");
        System.out.println("5 - services");
        System.out.println("6 - other");

        return reader.readLine();
    }

    public Long getTurnover() throws IOException {
        System.out.println("Enter the amount of money: ");
        return Long.parseLong(reader.readLine());
    }

    public List<String> getTimeLimits() throws IOException {
        List<String> limits = new ArrayList<>();

        System.out.println("Enter time limits of the operations in the report (format: yyyy-mm-dd): ");

        System.out.println("Start: ");
        limits.add(reader.readLine());

        System.out.println("End: ");
        limits.add(reader.readLine());
        return limits;

    }




}
