package Duke;

import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Task;
import Duke.task.Todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Ui {

    public static String fileLocation = "data/duke.txt";
    public static void drawLine() {
        System.out.println("____________________________________________________________\n");
    }

    public static void sayHi() {
        String tommyLogo =  " _____ ____  _______  _______  __ __  \n"
                +   "  | | |   | || || || || || ||  |_ _|  \n"
                +   "  | | |   | || || || || || ||   | |  \n"
                +   "  |_| |___| || || || || || ||   | |  \n";
        System.out.println(" Hello! I'm\n" + tommyLogo + "\n What can I do for you?\n");
        drawLine();
    }

    public static void sayBye() {
        System.out.println(" Bye. Hope to see you again soon!\n");
        drawLine();
    }

    public static String readInputs() {
        Scanner command = new Scanner(System.in);
        String input = command.nextLine();
        return input;
    }

    public static void checkMarked (Task task, String isDone) {
        if (isDone == "true") {
            task.isDone = true;
        }
    }
    public static int getIndex (String commandActual) {
        return Integer.parseInt(commandActual) - 1;
    }

    public static void listTasks (ArrayList<Task> list) {
        drawLine();
        System.out.println("Here are the tasks in your list:");
        int cnt = list.size();
        for (int i = 0; i < cnt; i++) {
            System.out.println((i + 1) + "." + list.get(i).toString());
        }
    }

    public static void findTasks (ArrayList<Task> list, String commandActual) {
        int size = list.size();
        int numberOfTasks = 1;
        drawLine();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < size; i++) {
            Task currTask = list.get(i);
            if (currTask.description.contains(commandActual)) {
                System.out.println(numberOfTasks + "." + list.get(i).toString());
                numberOfTasks++;
            }
        }
    }
    public static void setUnmarked (ArrayList<Task> list, String commandActual) {
        int indexToUnmark = getIndex(commandActual);
        Task unmarking = list.get(indexToUnmark);
        unmarking.setUnDone();
        list.set(indexToUnmark, unmarking);
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println(list.get(indexToUnmark).toString());
        try {
            Storage.writeToFile(fileLocation, list);
        } catch (IOException e) {
            System.out.println("Something went wrong:" + e.getMessage());
        }
    }
    public static void setMarked (ArrayList<Task> list, String commandActual) {
        int indexToMark = getIndex(commandActual);
        Task marking = list.get(indexToMark);
        marking.setDone();
        list.set(indexToMark, marking);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(indexToMark).toString());
        try {
            Storage.writeToFile(fileLocation, list);
        } catch (IOException e) {
            System.out.println("Something went wrong:" + e.getMessage());
        }
    }

    public static void newToDo (ArrayList<Task> list, String commandActual) throws EmptyToDo {
        if (commandActual == null) {
            throw new EmptyToDo();
        }
        Todo newTodo = new Todo(commandActual);
        list.add(newTodo);
        System.out.println(" Got it. I've added this task:");
        System.out.println("\t" + newTodo.toString());
        System.out.println(" Now you have " + list.size() + " tasks in the list.");
        try {
            Storage.writeToFile(fileLocation, list);
        } catch (IOException e) {
            System.out.println("Something went wrong:" + e.getMessage());
        }
    }
    public static void newDeadline (ArrayList<Task> list, String commandActual) {
        String arr2[] = commandActual.split("/by ", 2);
        String desc = arr2[0];
        String deadline = arr2[1];
        Deadline newDeadline = new Deadline(desc, deadline);
        list.add(newDeadline);
        System.out.println(" Got it. I've added this task:");
        System.out.println("\t" + newDeadline.toString());
        System.out.println(" Now you have " + list.size() + " tasks in the list.");
        try {
            Storage.writeToFile(fileLocation, list);
        } catch (IOException e) {
            System.out.println("Something went wrong:" + e.getMessage());
        }
    }
    public static void newEvent (ArrayList<Task> list, String commandActual) {
        String event[] = commandActual.split("/at ", 2);
        String name = event[0];
        String time = event[1];
        Event newEvent = new Event(name, time);
        list.add(newEvent);
        System.out.println(" Got it. I've added this task:");
        System.out.println("\t" + newEvent.toString());
        System.out.println(" Now you have " + list.size() + " tasks in the list.");
        try {
            Storage.writeToFile(fileLocation, list);
        } catch (IOException e) {
            System.out.println("Something went wrong:" + e.getMessage());
        }
    }
    public static void deleteTask (ArrayList<Task> list, String commandActual) {
        drawLine();
        System.out.println("Noted. I've removed this task:");
        int index = Integer.parseInt(commandActual) - 1;
        System.out.println("\t" + list.get(index).toString());
        list.remove(index);
        System.out.println(" Now you have " + list.size() + " tasks in the list.");
        try {
            Storage.writeToFile(fileLocation, list);
        } catch (IOException e) {
            System.out.println("Something went wrong:" + e.getMessage());
        }
    }
    public static void showError() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
    public static void showEmptyToDo() {
        drawLine();
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        drawLine();
    }
    public static void showIllegalCommand() {
        drawLine();
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        drawLine();
    }

}
