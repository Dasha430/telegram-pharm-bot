package ua.com.alevel.model.entity;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.annotation.IsCategory;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "income_categories")
@IsCategory
public class IncomeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "incomeCategories")
    private Set<Operation> operations = new HashSet<>();


}
