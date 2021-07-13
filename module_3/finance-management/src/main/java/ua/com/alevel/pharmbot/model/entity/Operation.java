package ua.com.alevel.model.entity;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.annotation.IsCategory;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "operations")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    private CategoryType categoryType;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "operation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "in_category_id", referencedColumnName = "id")
    )
    private Set<IncomeCategory> incomeCategories = new HashSet<>();

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "operation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "out_category_id", referencedColumnName = "id")
    )
    private Set<OutcomeCategory> outcomeCategories = new HashSet<>();

    @Column(name = "performed_at", nullable = false)
    private Instant performedAt;

    @Column(nullable = false)
    private Long turnover;

    @PrePersist
    public void onCreate() {
        if (performedAt == null) {
            performedAt = Instant.now();
        }
    }

    public void addCategory(Object category) {
        Class<?> classOfCategory = category.getClass();

        if(classOfCategory.isAnnotationPresent(IsCategory.class)) {
            if (classOfCategory.getName().equals("ua.com.alevel.model.entity.IncomeCategory")) {
                this.incomeCategories.add((IncomeCategory) category);
            } else {
                this.outcomeCategories.add((OutcomeCategory) category);
            }
            return;
        }

        throw new RuntimeException("Wrong type");
    }
}
