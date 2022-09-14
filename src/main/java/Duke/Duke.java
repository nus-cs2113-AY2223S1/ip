package Duke;

import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Task;
import Duke.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private static void writeToFile(String filePath, Integer cnt, ArrayList list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < cnt; i++) {
            fw.write(list.get(i).toString() + System.lineSeparator());
        }
        fw.close();
    }

    private static int getFileContents(String filePath, Integer cnt, ArrayList list) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            list.add(cnt, s.nextLine());
            cnt++;
        }
        return cnt;
    }
    public static void main(String[] args) throws EmptyToDo, IllegalCommand {

        String line = "____________________________________________________________\n";
        Scanner cmd = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        int cnt = 0;
        Boolean isLast = false;
        File storedList = new File("data/duke.txt");
        String fileLocation = storedList.getAbsolutePath();

        try {
            cnt = getFileContents(fileLocation, cnt, list);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }


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
                Task toSet = list.get(index);
                toSet.setUnDone();
                list.set(index, toSet);
                System.out.println(line + " OK, I've marked this Duke.task as not done yet:");
                System.out.println(list.get(index).toString());
                System.out.println(line);
            } else if (input.contains("mark")) {
                String a = input.substring(5);
                int index = Integer.parseInt(a) - 1;
                Task toSet = list.get(index);
                toSet.setDone();
                list.set(index, toSet);
                System.out.println(line + "Nice! I've marked this Duke.task as done:");
                System.out.println(list.get(index).toString());
                System.out.println(line);
            } else {
                try {
                    switch (first) {
                    case "todo":
                        if (second == null) {
                            throw new EmptyToDo();
                        }
                        list.add(new Todo(second));
                        System.out.println(line + " Got it. I've added this Duke.task:");
                        System.out.println("\t" + list.get(cnt).toString());
                        cnt++;
                        System.out.println(" Now you have " + cnt + " tasks in the list.");
                        System.out.println(line);
                        break;
                    case "deadline":
                        String arr2[] = second.split("/by", 2);
                        String desc = arr2[0];
                        String deadline = arr2[1];
                        list.add(new Deadline(desc, deadline));
                        System.out.println(line + " Got it. I've added this Duke.task:");
                        System.out.println("\t" + list.get(cnt).toString());
                        cnt++;
                        System.out.println(" Now you have " + cnt + " tasks in the list.");
                        System.out.println(line);
                        break;
                    case "event":
                        String event[] = second.split("/at", 2);
                        String name = event[0];
                        String time = event[1];
                        list.add(new Event(name, time));
                        System.out.println(line + " Got it. I've added this Duke.task:");
                        System.out.println("\t" + list.get(cnt).toString());
                        cnt++;
                        System.out.println(" Now you have " + cnt + " tasks in the list.");
                        System.out.println(line);
                        break;
                    case "list":
                        System.out.println(line);
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < cnt; i++) {
                            System.out.println("\t" + (i + 1) + "." + list.get(i).toString());
                        }
                        System.out.println(line);
                        break;
                    case "delete":
                        System.out.println(line);
                        System.out.println("Noted. I've removed this task:");
                        int index = Integer.parseInt(second) - 1;
                        System.out.println("\t" + list.get(index).toString());
                        list.remove(index);
                        cnt--;
                        System.out.println(" Now you have " + cnt + " tasks in the list.");
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

        try {
            writeToFile(fileLocation, cnt, list);
        } catch (IOException e) {
            System.out.println("File not found");
        }

        System.out.println(line + " Bye. Hope to see you again soon!\n" + line);
    }
}
