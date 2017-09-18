package com.laining.mybatis.demo.entity;

public class StudentToTeacher {

	private long sid;
	private long tid;

	public StudentToTeacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentToTeacher(long sid, long tid) {
		super();
		this.sid = sid;
		this.tid = tid;
	}

	public long getSid() {
		return sid;
	}

	public void setSid(long sid) {
		this.sid = sid;
	}

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

}
