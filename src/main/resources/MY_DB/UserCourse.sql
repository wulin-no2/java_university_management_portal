create table MY_DB.UserCourse
(
    user_course_id int auto_increment
        primary key,
    user_id        int not null,
    course_id      int not null,
    constraint UserCourse___fk
        foreign key (user_id) references my_db.user (user_id),
    constraint UserCourse___fk1
        foreign key (course_id) references my_db.Course (course_id)
);

