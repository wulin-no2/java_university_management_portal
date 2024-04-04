package com.assignment3.entity;

import javax.persistence.*;

/**
 * Assignment entity.
 * Many-to-one relationship with User and Course.
 */
@Entity
@Table(name = "Assessment")
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assessment_id")
    private Long assessmentId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @Column(name = "marks")
    private Long marks;
    @Column(name = "assessment_type")
    private String assessmentType;

    public Assessment() {
    }

    public Assessment(User user, Course course, Long marks, String assessmentType) {
        this.user = user;
        this.course = course;
        this.marks = marks;
        this.assessmentType = assessmentType;
    }

    public Long getAssessmentId() {
        return assessmentId;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getMarks() {
        return marks;
    }

    public void setMarks(Long marks) {
        this.marks = marks;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "assessmentId=" + assessmentId +
                ", user=" + user +
                ", course=" + course +
                ", marks=" + marks +
                ", assessmentType='" + assessmentType + '\'' +
                '}';
    }
}
