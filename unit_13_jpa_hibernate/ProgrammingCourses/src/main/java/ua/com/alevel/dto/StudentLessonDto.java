package ua.com.alevel.dto;

import lombok.Value;
import ua.com.alevel.entity.Teacher;
import ua.com.alevel.entity.Topic;

import java.sql.Timestamp;

@Value
public class StudentLessonDto {

    Timestamp startsAt;
    Timestamp endsAt;
    Teacher teacher;
    Topic topic;
}
