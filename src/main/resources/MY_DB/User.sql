create table MY_DB.User
(
    user_id  int auto_increment
        primary key,
    username varchar(20)                 not null,
    password varchar(20)                 not null,
    name     varchar(20)                 null,
    phone    varchar(20)                 null,
    role     enum ('teacher', 'student') not null
);

