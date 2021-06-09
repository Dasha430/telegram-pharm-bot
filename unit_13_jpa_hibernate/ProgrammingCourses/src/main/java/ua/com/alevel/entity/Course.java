package ua.com.alevel.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.entity.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Group> groups = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id", referencedColumnName = "id")
    )
    private Set<Topic> topics = new HashSet<>();


    public void addGroup(Group group) {
        this.groups.add(group);
    }

    public void addTopic(Topic topic) {
        this.topics.add(topic);
    }
}
