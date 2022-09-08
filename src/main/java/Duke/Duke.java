package Duke;

import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Task;
import Duke.task.Todo;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws EmptyToDo, IllegalCommand {
        String line = "____________________________________________________________\n";
        Scanner cmd = new Scanner(System.in);
        Task[] list = new Task[100];
        int cnt = 0;
        Boolean isLast = false;

        System.out.println(line + " Hello! I'm Tommy\n" + " What can I do for you?\n" + line);

        do {
            String input = cmd.nextLine();
            String[] arr = input.split(" ",2);
            String first = arr[0];
            String second = null;
            if (arr.length > 1) {
                second = arr[1];
            }

            if (input.contains("unmark")) {
                String a = input.substring(7);
                int index = Integer.parseInt(a) - 1;
                list[index].setUnDone();
                System.out.println(line + " OK, I've marked this Duke.task as not done yet:");
                System.out.println(list[index].toString());
                System.out.println(line);
            } else if (input.contains("mark")) {
                String a = input.substring(5);
                int index = Integer.parseInt(a) - 1;
                list[index].setDone();
                System.out.println(line + "Nice! I've marked this Duke.task as done:");
                System.out.println(list[index].toString());
                System.out.println(line);
            } else {
                try {
                    switch (first) {
                    case "todo":
                        if (second == null) {
                            throw new EmptyToDo();
                        }
                        list[cnt] = new Todo(second);
                        System.out.println(line + " Got it. I've added this Duke.task:");
                        System.out.println("\t" + list[cnt].toString());
                        cnt++;
                        System.out.println(" Now you have " + cnt + " tasks in the list.");
                        System.out.println(line);
                        break;
                    case "deadline":
                        String arr2[] = second.split("/by", 2);
                        String desc = arr2[0];
                        String deadline = arr2[1];
                        list[cnt] = new Deadline(desc, deadline);
                        System.out.println(line + " Got it. I've added this Duke.task:");
                        System.out.println("\t" + list[cnt].toString());
                        cnt++;
                        System.out.println(" Now you have " + cnt + " tasks in the list.");
                        System.out.println(line);
                        break;
                    case "event":
                        String event[] = second.split("/at", 2);
                        String name = event[0];
                        String time = event[1];
                        list[cnt] = new Event(name, time);
                        System.out.println(line + " Got it. I've added this Duke.task:");
                        System.out.println("\t" + list[cnt].toString());
                        cnt++;
                        System.out.println(" Now you have " + cnt + " tasks in the list.");
                        System.out.println(line);
                        break;
                    case "list":
                        System.out.println(line);
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < cnt; i++) {
                            System.out.println("\t" + (i + 1) + "." + list[i].toString());
                        }
                        System.out.println(line);
                        break;
                    case "bye":
                        isLast = true;
                        break;
                    default:
                        throw new IllegalCommand();
                    }
                } catch (EmptyToDo e){
                    System.out.println(line + "☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println(line);
                } catch (IllegalCommand e) {
                    System.out.println(line + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println(line);
                }
            }
        } while (!isLast);

        System.out.println(line + " Bye. Hope to see you again soon!\n" + line);
    }
}
