create table MY_DB.Course
(
    course_id   int auto_increment
        primary key,
    course_name varchar(20)     not null,
    semester    enum ('1', '2') not null
);

