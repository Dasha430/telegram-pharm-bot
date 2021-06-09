package ua.com.alevel.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ua.com.alevel.entity.*;

@Getter
@Setter
@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "starts_at", nullable = false)
    private Timestamp startsAt;

    @Column(name = "ends_at", nullable = false)
    private Timestamp endsAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @OneToMany(mappedBy = "lesson")
    private List<Mark> marks = new ArrayList<>();

    public void addMark(Mark mark) {
        this.marks.add(mark);
    }

}
