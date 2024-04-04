create table Assessment
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

create table Course
(
    course_id   int auto_increment
        primary key,
    course_name varchar(20)     not null,
    semester    enum ('1', '2') not null
);

create table User
(
    user_id  int auto_increment
        primary key,
    username varchar(20)                 not null,
    password varchar(20)                 not null,
    name     varchar(20)                 null,
    phone    varchar(20)                 null,
    role     enum ('teacher', 'student') not null
);

create table UserCourse
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


