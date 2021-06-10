package ua.com.alevel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.com.alevel.dto.StudentLessonDto;
import ua.com.alevel.dto.TeacherGroupDto;
import ua.com.alevel.entity.*;
import ua.com.alevel.service.jpa.JpaProgrammingCoursesService;

import javax.persistence.EntityManager;
import java.util.function.Supplier;

public class ProgrammingCoursesMain {

    public static void main(String[] args) {

        Configuration configuration = new Configuration().configure();


        try(SessionFactory sf = configuration.buildSessionFactory();
            Session session = sf.openSession()) {
            EntityManager em = sf.createEntityManager();

            Supplier<EntityManager> supplier = () ->  {return em;};
            JpaProgrammingCoursesService service = new JpaProgrammingCoursesService(supplier) ;
            Course course = service.createCourse("Java");
            Group group1 = service.createGroup("Group1", course);
            Group group2 = service.createGroup("Group2", course);

            Topic topic1 = service.createTopic("JPA and Hibernate", course);
            Topic topic2 = service.createTopic("JDBC", course);
            Topic topic3 = service.createTopic("Module", course);
            Topic topic4 = service.createTopic("Spring", course);

            Lesson lesson1 = service.createLesson(
                    "lesson 1",
                    "2021-06-01 11:00:00",
                    "2021-06-01 14:00:00",
                    topic2 );
            Lesson lesson2 = service.createLesson(
                    "lesson 2",
                    "2021-06-05 11:00:00",
                    "2021-06-05 14:00:00",
                    topic1);
            Lesson lesson3 = service.createLesson(
                    "writing module",
                    "2021-06-07 11:00:00",
                    "2021-06-07 14:00:00",
                    topic3);
            Lesson lesson4 = service.createLesson(
                    "topic 4",
                    "2021-06-10 11:00:00",
                    "2021-06-10 14:00:00",
                    topic4);

            Teacher teacher1 = service.createTeacher("Teacher1");

            service.addLessonToTeacher(lesson1, teacher1);
            service.addLessonToTeacher(lesson2, teacher1);
            service.addLessonToTeacher(lesson3, teacher1);
            service.addLessonToTeacher(lesson4, teacher1);

            Student student1 = service.createStudent("Stud 1", group1);
            Student student2 = service.createStudent("Stud 2", group1);
            Student student3 = service.createStudent("Stud 3", group1);
            Student student4 = service.createStudent("Stud 4", group2);
            Student student5 = service.createStudent("Stud 5", group2);
            Student student6 = service.createStudent("Stud 6", group2);

            service.addMarkToStudent(8, student1, lesson3);
            service.addMarkToStudent(10, student2, lesson3);
            service.addMarkToStudent(5, student3, lesson3);
            service.addMarkToStudent(8, student4, lesson3);
            service.addMarkToStudent(8, student5, lesson3);
            service.addMarkToStudent(6, student6, lesson3);

            Long studentId = 2L;
            StudentLessonDto studentLessonDto = service.findNextLessonByStudentId(studentId);
            System.out.println(" Next lesson for student #" + studentId);
            System.out.println(studentLessonDto.getTopic().getName());
            System.out.println(studentLessonDto.getStartsAt() + " - " + studentLessonDto.getEndsAt());
            System.out.println(studentLessonDto.getTeacher().getName());

            Long teacherId = 1L;
            TeacherGroupDto teacherGroupDto = service.findMostSuccessfulGroupByTeacherId(teacherId);
            System.out.println("The most successful group:");
            System.out.println(teacherGroupDto.getName() + " course: " + teacherGroupDto.getCourse().getName());

            session.close();

        }
    }
}
