package ua.com.alevel.entity;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.entity.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "marks")
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mark_value", nullable = false)
    private Integer markValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "marked_at", nullable = false)
    private Instant markedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    public Mark() {
        markedAt = Instant.now();
    }
}
