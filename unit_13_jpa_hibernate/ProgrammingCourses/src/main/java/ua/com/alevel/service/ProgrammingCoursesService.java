package ua.com.alevel.service;

import ua.com.alevel.dto.StudentLessonDto;
import ua.com.alevel.dto.TeacherGroupDto;
import ua.com.alevel.entity.*;

import javax.persistence.EntityManager;


public interface ProgrammingCoursesService {

    Group createGroup(String group, String course, EntityManager em);

    Course createCourse(String courseName, EntityManager em);

    Topic createTopic(String topic, EntityManager em);

    Teacher createTeacher(String teacher, EntityManager em);

    Student createStudent(String s, EntityManager em);

    Lesson createLesson(String name, String start, String finish, EntityManager em);

    Mark createMark(int mark);

    void addStudentToGroup(Group group, Student student);

    void addGroupToCourse(Group group, Course course, EntityManager em);

    void addTopicToCourse(Topic topic, Course course);

    void addLessonToTopic(Lesson lesson, Topic topic);

    void addMarkToStudent(Mark mark, Student student);

    void addLessonToTeacher(Lesson lesson, Topic topic);

    StudentLessonDto findLatestLessonByStudentId(Long id);

    TeacherGroupDto findMostSuccessfulGroupByTeacherId(Long id);


}
