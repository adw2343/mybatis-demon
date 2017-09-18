package com.laining.mybatis.demo;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.laining.mybatis.demo.entity.Student;
import com.laining.mybatis.demo.entity.StudentToTeacher;
import com.laining.mybatis.demo.entity.Teacher;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
public class MybatisDemonApplicationTests {

	@Autowired
	SqlSessionFactory sqlSessionFactory = null;

	@Test
	public void contextLoads() {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			Student student = new Student();
			student.setName("学生");
			session.insert("com.laining.mybatis.demo.mappers.StudentMapper.insertStudent", student);

			Teacher teacher = new Teacher();
			teacher.setName("老师");
			session.insert("com.laining.mybatis.demo.mappers.TeacherMapper.insertTeacher", teacher);

			Student existedStudent = session
					.selectOne("com.laining.mybatis.demo.mappers.StudentMapper.findStudentByName", "学生");
			Assert.assertNotNull(existedStudent);
			Teacher existedTeacher = session
					.selectOne("com.laining.mybatis.demo.mappers.TeacherMapper.findTeacherByName", "老师");
			Assert.assertNotNull(existedTeacher);
			StudentToTeacher studentToTeacher = new StudentToTeacher(existedStudent.getId(), existedTeacher.getId());
			session.insert("com.laining.mybatis.demo.mappers.StudentMapper.addStudentTeacher", studentToTeacher);

			student = session.selectOne("com.laining.mybatis.demo.mappers.StudentMapper.findStudentWithTeachers",
					existedStudent.getId());
			student.getTeachers();
			Assert.assertTrue(student.getTeachers().size() == 1);
			teacher = session.selectOne("com.laining.mybatis.demo.mappers.TeacherMapper.findTeacherWithStudents",
					existedTeacher.getId());
			teacher.getStudents();
			Assert.assertTrue(teacher.getStudents().size() == 1);

		}

	}

}
