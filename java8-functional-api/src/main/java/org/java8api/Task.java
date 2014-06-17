package org.java8api;

public class Task {

	String name;
	String assignee;
	boolean completed;
	String owner;

	public Task(String name, String assignee, boolean completed, String owner) {
		this.setName(name);
		this.assignee = assignee;
		this.completed = completed;
		this.owner = owner;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Task [name=").append(name).append(", assignee=")
		.append(assignee).append(", completed=").append(completed)
		.append(", owner=").append(owner).append("]");
		return builder.toString();
	}
}
