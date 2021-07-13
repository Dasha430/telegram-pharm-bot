package ua.com.alevel.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "medicine_forms")
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FormName name;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    private List<Medicine> medicines = new ArrayList<>();
}
