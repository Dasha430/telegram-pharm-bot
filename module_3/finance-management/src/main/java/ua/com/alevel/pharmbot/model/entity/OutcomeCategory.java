package ua.com.alevel.pharmbot.model.entity;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.annotation.IsCategory;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "outcome_categories")
@IsCategory
public class OutcomeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "outcomeCategories")
    private Set<Operation> operations = new HashSet<>();

}
