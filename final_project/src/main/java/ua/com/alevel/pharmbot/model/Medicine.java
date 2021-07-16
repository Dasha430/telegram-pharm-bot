package ua.com.alevel.pharmbot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name="medicines")
public class Medicine {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column
    private String instruction;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Form form;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.medicine", cascade=CascadeType.ALL)
    private Set<MedInPharmacy> medInPharmacySet = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.medicine", cascade = CascadeType.ALL)
    private List<Search> searched = new ArrayList<>();


}
