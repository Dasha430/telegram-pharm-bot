package ua.com.alevel.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Getter
@Setter
@Entity
@Table(name = "med_in_pharmacy")
@AssociationOverrides({
        @AssociationOverride(name = "pk.medicine",
                joinColumns = @JoinColumn(name = "medicine_id")),
        @AssociationOverride(name = "pk.pharmacy",
                joinColumns = @JoinColumn(name = "pharmacy_id")) })
public class MedInPharmacy {

    @EmbeddedId
    private MedInPharmacyId pk = new MedInPharmacyId();

    @Column(nullable = false)
    private BigInteger price;

    @Column(nullable = false)
    private long number;

    @Transient
    public Medicine getMedicine() {
        return pk.getMedicine();
    }

    public void setMedicine(Medicine m) {
        pk.setMedicine(m);
    }

    @Transient
    public Pharmacy getPharmacy() {
       return pk.getPharmacy();
    }

    public void setPharmacy(Pharmacy p) {
        pk.setPharmacy(p);
    }
}
