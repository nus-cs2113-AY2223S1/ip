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

            if (foo.contains("unmark")) {
                String a = foo.substring(7);
                int index = Integer.parseInt(a) - 1;
                list[index].setUnDone();
                System.out.println(line + " OK, I've marked this task as not done yet:");
                System.out.println("  [" + list[index].getStatusIcon() + "]" + list[index].description);
                System.out.println(line);
            } else if (foo.contains("mark")) {
                String a = foo.substring(5);
                int index = Integer.parseInt(a) - 1;
                list[index].setDone();
                System.out.println(line + "Nice! I've marked this task as done:");
                System.out.println("  [" + list[index].getStatusIcon() + "] " + list[index].description);
                System.out.println(line);
            } else {
                switch (foo) {
                case "list":
                    System.out.println(line);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < cnt; i++) {
                        System.out.println((i + 1) + ".[" + list[i].getStatusIcon() + "]" + list[i].description);
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
                    list[cnt] = new Task(foo);
                    cnt++;
                    System.out.println(line + "added: " + foo + "\n" + line);
                    break;
                }
            }
        } while (!isLast);

        System.out.println(line + " Bye. Hope to see you again soon!\n" + line);
    }
}
