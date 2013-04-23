package com.pomometer.PomometerApp;

public class Task {

	private int id;
	private String name;
	private String description;
	private int effort;
	private int project_id;
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public int getEffort() {
		return effort;
	}
	public int getProjectId() {
		return project_id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setEffort(int effort) {
		this.effort = effort;
	}
	public void setProjectId(int project_id) {
		this.project_id = project_id;
	}
	
	public Task(int id, String name, String description, int effort, int project_id){
		setId(id);
		setName(name);
		setDescription(description);
		setEffort(effort);
		setProjectId(project_id);
	}
}