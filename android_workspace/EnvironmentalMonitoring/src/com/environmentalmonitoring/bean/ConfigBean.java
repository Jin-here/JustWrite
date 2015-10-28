package com.environmentalmonitoring.bean;

public class ConfigBean {
	private String Machine00_min;
	private String Machine00_max;
	
	private String Machine01_min;
	private String Machine01_max;
	
	private String Machine02_min;
	private String Machine02_max;
	
	private String Machine03_min;
	private String Machine03_max;
	public ConfigBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ConfigBean(String machine00_min, String machine00_max,
			String machine01_min, String machine01_max, String machine02_min,
			String machine02_max, String machine03_min, String machine03_max) {
		super();
		Machine00_min = machine00_min;
		Machine00_max = machine00_max;
		Machine01_min = machine01_min;
		Machine01_max = machine01_max;
		Machine02_min = machine02_min;
		Machine02_max = machine02_max;
		Machine03_min = machine03_min;
		Machine03_max = machine03_max;
	}
	@Override
	public String toString() {
		return "ConfigBean [Machine00_min=" + Machine00_min
				+ ", Machine00_max=" + Machine00_max + ", Machine01_min="
				+ Machine01_min + ", Machine01_max=" + Machine01_max
				+ ", Machine02_min=" + Machine02_min + ", Machine02_max="
				+ Machine02_max + ", Machine03_min=" + Machine03_min
				+ ", Machine03_max=" + Machine03_max + "]";
	}
	public String getMachine00_min() {
		return Machine00_min;
	}
	public void setMachine00_min(String machine00_min) {
		Machine00_min = machine00_min;
	}
	public String getMachine00_max() {
		return Machine00_max;
	}
	public void setMachine00_max(String machine00_max) {
		Machine00_max = machine00_max;
	}
	public String getMachine01_min() {
		return Machine01_min;
	}
	public void setMachine01_min(String machine01_min) {
		Machine01_min = machine01_min;
	}
	public String getMachine01_max() {
		return Machine01_max;
	}
	public void setMachine01_max(String machine01_max) {
		Machine01_max = machine01_max;
	}
	public String getMachine02_min() {
		return Machine02_min;
	}
	public void setMachine02_min(String machine02_min) {
		Machine02_min = machine02_min;
	}
	public String getMachine02_max() {
		return Machine02_max;
	}
	public void setMachine02_max(String machine02_max) {
		Machine02_max = machine02_max;
	}
	public String getMachine03_min() {
		return Machine03_min;
	}
	public void setMachine03_min(String machine03_min) {
		Machine03_min = machine03_min;
	}
	public String getMachine03_max() {
		return Machine03_max;
	}
	public void setMachine03_max(String machine03_max) {
		Machine03_max = machine03_max;
	}
	
	
}
