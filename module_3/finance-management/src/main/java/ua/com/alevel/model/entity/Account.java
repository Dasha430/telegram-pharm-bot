package ua.com.alevel.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(nullable = false)
    private String password;

    @Column(name = "user_balance")
    private long balance;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Operation> operations = new ArrayList<>();

    public void addOperation(Operation operation) {
        this.operations.add(operation);
    }
}
