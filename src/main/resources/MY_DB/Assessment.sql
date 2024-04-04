create table MY_DB.Assessment
(
    assessment_id   int auto_increment
        primary key,
    user_id         int                                 not null,
    course_id       int                                 not null,
    marks           int                                 not null,
    assessment_type enum ('exam', 'assignment', 'quiz') not null,
    constraint Assessment___fk
        foreign key (user_id) references my_db.User (user_id),
    constraint Assessment___fk1
        foreign key (course_id) references my_db.Course (course_id)
);

