package com.laining.mybatis.demo.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import com.laining.mybatis.demo.entity.Teacher;

public interface TeacherMapper {
	@Insert("insert into Teacher(name,create_time,update_on) values (#{name},#{createOn},#{updateOn}) ")
	public void insertTeacher(Teacher teacher);

	@Update("update Teacher set name=#{name},update_on=#{updateOn} where id=#{id}")
	public void updateTeacher(Teacher teacher);

	@Select("select * from Teacher where  id=#{id} limit 1")
	public Teacher findTeacher(long id);

	@Select("select * from Teacher where  name=#{name} limit 1")
	public Teacher findTeacherByName(String name);

	@Results(id = "studentResultWithTeacher", value = { @Result(property = "id", column = "id", id = true),
			@Result(property = "name", column = "name"), @Result(property = "updateOn", column = "update_on"),
			@Result(property = "createOn", column = "create_time"),
			@Result(property = "students", column = "student", many = @Many(select = "com.laining.mybatis.demo.mappers.StudentMapper.findStudent", fetchType = FetchType.LAZY)) })
	@Select("select * from Teacher t left join student_to_teacher st on t.id = st.teacher  where t.id=#{id} limit 1")
	public Teacher findTeacherWithStudents(long id);
}
