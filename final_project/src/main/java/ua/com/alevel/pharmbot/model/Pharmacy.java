package ua.com.alevel.pharmbot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "pharmacies")
public class Pharmacy {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String address;

    @Column(name = "address_geocode")
    private String addressGeocode;

    @Column(name = "start_work_at", nullable = false)
    private LocalTime startWorkAt;

    @Column(name = "finish_work_at", nullable = false)
    private LocalTime finishWorkAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.pharmacy", cascade=CascadeType.ALL)
    private Set<MedInPharmacy> medInPharmacySet = new HashSet<>();

    private boolean isOpened() {
        return LocalTime.now().isAfter(this.startWorkAt) && LocalTime.now().isBefore(this.finishWorkAt);
    }
}
