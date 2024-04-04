package com.assignment3.service.userService;

import com.assignment3.entity.Assessment;
import com.assignment3.entity.Course;
import com.assignment3.entity.User;
import com.assignment3.entity.UserCourse;

import javax.persistence.*;
import java.sql.*;
import java.util.List;

/**
 * All the methods for users including students and teachers.
 * Most of the methods are shared and used by all the users.
 * So we don't create StudentService and TeacherService separately.
 */

public class UserService {
    // JPA:
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("example-unit");

    /**
     * a method for students and teachers to get their enrolled or assigned courses by username.
     * @param userName useName of students or teachers.
     * @return course list.
     */
    public List<Course> getEnrolledCoursesByUserName(String userName) throws SQLException {
        EntityManager em = emf.createEntityManager();
        try {
            // Native SQL query
            String sql = "SELECT c.* FROM course c " +
                    "JOIN usercourse uc ON c.course_id = uc.course_id " +
                    "JOIN user u ON uc.user_id = u.user_id " +
                    "WHERE u.username = ?";

            // Create a native query
            Query query = em.createNativeQuery(sql, Course.class);
            query.setParameter(1, userName);

            // Execute the query and get the result list
            @SuppressWarnings("unchecked")
            List<Course> courses = query.getResultList();
            return courses;
        } finally {
            em.close();
        }
    }

    /**
     * a method for students and teachers to get their unregistered courses by username.
     * @param userName useName of students or teachers.
     * @return course list.
     */
    public List<Course> getAvailableCoursesByUserName(String userName){
        EntityManager em = emf.createEntityManager();
        try {
            // Native SQL query
            String sql = "SELECT c.* FROM course c " +
                    "WHERE c.course_id NOT IN (" +
                    "    SELECT uc.course_id " +
                    "    FROM usercourse uc " +
                    "    INNER JOIN user u ON uc.user_id = u.user_id " +
                    "    WHERE u.username = ?" +
                    ")";
            // Create a native query
            Query query = em.createNativeQuery(sql, Course.class);
            query.setParameter(1, userName);

            // Execute the query and get the result list
            @SuppressWarnings("unchecked")
            List<Course> courses = query.getResultList();
            return courses;
        } finally {
            em.close();
        }
    }

    /**
     * a method for students and teachers to register a course by courseId.
     * @param userName useName of a student or a teacher.
     * @param courseId courseId of an unregistered course.
     */
    public void registerCourse(String userName, Long courseId){
        EntityManager em = emf.createEntityManager();
        if (getUserCourseByCourseIdAndUserName(userName,courseId))
            return;

        // Begin transaction
        em.getTransaction().begin();
        User user = getUserByUserName(userName);
        Course course = getCourseByCourseId(courseId);
        // Create and persist an employee entity
        UserCourse usercourse = new UserCourse();
        usercourse.setUser(user);
        usercourse.setCourse(course);
        System.out.println("UserCourse is: " + usercourse);

        em.persist(usercourse);

        // Commit transaction
        em.getTransaction().commit();

        // Retrieve an employee by ID
        UserCourse retrievedUserCourse = em.find(UserCourse.class, usercourse.getUserCourseId());
        System.out.println("UserCourse: " + retrievedUserCourse.getUser() + ", " + retrievedUserCourse.getCourse());

        // Close EntityManager and EntityManagerFactory
        em.close();
    }

    /**
     * a helper method to help get User object by username.
     * @param userName userName of a student or a teacher.
     * @return the User object. Maybe teacher or student.
     */
    public User getUserByUserName(String userName){
        EntityManager em = emf.createEntityManager();
        try {
            // Create a sql find the user
            String sql = "SELECT * FROM user WHERE username = ?";
            Query query = em.createNativeQuery(sql, User.class);
            query.setParameter(1, userName);
            User user = (User) query.getSingleResult();
            System.out.println("=================" + user + "=======================");
            return user;
        } finally {
            em.close(); // Only close the EntityManager here
        }
    }

    /**
     * a helper method to help get Course object by courseId.
     * @param courseId courseId of a course.
     * @return the Course object.
     */
    public Course getCourseByCourseId(Long courseId){
        EntityManager em = emf.createEntityManager();
        Course course = em.find(Course.class, courseId);
        // Close EntityManager and EntityManagerFactory
        em.close();
        return course;
    }

    /**
     * a helper method to help check if there exist a UserCourse object by username and courseId.
     * a course can only be registered by a user for once.
     * @param userName userName of a student or a teacher.
     * @param courseId courseId of a course.
     * @return if the UserCourse object exists.
     */
    public Boolean getUserCourseByCourseIdAndUserName(String userName, Long courseId){
        EntityManager em = emf.createEntityManager();

        try{
            Long userId = getUserByUserName(userName).getUserId();
            String sql = "select count(*) from UserCourse " +
                    "where UserCourse.user_id = ? and course_id = ? ";
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, userId);
            query.setParameter(2, courseId);
            Number count = (Number)query.getSingleResult();
            return count.intValue() > 0;
        }finally {
            em.close();
        }
    }
    /**
     * get  assessment results of a student.
     * @param userName
     * @param courseId
     * @return
     */
    public List<Assessment> getAssessmentByCourseIdAndUserName(String userName, Long courseId){
        EntityManager em = emf.createEntityManager();
        try {
            // Native SQL query
            String sql = "SELECT * FROM Assessment a " +
                    "JOIN course c ON a.course_id = c.course_id " +
                    "JOIN user u ON u.user_id = a.user_id " +
                    "WHERE u.username = ? and a.course_id = ? and u.role = 'student'";
            // Create a native query
            Query query = em.createNativeQuery(sql, Assessment.class);
            query.setParameter(1, userName);
            query.setParameter(2, courseId);

            // Execute the query and get the result list
            @SuppressWarnings("unchecked")
            List<Assessment> assessments = query.getResultList();
            return assessments;
        } finally {
            em.close();
        }
    }

    /**
     * a method to get UserCourse list by a courseId. So that we can get students list registered the course.
     * @param courseId courseId of a course.
     * @return UserCourse list.
     */
    public List<UserCourse> getStudentsByCourseId(Long courseId){
        EntityManager em = emf.createEntityManager();
        try {
            // Native SQL query
            String sql = "SELECT * FROM UserCourse uc " +
                    "join MY_DB.User u on uc.user_id = u.user_id " +
                    "WHERE u.role = 'student' and uc.course_id = ?";
            // Create a native query
            Query query = em.createNativeQuery(sql, UserCourse.class);
            query.setParameter(1, courseId);

            // Execute the query and get the result list
            @SuppressWarnings("unchecked")
            List<UserCourse> students = query.getResultList();
            return students;
        } finally {
            em.close();
        }
    }

    /**
     * a method for teacher to update assessments of a student for a specific course.
     * a student can only have one assignment mark, one quiz mark, and one exam mark.
     * @param userName username of a student.
     * @param courseId courseId of a course.
     * @param assignmentMark the mark for the assignment of the course.
     * @param quizMark the mark for the quiz of the course.
     * @param examMark the mark for the exam of the course.
     */
    public void updateStudentAssessment(String userName, Long courseId, int assignmentMark, int quizMark, int examMark){
        EntityManager em = emf.createEntityManager();
        if (getAssessmentByCourseIdAndUserName(userName, courseId).isEmpty()){
            // Begin transaction
            em.getTransaction().begin();

            Assessment assessment1 = new Assessment(getUserByUserName(userName), getCourseByCourseId(courseId), (long) assignmentMark,"assignment");
            Assessment assessment2 = new Assessment(getUserByUserName(userName), getCourseByCourseId(courseId), (long) quizMark,"quiz");
            Assessment assessment3 = new Assessment(getUserByUserName(userName), getCourseByCourseId(courseId), (long) examMark,"exam");
            em.persist(assessment1);
            em.persist(assessment2);
            em.persist(assessment3);

            // commit
            em.getTransaction().commit();

        }
        else {
            // Begin transaction
            em.getTransaction().begin();

            // Native SQL query
            String sql =  "UPDATE Assessment a " +
                    "SET a.marks = CASE a.assessment_type " +
                    "                WHEN 'assignment' THEN ? " +
                    "                WHEN 'quiz' THEN ? " +
                    "                WHEN 'exam' THEN ? " +
                    "              END " +
                    "WHERE user_id = ? and course_id = ? " +
                    "AND a.assessment_type IN ('assignment', 'quiz', 'exam')";
//                String sql = "SELECT * FROM UserCourse uc " +
//                        "join MY_DB.User u on uc.user_id = u.user_id " +
//                        "WHERE u.role = 'student' and uc.course_id = ?";
            // Create a native query
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, assignmentMark);
            query.setParameter(2, quizMark);
            query.setParameter(3, examMark);
            query.setParameter(4, getUserByUserName(userName).getUserId());
            query.setParameter(5, courseId);

            query.executeUpdate();
            em.getTransaction().commit(); // Commit transaction
        }
        em.close();
    }
}
