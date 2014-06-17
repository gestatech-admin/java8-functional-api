package org.java8api;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ShowMenu {

	static final String HUNTER2 = "other hunter";
	static final String HUNTER = "hunter";
	static final String BAMBI = "bambi";
	static final String BAMBI_MOM = "bambi's mom";

	static final List<User> users = Arrays.asList(
			new User(BAMBI, false),
			new User(BAMBI_MOM, false), 
			new User(HUNTER, true), 
			new User(HUNTER2, false));

	static final List<Task> tasks = Arrays.asList(
			new Task("shoot " + BAMBI_MOM, HUNTER, true, null),
			new Task("shoot " + BAMBI, HUNTER2, false, HUNTER),
			new Task("survive " + HUNTER, BAMBI_MOM, false, null),
			new Task("survive " + HUNTER, BAMBI, false, null));

	static final List<MenuItem> actions = Arrays.asList(
			new MenuItem("show", "show.xhtml"),
			new MenuItem("complete", "complete.xhtml"),
			new MenuItem("delegate", "delegate.xhtml"),
			new MenuItem("claim", "claim.xhtml"));

	MenuResolver resolver;

	public ShowMenu() {
		resolver = new MenuResolver(() -> actions);
	}

	public static Predicate<MenuItem> showStrategy(final Task task,
			final User user) {
		return (item) -> {
			if ("show".equals(item.label)) {
				return task.assignee.equals(user.id) || user.admin;
			} else {
				return true;
			}
		};
	}

	public static Predicate<MenuItem> completeStrategy(final Task task,
			final User user) {
		return (item) -> {
			if ("complete".equals(item.label)) {
				return (task.owner != null && task.owner.equals(user.id))
						|| task.assignee.equals(user.id) || user.admin;
			} else {
				return true;
			}
		};
	}

	public List<MenuItem> getItems(final Task task, final User user) {
		return resolver.getItems(showStrategy(task, user).and(
				completeStrategy(task, user)));
	}

	public List<MenuItem> getItems2(final Task task, final User user) {
		return resolver
		        .getItems2((map) -> {
			map.put("show", (item) -> task.assignee.equals(user.id)
					|| user.admin);
			map.put("complete",
					(item) -> (task.owner != null && task.owner
					.equals(user.id))
					|| task.assignee.equals(user.id) || user.admin);
		});
	}
	
	public List<MenuItem> getItems3(final Task task, final User user) {
		
		return resolver
		        .getItems2((map) -> {
			map.put("show", (item) -> task.assignee.equals(user.id)
					|| user.admin);
			map.put("complete",
					(item) -> (task.owner != null && task.owner
					.equals(user.id))
					|| task.assignee.equals(user.id) || user.admin);
		});
	}

}
