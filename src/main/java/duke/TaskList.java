package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    public String command;
    public String filePath;
    public ArrayList<Task> tasks;
    public int numberOfTasks;

    public String[] words;
    public String firstWord;

    UI ui = new UI();

    public TaskList(String command, String filePath, ArrayList<Task> tasks, int numberOfTasks) {
        this.command = command;
        this.filePath = filePath;
        this.tasks = tasks;
        this.numberOfTasks = numberOfTasks;

        words = command.split(" ");
        firstWord = words[0];
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    public Task createTask(String command) throws DukeException{
        //Handle empty description exception
        if (words.length == 1) {
            throw new DukeException();
        }

        Task newTask = new Task("");

        switch (firstWord) {
        case "todo" :
            newTask = new Todo(command.substring(5));
            break;
        case "deadline" :
            String deadlineWords[] = command.substring(8).split("/");
            newTask = new Deadline(deadlineWords[0].trim(), deadlineWords[1].substring(3));
            break;
        case "event" :
            String eventWords[] = command.substring(6).split("/");
            newTask = new Event(eventWords[0].trim(), eventWords[1].substring(3));
            break;
        }
        return newTask;
    }

    public void addTask(Task currentTask, boolean isFile) {
        tasks.add(currentTask);
        numberOfTasks++;

        if (!isFile) {
            ui.printDivider();
            System.out.println("\tGot it! (๑˃ᴗ˂)ﻭ I've added this task:");
            System.out.print("\t  ");
            System.out.println(currentTask);
            System.out.println("\tNow you have " + numberOfTasks + " task(s) in the list! 凸(￣ヘ￣)");
            System.out.println("");
            ui.printDivider();
        }
    }

    public void removeTask(int index) {
        try {
            Task removedTask = tasks.get(index);
            tasks.remove(index);
            numberOfTasks--;

            ui.printDivider();
            System.out.println("\tNoted! (๑˃ᴗ˂)ﻭ I've removed this task:");
            System.out.print("\t  ");
            System.out.println(removedTask);
            System.out.println("\tNow you have " + numberOfTasks + " task(s) in the list! 凸(￣ヘ￣)");
            System.out.println("");
            ui.printDivider();
        } catch (IndexOutOfBoundsException e) {
            ui.printIndexError();
        }
    }

    public void printList() {
        ui.printDivider();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.print("\t" + (i + 1) + ".");
            System.out.println(tasks.get(i));
        }
        System.out.println();
        ui.printDivider();
    }

    public void markAsDone(String index, boolean isFile) {
        Storage storage = new Storage(filePath, tasks, numberOfTasks);

        try {
            Task currentTask = tasks.get(Integer.parseInt(index) - 1);
            currentTask.markAsDone();
            storage.rewriteFile();

            if (!isFile) {
                ui.printDivider();
                System.out.println("\tNice! (〃＾▽＾〃) I've marked this task as done:");
                System.out.println("\t  [" + currentTask.getStatusIcon() + "] " + currentTask.description);
                System.out.println("\tWell done completing your task, friend! (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧");
                System.out.println("");
                ui.printDivider();
            }
        } catch (NullPointerException e) {
            ui.printIndexError();
        } catch (IOException e) {
            ui.printLoadingError();
        }
    }

    public void markAsUndone(String index, boolean isFile) {
        Storage storage = new Storage(filePath, tasks, numberOfTasks);

        try {
            Task currentTask = tasks.get(Integer.parseInt(index) - 1);
            currentTask.markAsUndone();
            storage.rewriteFile();

            if (!isFile) {
                ui.printDivider();
                System.out.println("\tOK, I've marked this task as not done yet:");
                System.out.println("\t  [" + currentTask.getStatusIcon() + "] " + currentTask.description);
                System.out.println("\tYou better stop procrastinating, friend.. (╥﹏╥)");
                System.out.println("");
                ui.printDivider();
            }
        } catch (NullPointerException e) {
            ui.printIndexError();
        } catch (IOException e) {
            ui.printLoadingError();
        }
    }

    public void findTask(String taskSubstring) {
        int currentIndex = 1;

        ui.printDivider();
        System.out.println("\t  Here are the matching tasks in your list:");

        for (Task task : tasks) {
            if (task.description.contains(taskSubstring)) {
                System.out.println("\t  " + currentIndex + "." + task);
            }
        }

        ui.printDivider();
    }
}
