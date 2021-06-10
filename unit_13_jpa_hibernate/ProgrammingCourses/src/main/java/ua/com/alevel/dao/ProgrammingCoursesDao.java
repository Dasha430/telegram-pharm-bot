package ua.com.alevel.dao;

import ua.com.alevel.dto.TeacherGroupDto;
import ua.com.alevel.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class ProgrammingCoursesDao {

    public Group createGroup(EntityManager jpa, String group, Course course) {

        EntityTransaction transaction = jpa.getTransaction();
        transaction.begin();
        try {

            Group g = new Group();
            g.setName(group);

            Course persisted = jpa.find(Course.class, course.getId());

            g.setCourse(persisted);
            jpa.persist(g);
            persisted.addGroup(g);
            jpa.merge(persisted);
            transaction.commit();
            return g;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("failed to create group");
        }
    }

    public Course createCourse(EntityManager jpa, String courseName) {

        EntityTransaction transaction = jpa.getTransaction();
        transaction.begin();
        try {

            Course c = new Course();
            c.setName(courseName);
            jpa.persist(c);
            transaction.commit();
            return c;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("failed to create course");
        }
    }

    public Topic createTopic(EntityManager jpa, String topic, Course course) {
        EntityTransaction transaction = jpa.getTransaction();
        transaction.begin();
        try {

            Topic t = new Topic();
            t.setName(topic);

            Course persisted = jpa.find(Course.class, course.getId());
            t.addCourse(persisted);
            jpa.persist(t);
            persisted.addTopic(t);
            jpa.merge(persisted);

            transaction.commit();
            return t;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("failed to create topic");
        }
    }

    public Teacher createTeacher(EntityManager jpa, String name) {

        EntityTransaction transaction = jpa.getTransaction();
        transaction.begin();
        try {

            Teacher teacher = new Teacher();
            teacher.setName(name);
            jpa.persist(teacher);
            transaction.commit();
            return teacher;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("failed to create teacher");
        }
    }

    public Student createStudent(EntityManager jpa, String s, Group group) {
        EntityTransaction transaction = jpa.getTransaction();
        transaction.begin();
        try {

            Student student = new Student();
            student.setName(s);

            Group persisted = jpa.find(Group.class, group.getId());
            student.setGroup(persisted);
            persisted.addStudent(student);

            jpa.persist(student);
            jpa.merge(persisted);

            transaction.commit();
            return student;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("failed to create student");
        }
    }

    public Lesson createLesson(EntityManager jpa, String name, String start, String finish, Topic topic) {
        EntityTransaction transaction = jpa.getTransaction();
        transaction.begin();
        try {

            Lesson lesson = new Lesson();
            lesson.setName(name);
            lesson.setStartsAt(Timestamp.valueOf(start));
            lesson.setEndsAt(Timestamp.valueOf(finish));

            Topic persisted = jpa.find(Topic.class, topic.getId());
            lesson.setTopic(persisted);
            jpa.persist(lesson);
            persisted.addLesson(lesson);

            jpa.merge(persisted);

            transaction.commit();
            return lesson;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("failed to create lesson");
        }
    }

    public void addMarkToStudent(EntityManager jpa, Integer mark, Student student, Lesson lesson) {
        EntityTransaction transaction = jpa.getTransaction();
        transaction.begin();
        try {

            Student persistedStudent = jpa.find(Student.class, student.getId());
            Lesson persistedLesson = jpa.find(Lesson.class, lesson.getId());
            Mark newMark = new Mark();
            newMark.setMarkValue(mark);
            newMark.setStudent(persistedStudent);
            newMark.setLesson(persistedLesson);

            persistedStudent.addMark(newMark);
            persistedLesson.addMark(newMark);

            jpa.persist(newMark);
            jpa.merge(persistedLesson);
            jpa.merge(persistedStudent);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("failed to add mark to student");
        }
    }

    public void addLessonToTeacher(EntityManager jpa, Lesson lesson, Teacher teacher) {

        EntityTransaction transaction = jpa.getTransaction();
        transaction.begin();
        try {
            Lesson persistedLesson = jpa.find(Lesson.class, lesson.getId());
            Teacher persistedTeacher = jpa.find(Teacher.class, teacher.getId());

            persistedTeacher.addLesson(persistedLesson);
            persistedLesson.setTeacher(persistedTeacher);

            jpa.merge(persistedLesson);
            jpa.merge(persistedTeacher);

            transaction.commit();
        } catch (Exception e) {
           transaction.rollback();
            throw new RuntimeException("failed to add lesson to teacher");
        }

    }

    public List<Lesson> findNextLessonByStudentId(EntityManager jpa, Long id) {

        EntityTransaction transaction = jpa.getTransaction();
        transaction.begin();
        try{
            String nextLessonDate = "select min(startsAt) from Lesson l where l.startsAt > current_timestamp";
            Date d = jpa
                    .createQuery(nextLessonDate, Date.class)
                    .getSingleResult();
            Timestamp lessonDate = new Timestamp(d.getTime());

            String hql = "select l from Lesson l " +
                    "join l.topic topic " +
                    "join topic.courses course " +
                    "join course.groups coursegroup " +
                    "join coursegroup.students student " +
                    "where student.id = :id and l.startsAt = :starts";
            TypedQuery<Lesson> typedQuery = jpa.createQuery(hql, Lesson.class);
            typedQuery.setParameter("id", id);
            typedQuery.setParameter("starts", lessonDate);
            transaction.commit();
            return typedQuery.getResultList();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("failed to find next lesson by student id");
        }


    }

    public TeacherGroupDto findMostSuccessfulGroupByTeacherId(EntityManager jpa, Long id) {

        EntityTransaction transaction = jpa.getTransaction();
        transaction.begin();

        try {
            String sqlQuery = "select distinct cg.id, cg.name, cg.course_id from course_groups cg" +
                    "    inner join students s on cg.id = s.group_id " +
                    "inner join marks m on s.id = m.student_id " +
                    "inner join lessons l on m.lesson_id = l.id " +
                    "inner join teachers t on l.teacher_id = t.id " +
                    "where t.id = :id and l.name = 'writing module' and cg.id in " +
                    "    (select sub.group_id from (select max(max_median), group_id from " +
                    "    (select row_number() over(partition by group_id order by mark_value) max_median, group_id " +
                    "     from students s inner join marks m  on s.id = m.student_id ) as joined " +
                    "    where joined.max_median = floor( " +
                    "          ((select count(mark_value) " +
                    "          from students s inner join marks m  on s.id = m.student_id " +
                    "          where s.group_id) + 1)/2)) as sub)";

            Query query = jpa.createNativeQuery(sqlQuery);
            query.setParameter("id", id);
            List<Object[]> resultSet = query.getResultList();
            Course course = jpa.find(Course.class, Long.parseLong(resultSet.get(0)[2].toString()));
            transaction.commit();
            return new TeacherGroupDto(Long.parseLong(resultSet.get(0)[0].toString()), (String)resultSet.get(0)[1], course);

        }
        catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("failed to find group by teacher id");
        }

    }
}
