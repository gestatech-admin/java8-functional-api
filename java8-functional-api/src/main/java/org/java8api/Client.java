package org.java8api;

import java.util.List;

public class Client {


	public static void main(String[] args) {
		ShowMenu menuTest = new ShowMenu();

		showTasksForUsers(ShowMenu.users, ShowMenu.tasks, menuTest);
	}

	public static void showTasksForUsers(final List<User> users, final List<Task> tasks, ShowMenu menuTest) {
		users.stream().forEach((user) -> {
			System.out.println("'"+user.id+"' can do:");
			
			tasks.stream().forEach((task) -> {

				System.out.print("  ");
				menuTest.getItems(task, user).forEach((item) -> System.out.print(item.label+", "));

				System.out.print(" --  On " + task + "\n");
			});

			System.out.println("");
		});
	}
}
