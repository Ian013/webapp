create schema if not exists faculty collate utf8_general_ci;

create table if not exists role
(
  id int auto_increment
    primary key,
  name varchar(20) null
);

create table if not exists user
(
  id int auto_increment,
  firstName varchar(45) not null,
  lastName varchar(45) not null,
  registerDate date null,
  email varchar(100) not null,
  password varchar(100) null,
  enabled tinyint(1) null,
  constraint id_UNIQUE
    unique (id),
  constraint user_email_uindex
    unique (email)
);

alter table user
  add primary key (id);

create table if not exists course
(
  id int auto_increment,
  name varchar(45) not null,
  teacher_id int not null,
  startDate date null,
  endDate date null,
  primary key (id, teacher_id),
  constraint id_UNIQUE
    unique (id),
  constraint fk_course_teacher1
    foreign key (teacher_id) references user (id)
);

create table if not exists archive
(
  id int auto_increment,
  student_id int not null,
  course_id int not null,
  note int null,
  date date null,
  constraint archive_id_uindex
    unique (id),
  constraint fk_archive_course1
    foreign key (course_id) references course (id),
  constraint fk_archive_student1
    foreign key (student_id) references user (id)
);

create index fk_archive_course1_idx
  on archive (course_id);

create index fk_archive_student1_idx
  on archive (student_id);

alter table archive
  add primary key (id);

create index fk_course_teacher1_idx
  on course (teacher_id);

create table if not exists user_has_course
(
  user_id int not null,
  course_id int not null,
  primary key (user_id, course_id),
  constraint fk_user_has_course_course1
    foreign key (course_id) references course (id),
  constraint fk_user_has_course_student1
    foreign key (user_id) references user (id)
);

create index fk_student_has_course_course1_idx
  on user_has_course (course_id);

create index fk_student_has_course_student1_idx
  on user_has_course (user_id);

create table if not exists user_has_role
(
  user_id int not null,
  role_id int not null,
  primary key (user_id, role_id),
  constraint user_has_role_role_id_fk
    foreign key (role_id) references role (id),
  constraint user_has_role_user_id_id_fk
    foreign key (user_id) references user (id)
);

create index user_has_role_user_id_index
  on user_has_role (user_id);
