package org.java8api;

public class User {
	String id;
	boolean admin;

	public User(String id, boolean admin) {
		this.id = id;
		this.admin = admin;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}
