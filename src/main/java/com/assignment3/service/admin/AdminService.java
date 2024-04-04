package com.assignment3.service.admin;

import com.assignment3.entity.Course;
import com.assignment3.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 * All the methods for admin to create a course or a user.
 */
public class AdminService {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("example-unit");
    EntityManager em = emf.createEntityManager();

    /**
     * a method for admin to create a new course.
     * @param courseName the course name of the new course.
     * @param semester the semester(1 or 2) of the new course.
     * the courseId will be generated automatically.
     */
    public void addCourse(String courseName, int semester) {
        // Begin transaction
        em.getTransaction().begin();
        // Create and persist an employee entity
        Course course = new Course(courseName, semester);
        em.persist(course);

        // Commit transaction
        em.getTransaction().commit();

        // Retrieve a course by ID
        Course retrievedCourse = em.find(Course.class, course.getCourseId());
        System.out.println("Course: " + retrievedCourse.getCourseName() + ", " + retrievedCourse.getSemester());

        // Close EntityManager and EntityManagerFactory
        em.close();
        emf.close();
    }

    /**
     * a method for admin to create a new user. Can be a student or a teacher.
     * @param userName the username of the user used to log in.
     * @param password the password of the user used to log in.
     * @param name the name of the user.
     * @param phone the phone number of the user.
     * @param role the user should be a teacher or a student.
     */
    public void addUser(String userName, String password, String name, String phone, String role) {

        // Begin transaction
        em.getTransaction().begin();
        // Create and persist an employee entity
        User user = new User(userName, password, name, phone, role);
        em.persist(user);

        // Commit transaction
        em.getTransaction().commit();

        // Retrieve an employee by ID
        User retrievedUser = em.find(User.class, user.getUserId());
        System.out.println("User: " + retrievedUser.getUsername() + ", " + retrievedUser.getName() +
                ", " + retrievedUser.getPhone() + ", " + retrievedUser.getRole());

        // Close EntityManager and EntityManagerFactory
        em.close();
        emf.close();
    }
}
