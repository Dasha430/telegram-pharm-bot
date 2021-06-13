package ua.com.alevel.parser;

import com.opencsv.CSVWriter;
import org.apache.log4j.Logger;
import ua.com.alevel.model.dto.AccountReport;
import ua.com.alevel.model.entity.Operation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportParser {

    private static final Logger log = Logger.getLogger(ReportParser.class);
    private final String FILEPATH = "module_3/finance-management/src/main/resources/";

    public File createFile(AccountReport report) {

        String filename = FILEPATH + "account" + report.getId() + ".csv";
        File file = new File(filename);

        try {
            if (file.createNewFile()) {
                log.info("file " + filename + " created successfully");
            } else throw new IOException();

            return file;
        } catch (IOException e) {
            log.error("Error while working with file");
            throw new RuntimeException();
        }

    }

    public void writeReport(AccountReport report) {

        List<String[]> data = new ArrayList<>();

        String[] title = {"category_type", "performed_at", "turnover"};
        data.add(title);

        for (Operation op: report.getOperations()) {
            String[] values = {op.getCategoryType().toString(),
                    op.getPerformedAt().toString(),
                    op.getTurnover().toString()};
            data.add(values);
        }

        String[] income = {"income", report.getAllIncome().toString()};
        data.add(income);
        String[] balance = {"balance", report.getBalance().toString()};
        data.add(balance);
        String[] surplus = {"surplus", report.getSurplus().toString()};
        data.add(surplus);

        File file = createFile(report);
        try (CSVWriter writer = new CSVWriter(new FileWriter(FILEPATH + file.getName()))) {
            writer.writeAll(data);

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
