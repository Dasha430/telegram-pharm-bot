package ua.com.alevel.pharmbot.model.records;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import ua.com.alevel.pharmbot.model.Medicine;
import ua.com.alevel.pharmbot.model.Pharmacy;

import java.math.BigDecimal;
import java.math.BigInteger;

@AllArgsConstructor
@Getter
public class MedInPharmacyRecord {
    private Medicine medicine;
    private Pharmacy pharmacy;
    private BigDecimal price;
}
