package com.pomometer.PomometerApp;

public class Project {

	private int id;
	private String name;

	public int getId(){
		return id;
	}
	public String getName() {
		return name;
	}
	
	public void setId(int id){
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Project(int id, String name){
		setName(name);
		setId(id);
	}
}