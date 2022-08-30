import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Duke {
	public static void greet() {
		String intro = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
				+ "Hello! I'm Jackson, your personal chatbot! :)\n"
				+ "What service are you looking for?\n"
				+ "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
		System.out.println(intro);
	}

	public static void exit() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
				+ "Leaving already? :( Come back soon!\n"
				+ "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}

	private static boolean isContainString(String source, String subItem) {
		String pattern = "\\b" + subItem + "\\b";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(source);
		return m.find();
	}

	private static void markOrNot(char a, ArrayList<String> tasks, int number) {
		String str = tasks.get(number - 1);
		StringBuilder mod = new StringBuilder(str);
		mod.setCharAt(mod.indexOf(" "), a); // fill first space with X
		str = mod.toString();
		tasks.set(number - 1, str); // replace with marked task
		if (a == 'X') {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
					+ "The following task been marked as completed:\n"
					+ tasks.get(number - 1) + "\n"
					+ "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		} else {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
					+ "The following task been marked as not done yet:\n"
					+ tasks.get(number - 1) + "\n"
					+ "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		}
	}

	private static String taskType(String input, String tasktype) {
		String description = input.substring(input.indexOf(' ') + 1, input.indexOf('/') - 1); // get task
		String dateOrTime = input.substring(input.indexOf('/') + 1); // get string after seperator
		System.out.println(input.indexOf('/'));
		dateOrTime = new StringBuilder(dateOrTime).insert(dateOrTime.indexOf(' '), ":").toString(); // add colon
		return "  •[" + tasktype + "]" + "[ ] " + description + " (" + dateOrTime + ")";
	}

	private static void addTask(String task, ArrayList<String> tasks) {
		tasks.add(task);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
				+ "Noted. Following task has been added: " + '\n' + task + "\n"
				+ "Total tasks in list: " + tasks.size() + '\n'
				+ "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}

	public static void modifyList() {
		ArrayList<String> tasks = new ArrayList<String>();
		Scanner reader = new Scanner(System.in);
		String input = reader.nextLine();
		String mark = "mark";
		String umark = "unmark";
		String todo = "todo";
		String deadline = "deadline";
		String event = "event";

		while (true) {
			if (input.equals("bye")) { // ends if input equals to bye
				break;
			}

			else if (input.equals("list")) { // display list of items
				int num = 1;
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
				System.out.println("Here are your tasks:\n");
				for (String task : tasks) {
					System.out.println(num + "." + task + "\n");
					num++;
				}
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			}

			else if (isContainString(input, umark)) { // remove x
				int number = input.charAt(input.length() - 1) - '0'; // get index of task
				if (number > tasks.size() || tasks.size() == 0) {
					System.out.println("Sorry, there is no such task. Please input a correct number");
				} else {
					markOrNot(' ', tasks, number);
				}
			}

			else if (isContainString(input, mark)) { // put x
				int number = input.charAt(input.length() - 1) - '0'; // get index of task
				if (number > tasks.size() || tasks.size() == 0) {
					System.out.println("Sorry, there is no such task. Please input a correct number");
				} else {
					markOrNot('X', tasks, number);
				}
			}

			else if (isContainString(input, todo)) {
				String description = input.substring(input.indexOf(' ') + 1); // get task
				description = "[T][ ] " + description;
				addTask(description, tasks);
			}

			else if (isContainString(input, deadline)) {
				String task = taskType(input, "D");
				addTask(task, tasks);
			}

			else if (isContainString(input, event)) {
				String task = taskType(input, "E");
				addTask(task, tasks);
			}

			input = reader.nextLine(); // get next input indefinitely
		}
	}

	public static void main(String[] args) {
		String jackson = "       _            _                    \n"
				+ "      | |          | |                   \n"
				+ "      | | __ _  ___| | _____  ___  _ __  \n"
				+ "  _   | |/ _` |/ __| |/ / __|/ _ \\| '_ \\ \n"
				+ " | |__| | (_| | (__|   <\\__ \\ (_) | | | |\n"
				+ "  \\____/ \\__,_|\\___|_|\\_\\___/\\___/|_| |_|\n";
		System.out.println("Hello from\n" + jackson);
		greet();
		modifyList();
		exit();

	}
}
