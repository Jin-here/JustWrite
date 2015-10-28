package com.environmentalmonitoring.bean;

public class History_Data {
	private String name;
	private double value;
	private long time;
	public History_Data() {
		super();
		// TODO Auto-generated constructor stub
	}
	public History_Data(String name, double value, long time) {
		super();
		this.name = name;
		this.value = value;
		this.time = time;
	}
	@Override
	public String toString() {
		return "History_Data [name=" + name + ", value=" + value + ", time="
				+ time + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
	
}
