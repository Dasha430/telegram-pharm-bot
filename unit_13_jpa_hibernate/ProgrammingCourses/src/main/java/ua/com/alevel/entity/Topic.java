package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ua.com.alevel.entity.*;

@Getter
@Setter
@Entity
@Table(name = "topics")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "topics")
    private Set<Course> courses = new HashSet<>();

    @OneToMany(mappedBy = "topic")
    private List<Lesson> lessons = new ArrayList<>();

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void addLesson(Lesson lesson) {
        this.lessons.add(lesson);
    }
}
