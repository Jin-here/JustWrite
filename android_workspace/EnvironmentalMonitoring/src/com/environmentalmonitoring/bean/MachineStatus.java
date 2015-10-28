package com.environmentalmonitoring.bean;

import java.io.Serializable;

public class MachineStatus{

	//private static final long serialVersionUID = 1L;
	private boolean machine00 = false;
	private boolean machine01 = false;
	private boolean machine02 = false;
	private boolean machine03 = false;
	public MachineStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MachineStatus(boolean machine00, boolean machine01,
			boolean machine02, boolean machine03) {
		super();
		this.machine00 = false;
		this.machine01 = false;
		this.machine02 = false;
		this.machine03 = false;
		
	}
	@Override
	public String toString() {
		return "MachineStatus [machine00=" + machine00 + ", machine01="
				+ machine01 + ", machine02=" + machine02 + ", machine03="
				+ machine03 + "]";
	}
	
	public boolean getStatus(int i){
		switch(i){
		case 0:
			return machine00;
		case 1:
			return machine01;
		case 2:
			return machine02;
		case 3:
			return machine03;
		}
		return false;
	}
	
	public void setStatus(boolean machine00,boolean machine01,boolean machine02,boolean machine03){
		this.machine00 = machine00;
		this.machine01 = machine01;
		this.machine02 = machine02;
		this.machine03 = machine03;
	}
}
