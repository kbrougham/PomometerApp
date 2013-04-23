package com.pomometer.PomometerApp;

import java.util.Date;

public class Result {

	private int id;
	private String goal;
	private String notes;
	private int duration;
	private java.util.Date started_at;
	private java.util.Date ended_at;
	private int task_id;
	
	public int getId() {
		return id;
	}
	public String getGoal() {
		return goal;
	}
	public String getNotes() {
		return notes;
	}
	public int getDuration() {
		return duration;
	}
	public java.util.Date getStartedAt() {
		return started_at;
	}
	public java.util.Date getEndedAt() {
		return ended_at;
	}
	public int getTaskId() {
		return task_id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public void setStartedAt(java.util.Date started_at) {
		this.started_at = started_at;
	}
	public void setEndedAt(java.util.Date ended_at) {
		this.ended_at = ended_at;
	}
	public void setTaskId(int task_id) {
		this.task_id = task_id;
	}
	
	public Result(int id, String goal, String notes, int duration, java.util.Date started_at, java.util.Date ended_at, int task_id){
		setId(id);
		setGoal(goal);
		setNotes(notes);
		setDuration(duration);
		setStartedAt(started_at);
		setEndedAt(ended_at);
		setTaskId(task_id);
	}
}