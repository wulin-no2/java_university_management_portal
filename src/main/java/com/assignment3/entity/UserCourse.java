package com.assignment3.entity;

import javax.persistence.*;

/**
 * UserCourse entity. Many-to-one relationship with User and Course.
 */
@Entity
@Table(name = "UserCourse")
public class UserCourse {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_course_id")
    private Long userCourseId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public UserCourse(User user, Course course) {
        this.user = user;
        this.course = course;
    }
    public UserCourse() {
    }
    public Long getUserCourseId() {
        return userCourseId;
    }

    public void setUserCourseId(Long userCourseId) {
        this.userCourseId = userCourseId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "UserCourse{" +
                "userCourseId=" + userCourseId +
                ", course=" + (course != null ? "CourseId=" + course.getCourseId() : "null") +
                ", user=" + (user != null ? "UserId=" + user.getUserId() : "null") +
                '}';
    }
}
