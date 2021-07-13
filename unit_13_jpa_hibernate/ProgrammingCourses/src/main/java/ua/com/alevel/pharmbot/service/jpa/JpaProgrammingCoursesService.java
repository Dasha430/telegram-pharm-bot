package ua.com.alevel.service.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.ProgrammingCoursesDao;
import ua.com.alevel.dto.StudentLessonDto;
import ua.com.alevel.dto.TeacherGroupDto;
import ua.com.alevel.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import java.util.List;
import java.util.function.Supplier;


public class JpaProgrammingCoursesService  {

    private static final Logger log = LoggerFactory.getLogger(JpaProgrammingCoursesService.class);

    private final Supplier<EntityManager> persistence;

    private ProgrammingCoursesDao dao = new ProgrammingCoursesDao();

    public JpaProgrammingCoursesService(Supplier<EntityManager> persistence) {
        this.persistence = persistence;
    }

    public Group createGroup(String group, Course course) {

        EntityManager jpa = persistence.get();

        try {
            log.info("Start creating group");
            Group g = dao.createGroup(jpa, group, course);
            log.info("Finish creating group");
            return g;
        } catch (Exception e) {
            throw new RuntimeException("failed to create group");
        }
    }


    public Course createCourse(String courseName) {
        EntityManager jpa = persistence.get();

        EntityTransaction transaction = jpa.getTransaction();
        transaction.begin();
        try {
            log.info("Start creating course");
            Course c = dao.createCourse(jpa, courseName);
            log.info("Finish creating course");
            return c;
        } catch (Exception e) {
            throw new RuntimeException("failed to create course");
        }
    }


    public Topic createTopic(String topic, Course course) {
        EntityManager jpa = persistence.get();
        try {
            log.info("Start creating topic");
            Topic t = dao.createTopic(jpa, topic, course);
            log.info("Finish creating topic");
            return t;
        } catch (Exception e) {
            throw new RuntimeException("failed to create topic");
        }
    }


    public Teacher createTeacher(String name) {
        EntityManager jpa = persistence.get();

        try {
            log.info("Start creating teacher");
            Teacher teacher = dao.createTeacher(jpa, name);
            log.info("Finish creating teacher");
            return teacher;
        } catch (Exception e) {
            throw new RuntimeException("failed to create teacher");
        }
    }


    public Student createStudent(String s, Group group) {
        EntityManager jpa = persistence.get();

        try {
            log.info("Start creating student");
            Student student = dao.createStudent(jpa, s, group);

            log.info("Finish creating student");
            return student;
        } catch (Exception e) {
            throw new RuntimeException("failed to create student");
        }
    }


    public Lesson createLesson(String name, String start, String finish, Topic topic) {
        EntityManager jpa = persistence.get();

        try {
            log.info("Start creating lesson");

            Lesson lesson = dao.createLesson(jpa, name, start, finish, topic);
            log.info("Finish creating lesson");
            return lesson;
        } catch (Exception e) {
            throw new RuntimeException("failed to create lesson");
        }
    }



    public void addMarkToStudent(Integer mark, Student student, Lesson lesson) {
        EntityManager jpa = persistence.get();

        try {
            log.info("Start adding mark to student");
            dao.addMarkToStudent(jpa, mark, student, lesson);

            log.info("Finish adding mark to student");
        } catch (Exception e) {
            throw new RuntimeException("failed to add mark to student");
        }
    }


    public void addLessonToTeacher(Lesson lesson, Teacher teacher) {

        EntityManager jpa = persistence.get();

        try {
            log.info("Start adding lesson to teacher");
            dao.addLessonToTeacher(jpa, lesson, teacher);
            log.info("Finish adding lesson to teacher");
        } catch (Exception e) {
            throw new RuntimeException("fail to add lesson to teacher");
        }

    }


    public StudentLessonDto findNextLessonByStudentId(Long id) {
        EntityManager jpa = persistence.get();

        try{
            log.info("Start finding lesson by student id");
            List<Lesson> result = dao.findNextLessonByStudentId(jpa, id);
            log.info("Finish finding lesson by student id");
            return mergeLessonWithDto(result.get(0));

        } catch (Exception e) {
            throw new RuntimeException("failed to find next lesson by student id");
        }


    }

    public TeacherGroupDto findMostSuccessfulGroupByTeacherId(Long id) {
        EntityManager jpa = persistence.get();


        try {
            log.info("Start finding group by teacher id");

            TeacherGroupDto dto = dao.findMostSuccessfulGroupByTeacherId(jpa, id);
            log.info("Finish finding group by teacher id");
            return dto;
        } catch (Exception e) {
            throw new RuntimeException("failed to find group by teacher id");
        }

    }

    private StudentLessonDto mergeLessonWithDto(Lesson l) {
        return new StudentLessonDto(l.getStartsAt(), l.getEndsAt(), l.getTeacher(), l.getTopic());
    }
}
