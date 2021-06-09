package ua.com.alevel.dto;

import lombok.Value;
import ua.com.alevel.entity.Course;

@Value
public class TeacherGroupDto {

    Long id;

    String name;

    Course course;
}
