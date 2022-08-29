import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        Scanner cmd = new Scanner(System.in);
        Task[] list = new Task[100];
        int cnt = 0;
        Boolean isLast = false;

        System.out.println(line + " Hello! I'm Tommy\n" + " What can I do for you?\n" + line);

        do {
            String foo = cmd.nextLine();
            String[] arr = foo.split(" ",2);
            String first = arr[0];
            String second = null;
            if (arr.length > 1) {
                second = arr[1];
            }

            if (foo.contains("unmark")) {
                String a = foo.substring(7);
                int index = Integer.parseInt(a) - 1;
                list[index].setUnDone();
                System.out.println(line + " OK, I've marked this task as not done yet:");
                System.out.println(list[index].toString());
                System.out.println(line);
            } else if (foo.contains("mark")) {
                String a = foo.substring(5);
                int index = Integer.parseInt(a) - 1;
                list[index].setDone();
                System.out.println(line + "Nice! I've marked this task as done:");
                System.out.println(list[index].toString());
                System.out.println(line);
            } else {
                switch (first) {
                case "todo":
                    list[cnt] = new Todo(second);
                    System.out.println(line + " Got it. I've added this task:");
                    System.out.println("\t" + list[cnt].toString());
                    cnt++;
                    System.out.println(" Now you have " + cnt + " tasks in the list.");
                    System.out.println(line);
                    break;
                case "deadline":
                    String arr2[] = second.split("/by",2);
                    String desc = arr2[0];
                    String deadline = arr2[1];
                    list[cnt] = new Deadline(desc,deadline);
                    System.out.println(line + " Got it. I've added this task:");
                    System.out.println("\t" + list[cnt].toString());
                    cnt++;
                    System.out.println(" Now you have " + cnt + " tasks in the list.");
                    System.out.println(line);
                    break;
                case "event":
                    String event[] = second.split("/at",2);
                    String name = event[0];
                    String time = event[1];
                    list[cnt] = new Event(name,time);
                    System.out.println(line + " Got it. I've added this task:");
                    System.out.println("\t" + list[cnt].toString());
                    cnt++;
                    System.out.println(" Now you have " + cnt + " tasks in the list.");
                    System.out.println(line);
                    break;
                case "list":
                    System.out.println(line);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < cnt; i++) {
                        System.out.println("\t" + (i+1) + "." + list[i].toString());
                    }
                    System.out.println(line);
                    break;
                case "blah":
                    System.out.println(line + " blah\n" + line);
                    break;
                case "bye":
                    isLast = true;
                    break;
                default:
                    break;
                }
            }
        } while (!isLast);

        System.out.println(line + " Bye. Hope to see you again soon!\n" + line);
    }
}
