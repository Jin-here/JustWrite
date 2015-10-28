package com.example.po;

public class Student {
	private int id;
	private String name;
	private Classes cls;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(int id, String name, Classes cls) {
		super();
		this.id = id;
		this.name = name;
		this.cls = cls;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", cls=" + cls + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Classes getCls() {
		return cls;
	}
	public void setCls(Classes cls) {
		this.cls = cls;
	}
	
	
}
