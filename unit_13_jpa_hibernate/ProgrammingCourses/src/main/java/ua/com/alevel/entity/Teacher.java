package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import ua.com.alevel.entity.*;

@Getter
@Setter
@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "teacher")
    private List<Lesson> lessons = new ArrayList<>();

    public void addLesson(Lesson lesson) {
        this.lessons.add(lesson);
    }

}
