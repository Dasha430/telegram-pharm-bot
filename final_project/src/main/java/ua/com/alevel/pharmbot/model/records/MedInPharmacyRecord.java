package ua.com.alevel.pharmbot.model.records;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ua.com.alevel.pharmbot.model.Medicine;
import ua.com.alevel.pharmbot.model.Pharmacy;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class MedInPharmacyRecord {
    private Medicine medicine;
    private Pharmacy pharmacy;
    private BigDecimal price;
}
