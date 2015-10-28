package com.gaw.garbage.bean;

import com.gaw.garbage.config.Config;

public class Garbage {
	private long id = Long.MAX_VALUE;
	private String filename = "";
	private boolean hasFile = false;
	private String brief = "";
	private String context = "";
	private long time = 0;
	public Garbage() {
		super();
		// TODO Auto-generated constructor stub
		this.id = Long.MAX_VALUE;
		this.filename = "";
		this.hasFile = false;
		this.brief = "";
		this.context = "";
		this.time = 0;
	}
	
	public Garbage(Garbage garbage) {
		super();
		// TODO Auto-generated constructor stub
		this.id = garbage.getId();
		this.filename = garbage.getFilename();
		this.hasFile = garbage.isHasFile();
		this.brief = garbage.getBrief();
		this.context = garbage.getContext();
		this.time = garbage.getTime();
	}
	
	public Garbage(long id, String filename, boolean hasFile, String brief,
			String context, long time) {
		super();
		this.id = id;
		this.filename = filename;
		this.hasFile = hasFile;
		this.brief = brief;
		this.context = context;
		this.time = time;
	}
	@Override
	public String toString() {
		return "Garbage [id=" + id + ", filename=" + filename + ", hasFile="
				+ hasFile + ", brief=" + brief + ", context=" + context
				+ ", time=" + time + "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public boolean isHasFile() {
		return hasFile;
	}
	public void setHasFile(boolean hasFile) {
		this.hasFile = hasFile;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
	public void clear(){
		this.id = Long.MAX_VALUE;
		this.filename = "";
		this.context = "";
		this.brief = "";
		this.hasFile = false;
		this.time = 0;
	}
}
