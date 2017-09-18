package com.laining.mybatis.demo.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import com.laining.mybatis.demo.entity.Student;
import com.laining.mybatis.demo.entity.StudentToTeacher;

public interface StudentMapper {

	@Insert("insert into Student(name,create_time,update_on) values (#{name},#{createOn},#{updateOn}) ")
	public void insertStudent(Student student);

	@Update("update Student set name=#{name},update_on=#{updateOn} where id=#{id}")
	public void updateStudent(Student student);

	@Insert("insert into student_to_teacher(student,teacher) values (#{sid},#{tid}) ")
	public void addStudentTeacher(StudentToTeacher studentToTeacher);

	@Select("select * from Student where  id=#{id} limit 1")
	public Student findStudent(long id);

	@Select("select * from Student where  name=#{name} limit 1")
	public Student findStudentByName(String name);

	@Results(id = "studentResultWithTeacher", value = { @Result(property = "id", column = "id", id = true),
			@Result(property = "name", column = "name"), @Result(property = "updateOn", column = "update_on"),
			@Result(property = "createOn", column = "create_time"),
			@Result(property = "teachers", column = "teacher", many = @Many(select = "com.laining.mybatis.demo.mappers.TeacherMapper.findTeacher", fetchType = FetchType.LAZY)) })
	@Select("select * from Student s left join student_to_teacher st on s.id = st.student  where s.id=#{id} limit 1")
	public Student findStudentWithTeachers(long id);

}
