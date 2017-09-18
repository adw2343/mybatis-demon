CREATE DATABASE IF NOT EXISTS testdb3 DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
use testdb3;
drop table if exists student_to_teacher;
drop table if exists Teacher;
drop table if exists Student;
create table  if not exists Teacher(
	id bigint(20) not null primary key auto_increment,
	name varchar(80) default null,
	create_time datetime not null,
	update_on datetime not null
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;

create table  if not exists Student(
	id bigint(20) not null primary key auto_increment,
	name varchar(80) default null,
	create_time datetime not null,
	update_on datetime not null
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;


create table  if not exists student_to_teacher(
	id bigint(20) not null primary key auto_increment,
	student bigint(20) not null,
	teacher bigint(20) not null,
	foreign key `fk_student_teacher`(`teacher`) references `Teacher`(`id`) on delete cascade,
	foreign key `fk_teacher_student`(`student`) references `Student`(`id`) on delete cascade
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;





