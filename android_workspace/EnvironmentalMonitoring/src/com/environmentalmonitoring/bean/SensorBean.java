package com.environmentalmonitoring.bean;

import java.io.Serializable;


public class SensorBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String minValue = "0";
	
	private String maxValue = "0";

	private String value = "0";
	public SensorBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SensorBean(String minValue, String maxValue, String value) {
		super();
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.value = value;
	}
	@Override
	public String toString() {
		return "SensorBean [minValue=" + minValue + ", maxValue=" + maxValue
				+ ", value=" + value + "]";
	}
	public String getMinValue() {
		return minValue;
	}
	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}
	public String getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
